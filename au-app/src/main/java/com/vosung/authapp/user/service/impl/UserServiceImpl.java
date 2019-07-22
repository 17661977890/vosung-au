package com.vosung.authapp.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.orguserrole.*;
import com.vosung.auapi.client.dto.requestdto.user.ResetPasswordRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UpdateUserStatusRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserRequestDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.user.*;
import com.vosung.auapi.client.dto.responsedto.orguserrole.OrgUserRoleResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.dept.mapper.TAuDepartemntMapper;
import com.vosung.authapp.employee.mapper.TAuEmployeeMapper;
import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.org.service.OrgUserRoleService;
import com.vosung.authapp.role.mapper.TAuRoleMapper;
import com.vosung.authapp.user.mapper.TAuUserMapper;
import com.vosung.authapp.user.mapper.TAuUserOrgRoleMapper;
import com.vosung.authapp.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 用户管理业务层实现类
 * @Author 彬
 * @Date 2019/4/29
 */
@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService{

    @Autowired
    private TAuUserMapper tAuUserMapper;
    @Autowired
    private TAuUserOrgRoleMapper tAuUserOrgRoleMapper;
    @Autowired
    private TAuDepartemntMapper tAuDepartemntMapper;
    @Autowired
    private TAuEmployeeMapper tAuEmployeeMapper;
    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private TAuRoleMapper tAuRoleMapper;
    @Autowired
    private OrgUserRoleService orgUserRoleService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;

    /**
     * 保存用户(含分配角色：用户关联组织，组织关联角色)
     * @param userRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveUser(UserRequestDto userRequestDto) {
        Integer userId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuUser tAuUser = new TAuUser();
        Integer record = 0;
        try {
            BeanUtils.copyProperties(tAuUser,userRequestDto);
            if(userRequestDto.getId() == null){
                //新增用户 判重账号和名称（名称和账号也不可以相同）
                RestResponse restResponse = checkAddIsExist(userRequestDto.getUsername(),userRequestDto.getName());
                List<Map> messageList = (List<Map>) restResponse.getBody();
                if(!CollectionUtils.isEmpty(messageList)){
                    return restResponse;
                }
                tAuUser.setDataState(DataStateCode.CREATED);
                //todo 创建组织，登录用户的组织；用户类型逻辑不清楚；待修改
                //初始密码默认888888；SHA加盐加密（并没有对盐加密，可以用工具加密）
                String initPwd = "888888";
                String encyPwd = setSHAPassword(initPwd);
                tAuUser.setPassword(encyPwd);
                tAuUser.setUserType(ConstantUtil.INNER_USER);
                tAuUser.setCreateOrgId(1);
                tAuUser.setCreateUser(userId);
                tAuUser.setCreateTime(new Date());
                tAuUser.setUpdateTime(new Date());
                tAuUser.setUpdateUser(userId);
                //新增返回主键id
                record = tAuUserMapper.insertSelective(tAuUser);
                //如果组织机构角色关联列表不为空，要保存组织用户关联信息以及组织角色信息
                saveUserOrgRole(tAuUser.getId(),userRequestDto.getOrgRoleDtoList());
            }else {
                //修改用户 改变进行判重
                TAuUser tAuUser1 = tAuUserMapper.selectByPrimaryKey(userRequestDto.getId());
                if(tAuUser1 == null){
                    throw new BusinessException("3011111151",getMessage("3011111151"));
                }
                Boolean a = !tAuUser1.getUsername().equalsIgnoreCase(userRequestDto.getUsername());
                Boolean b = !tAuUser1.getName().equalsIgnoreCase(userRequestDto.getName());
                if(a || b){
                    //只要修改账号或者名称，就判重
                    RestResponse restResponse = checkUpdateIsExist(userRequestDto.getUsername(),userRequestDto.getName(),a,b);
                    List<Map> messageList = (List<Map>) restResponse.getBody();
                    if(!CollectionUtils.isEmpty(messageList)){
                        return restResponse;
                    }
                }
                tAuUser.setUpdateTime(new Date());
                tAuUser.setUpdateUser(userId);
                record = tAuUserMapper.updateByPrimaryKeySelective(tAuUser);
                //保存用户组织角色关系
                updateUserOrgRole(userRequestDto.getId(),userRequestDto.getOrgRoleDtoList());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存用户信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据id查询用户详情（包含组织用户角色得关系）
     * @param id
     * @return
     */
    @Override
    public RestResponse getUserInfoById(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        TAuUser tAuUser = tAuUserMapper.selectByPrimaryKey(id);
        try {
            BeanUtils.copyProperties(userInfoResponseDto,tAuUser);
            //所属部门名赋值
            if(tAuUser.getBelongDeptId() != null){
                TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(tAuUser.getBelongDeptId());
                userInfoResponseDto.setBelongDeptName(tAuDepartemnt.getDeptName());
            }
            //根据联系对象类型去查对应的名称，现在只有员工
            if(tAuUser.getContactStaff() != null){
                if(ConstantUtil.EMPLOYEE.equals(tAuUser.getContactStaffType())){
                    TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(tAuUser.getContactStaff());
                    userInfoResponseDto.setContactStaffName(tAuEmployee.getEmployeeName());
                }

            }
            //用户组织关系---根据用户id查组织列表
            List<OrgRoleResponseDto>  orgRoleResponseDtos = getUserOrgRole(id);
            userInfoResponseDto.setList(orgRoleResponseDtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查看用户详情成功");
        return RestResponse.resultSuccess(userInfoResponseDto,restResponseHeader);
    }

    /**
     * 获取所有用户列表
     * @return
     */
    @Override
    public RestResponse getUserList(RestRequest<UserListRequestDto> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        UserResponse userResponse = new UserResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(UserResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            TAuUserExample example = new TAuUserExample();
            TAuUserExample.Criteria criteria = example.createCriteria();
            //显示未删除的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);

            PageMethod.startPage(pageNum,pageSize,"id");
            if(restRequest.getBody().getBelongDeptId()!=null){
                criteria.andBelongDeptIdEqualTo(restRequest.getBody().getBelongDeptId());
            }
            if(restRequest.getBody().getProhibitState()!=null){
                criteria.andProhibitStateEqualTo(restRequest.getBody().getProhibitState());
            }
            List<TAuUser> tAuUserList =  tAuUserMapper.selectByExample(example);
            List<UserResponseDto> userResponseDtos = new ArrayList<>();

            for (TAuUser tAuUser: tAuUserList) {
                UserResponseDto userResponseDto =new UserResponseDto();
                BeanUtils.copyProperties(userResponseDto,tAuUser);
                userResponseDto.setUserType((String) redisUtil.hget(SysDictEnum.USER_TYPE.getCode(),userResponseDto.getUserType()));
                if(userResponseDto.getBelongDeptId()!=null){
                    TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(userResponseDto.getBelongDeptId());
                    if(tAuDepartemnt!=null){
                        userResponseDto.setBelongDeptName(tAuDepartemnt.getDeptName());
                    }
                }
                userResponseDtos.add(userResponseDto);
            }
            Integer sum = tAuUserMapper.countByExample(example);
            userResponse.setColumnList(viewColumnList);
            userResponse.setPageInfo(new PageInfo<>(userResponseDtos));
            userResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询用户列表成功 ");
        return RestResponse.resultSuccess(userResponse,restResponseHeader);
    }

    /**
     * 禁用用户
     * @param requestDto
     * @return
     */
    @Override
    public RestResponse forbiddenUser(UpdateUserStatusRequestDto requestDto) {
        Integer userId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] userIds = requestDto.getUserIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<userIds.length;i++) {
            Map map = new HashMap();
            TAuUser tAuUser = tAuUserMapper.selectByPrimaryKey(Integer.valueOf(userIds[i]));

            //禁用
            if (ConstantUtil.YES_OR_NO_N.equals(tAuUser.getProhibitState())) {
                tAuUser.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuUser.setProhibitUser(userId);
                tAuUser.setProhibitTime(new Date());
                tAuUser.setUpdateTime(new Date());
                tAuUser.setUpdateUser(userId);
                tAuUserMapper.updateByPrimaryKeySelective(tAuUser);
                map.put("message", "用户账号为 ：" + tAuUser.getUsername() + " 禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "用户账号为 ：" + tAuUser.getUsername() + " 已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 反禁用用户
     * @param requestDto
     * @return
     */
    @Override
    public RestResponse unForbiddenUser(UpdateUserStatusRequestDto requestDto) {
        Integer userId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] userIds = requestDto.getUserIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<userIds.length;i++) {
            Map map = new HashMap();
            TAuUser tAuUser = tAuUserMapper.selectByPrimaryKey(Integer.valueOf(userIds[i]));
            //反禁用
            if (ConstantUtil.YES_OR_NO_Y.equals(tAuUser.getProhibitState())) {
                tAuUser.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuUser.setLiftBanBy(userId);
                tAuUser.setLiftBanTime(new Date());
                tAuUser.setUpdateTime(new Date());
                tAuUser.setUpdateUser(userId);
                tAuUserMapper.updateByPrimaryKeySelective(tAuUser);
                map.put("message", "用户账号为 ：" + tAuUser.getUsername() + " 反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "用户账号为 ：" + tAuUser.getUsername() + " 已处于反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 分配角色（新增数据）---需求不支持批量操作，三层循环效率准确率得不到保障
     * @param userOrgRoleRequestDto
     * @return
     */
    @Override
    public RestResponse addUserOrgRole(UserOrgRoleRequestDto userOrgRoleRequestDto) {
        Integer userId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        Integer record =0;
        for (OrgRoleDto orgRoleDto: userOrgRoleRequestDto.getOrgRoleDtoList()) {
            for (Integer roleId:orgRoleDto.getRoleIds()) {
                //如果是已有的数据，不需要重复添加
                TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
                TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
                criteria.andUserIdEqualTo(userOrgRoleRequestDto.getUserId());
                criteria.andOrgIdEqualTo(orgRoleDto.getOrgId());
                criteria.andRoleIdEqualTo(roleId);
                List<TAuUserOrgRole> userOrgRoleList =  tAuUserOrgRoleMapper.selectByExample(example);
                if(CollectionUtils.isEmpty(userOrgRoleList)){
                    TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                    tAuUserOrgRole.setUserId(userOrgRoleRequestDto.getUserId());
                    tAuUserOrgRole.setOrgId(orgRoleDto.getOrgId());
                    tAuUserOrgRole.setRoleId(roleId);
                    tAuUserOrgRole.setCreateUser(userId);
                    tAuUserOrgRole.setCreateTime(new Date());
                    tAuUserOrgRole.setUpdateUser(userId);
                    tAuUserOrgRole.setUpdateTime(new Date());
                    tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                    record++;
                }

            }
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("分配角色成功");
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 重置用户密码
     * @param resetPasswordRequestDto
     * @return
     */
    @Override
    public RestResponse resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) {
        if(!resetPasswordRequestDto.getNewPassword().equals(resetPasswordRequestDto.getConfirmNewPassword())){
            throw new BusinessException("3011111161",getMessage("3011111161"));
        }
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuUser tAuUser = tAuUserMapper.selectByPrimaryKey(resetPasswordRequestDto.getId());
        //加密后存
        String pwd = setSHAPassword(resetPasswordRequestDto.getNewPassword());
        tAuUser.setPassword(pwd);
        Integer record = tAuUserMapper.updateByPrimaryKeySelective(tAuUser);
        if(record==1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("重置密码成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 获取用户所属部门
     * @return
     */
    @Override
    public RestResponse getBelongDept() {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuUserExample example = new TAuUserExample();
        List<TAuUser> tAuUsers = tAuUserMapper.selectByExample(example);
        List<BelongDeptDto> belongDeptDtoList = new ArrayList<>();
        List<Integer> deptIds = new ArrayList<>();
        for (TAuUser tAuUser: tAuUsers) {
            if(!deptIds.contains(tAuUser.getBelongDeptId())){
                deptIds.add(tAuUser.getBelongDeptId());
                TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(tAuUser.getBelongDeptId());
                if(tAuDepartemnt == null){
                    throw new BusinessException("3011111123",getMessage("3011111123"));
                }
                BelongDeptDto belongDeptDto = new BelongDeptDto();
                belongDeptDto.setId(tAuUser.getBelongDeptId());
                belongDeptDto.setDeptName(tAuDepartemnt.getDeptName());
                belongDeptDtoList.add(belongDeptDto);
            }
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("展示所属部门成功");
        return RestResponse.resultSuccess(belongDeptDtoList,restResponseHeader);
    }

    /**
     * 根据用户id 查询关联组织列表和角色列表（默认显示该用户关联第一个组织下的角色list）
     * @param userId
     * @return
     */
    private List<OrgRoleResponseDto> getUserOrgRole(Integer userId) throws InvocationTargetException, IllegalAccessException {
        TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
        TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
        List<Integer> orgIds = new ArrayList<>();
        List<OrgRoleResponseDto> orgList = new ArrayList<>();
        //orgId去重
        for (TAuUserOrgRole t:tAuUserOrgRoleList) {
            if (t.getOrgId()!=null && !orgIds.contains(t.getOrgId())) {
                orgIds.add(t.getOrgId());
            }
        }
        for (Integer orgId:orgIds) {
            OrgRoleResponseDto orgRoleResponseDto = new OrgRoleResponseDto();
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(orgId);
            BeanUtils.copyProperties(orgRoleResponseDto,tAuOrganization);
            orgList.add(orgRoleResponseDto);
        }
        //用户组织 对应的角色列表
        for (OrgRoleResponseDto orgRoleResponseDto: orgList) {
            GetUserRoleListRequestDto getUserRoleListRequestDto = new GetUserRoleListRequestDto();
            getUserRoleListRequestDto.setOrgId(orgRoleResponseDto.getId());
            getUserRoleListRequestDto.setUserId(userId);
            RestResponse restResponse = orgUserRoleService.getRoleListByOrgUserId(getUserRoleListRequestDto);
            OrgUserRoleResponseDto orgUserRoleResponseDto = (OrgUserRoleResponseDto)restResponse.getBody();
            if(orgUserRoleResponseDto.getRoleList() == null){
                orgUserRoleResponseDto.setRoleList(new ArrayList<>());
            }
            orgRoleResponseDto.setList(orgUserRoleResponseDto.getRoleList());
        }
        return orgList;
    }

    /**
     * 密码加密方法：SHA-256+盐
     * @param password
     * @return
     */
    public String setSHAPassword(String password) {
        MessageDigest messageDigest;
        String SHAPwd = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update((password).getBytes("UTF-8"));
            SHAPwd = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "vosung"+SHAPwd+"authapp";
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    public String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for(int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if(temp.length()==1){
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * 新增用户组织角色关系
     * @param orgRoleDtoList
     * @param userId
     */
    private void saveUserOrgRole(Integer userId,List<OrgRoleDto> orgRoleDtoList){
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        Integer record = 0;
        for (OrgRoleDto orgRoleDto: orgRoleDtoList) {
            for (Integer roleId: orgRoleDto.getRoleIds()) {
                TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                tAuUserOrgRole.setOrgId(orgRoleDto.getOrgId());
                tAuUserOrgRole.setUserId(userId);
                tAuUserOrgRole.setRoleId(roleId);
                tAuUserOrgRole.setCreateTime(new Date());
                tAuUserOrgRole.setCreateUser(loginUserId);
                tAuUserOrgRole.setUpdateTime(new Date());
                tAuUserOrgRole.setUpdateUser(loginUserId);
                tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                record++;
            }
        }
        log.info("新增用户时，对应新增得用户组织角色关系，数量为："+record);

    }

    /**
     * 修改调整组织用户关系
     * @param userId
     * @param orgRoleDtoList
     */
    private void updateUserOrgRole(Integer userId,List<OrgRoleDto> orgRoleDtoList){
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        //根据用户id查，之前有没有数据
        TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
        TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
        Integer record=0;
        Integer deleteCount=0;
        if(CollectionUtils.isEmpty(tAuUserOrgRoleList)){
            //之前没有数据
            if(!CollectionUtils.isEmpty(orgRoleDtoList)){
                //如果传参用户和角色的对应list不为空，即新增数据
                for (OrgRoleDto orgRoleDto:orgRoleDtoList) {
                    for (Integer roleId:orgRoleDto.getRoleIds()) {
                        TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                        tAuUserOrgRole.setOrgId(orgRoleDto.getOrgId());
                        tAuUserOrgRole.setUserId(userId);
                        tAuUserOrgRole.setRoleId(roleId);
                        tAuUserOrgRole.setCreateTime(new Date());
                        tAuUserOrgRole.setCreateUser(loginUserId);
                        tAuUserOrgRole.setUpdateTime(new Date());
                        tAuUserOrgRole.setUpdateUser(loginUserId);
                        tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                        record++;
                    }
                }
                log.info("修改调整组织用户关系:新增组织用户维护关系数量："+record);
            }
        }else {
            //之前有数据
            if(CollectionUtils.isEmpty(orgRoleDtoList) && userId!=null){
                //传参为空，即删除所有之前数据
                deleteCount = tAuUserOrgRoleMapper.deleteByExample(example);
                log.info("传参组织和角色对应关系全为空，删除用户id为："+userId+" 组织用户角色关系数量："+deleteCount);
            }else if(!CollectionUtils.isEmpty(orgRoleDtoList) && userId!=null){
                List<Integer> orgIds = new ArrayList<>();
                Map<Integer,List<Integer>> map = new HashMap();
                for (OrgRoleDto orgRoleDto: orgRoleDtoList) {
                    if(!orgIds.contains(orgRoleDto.getOrgId())){
                        orgIds.add(orgRoleDto.getOrgId());
                        map.put(orgRoleDto.getOrgId(),orgRoleDto.getRoleIds());
                        log.info("传参中组织与角色的对应关系："+ JSON.toJSONString(map));
                    }

                }
                //传参不为空,查关联组织list变化
                List<TAuOrganization> orgList = getOrgRoleListByUserId(userId);
                List<Integer> orgIdList = new ArrayList<>();
                for (TAuOrganization tAuOrganization: orgList) {
                    orgIdList.add(tAuOrganization.getId());
                }
                //先看传参中用户对应原有用户
                for (TAuOrganization tAuOrganization: orgList) {
                    if(orgIds.contains(tAuOrganization.getId())){
                        //传参组织id，之前数据也有，再看角色变化
                        GetUserRoleListRequestDto getUserRoleListRequestDto = new GetUserRoleListRequestDto();
                        getUserRoleListRequestDto.setUserId(userId);
                        getUserRoleListRequestDto.setOrgId(tAuOrganization.getId());
                        RestResponse restResponse = orgUserRoleService.getRoleListByOrgUserId(getUserRoleListRequestDto);
                        OrgUserRoleResponseDto responseDto = (OrgUserRoleResponseDto)restResponse.getBody();
                        List<TAuRole> roleList = responseDto.getRoleList();
                        List<Integer> roleIdList = new ArrayList<>();
                        for (TAuRole tAuRole: roleList) {
                            roleIdList.add(tAuRole.getId());
                        }
                        //开始角色比较
                        for (TAuRole tAuRole: roleList) {
                            if(!(map.get(tAuOrganization.getId())).contains(tAuRole.getId())){
                                //传参用户下角色id，之前有，不操作
                                //之前有，但是传参没有。
                                criteria.andOrgIdEqualTo(tAuOrganization.getId());
                                criteria.andRoleIdEqualTo(tAuRole.getId());
                                deleteCount = tAuUserOrgRoleMapper.deleteByExample(example);
                                log.info("删除组织id为："+tAuOrganization.getId()+" 用户id为："+userId+"角色id为："+tAuRole.getId()+" 的组织维护关系数量："+deleteCount);
                            }
                        }
                        for (Integer roleId:map.get(tAuOrganization.getId())) {
                            if(!roleIdList.contains(roleId)){
                                //传参多与原有，新增
                                TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                                tAuUserOrgRole.setOrgId(tAuOrganization.getId());
                                tAuUserOrgRole.setUserId(userId);
                                tAuUserOrgRole.setRoleId(roleId);
                                tAuUserOrgRole.setCreateTime(new Date());
                                tAuUserOrgRole.setCreateUser(loginUserId);
                                tAuUserOrgRole.setUpdateTime(new Date());
                                tAuUserOrgRole.setUpdateUser(loginUserId);
                                tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                                record++;
                                log.info("============新增组织用户维护关系数量，对应组织用户的角色变化=============");
                            }

                        }
                    }else {
                        //之前用户id，传参没有，删除数据
                        criteria.andOrgIdEqualTo(tAuOrganization.getId());
                        deleteCount = tAuUserOrgRoleMapper.deleteByExample(example);
                        log.info("删除组织id为："+tAuOrganization.getId()+" 用户id为："+userId+" 的组织维护关系数量："+deleteCount);
                    }
                }
                //再看原有组织的对应传参用户
                for (Integer orgId: orgIds) {
                    if (!orgIdList.contains(orgId)){
                        //传参在原有中不存在，新增数据
                        if(CollectionUtils.isEmpty(map.get(orgId))){
                            //对应用户角色为空---保存用户组织关系
                            TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                            tAuUserOrgRole.setOrgId(orgId);
                            tAuUserOrgRole.setUserId(userId);
                            tAuUserOrgRole.setCreateTime(new Date());
                            tAuUserOrgRole.setCreateUser(loginUserId);
                            tAuUserOrgRole.setUpdateTime(new Date());
                            tAuUserOrgRole.setUpdateUser(loginUserId);
                            tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                            record++;
                            log.info("对应用户角色,用户id："+userId+"之前不存在，且传参没有对应角色,新增组织用户关系");
                        }else {
                            //对应用户角色list存在
                            for (Integer roleId:map.get(orgId)) {
                                TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                                tAuUserOrgRole.setOrgId(orgId);
                                tAuUserOrgRole.setUserId(userId);
                                tAuUserOrgRole.setRoleId(roleId);
                                tAuUserOrgRole.setCreateTime(new Date());
                                tAuUserOrgRole.setCreateUser(loginUserId);
                                tAuUserOrgRole.setUpdateTime(new Date());
                                tAuUserOrgRole.setUpdateUser(loginUserId);
                                tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                                record++;
                                log.info("对应用户角色,用户id："+userId+"之前不存在，且传参有对应角色，新增组织用户关系");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 查用户对应组织
     * @param userId
     * @return
     */
    private List<TAuOrganization> getOrgRoleListByUserId(Integer userId){
        TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
        TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
        List<Integer> orgIds = new ArrayList<>();
        List<TAuOrganization> orgList = new ArrayList<>();
        //userId去重
        for (TAuUserOrgRole t:tAuUserOrgRoleList) {
            if (!orgIds.contains(t.getOrgId())) {
                orgIds.add(t.getOrgId());
            }
        }
        for (Integer orgId:orgIds) {
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(orgId);
            orgList.add(tAuOrganization);
        }
        return orgList;
    }


    /**
     * 检查新增用户判重
     * @param username
     * @param name
     */
    private RestResponse checkAddIsExist(String username,String name){
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuUserExample example = new TAuUserExample();
        example.or().andUsernameEqualTo(username);
        example.or().andNameEqualTo(name);
        example.or().andNameEqualTo(username);
        example.or().andUsernameEqualTo(name);
        List<TAuUser> userList = tAuUserMapper.selectByExample(example);
        List<Map> messageList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userList)){
            if(username.equalsIgnoreCase(userList.get(0).getUsername())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此账号：'"+username+"'已存在");
                messageList.add(map);
            }
            if(name.equalsIgnoreCase(userList.get(0).getName())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此用户名：'"+name+"'已存在");
                messageList.add(map);
            }
            if(username.equalsIgnoreCase(userList.get(0).getName())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此账号：'"+username+"'与以有用户名称重复");
                messageList.add(map);
            }
            if(name.equalsIgnoreCase(userList.get(0).getUsername())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此用户名：'"+name+"'与以有账号重复");
                messageList.add(map);
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("新增失败");
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 用户修改判重
     * @param username
     * @param name
     * @param a
     * @param b
     * @return
     */
    private RestResponse checkUpdateIsExist(String username,String name,boolean a,boolean b){
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        TAuUserExample example = new TAuUserExample();
        if(a){
            //用户账号变化：只要和已经有的name、username一样都不行
            example.or().andUsernameEqualTo(username);
            example.or().andNameEqualTo(username);
            List<TAuUser> userList = tAuUserMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(userList)){
                if(username.equalsIgnoreCase(userList.get(0).getUsername())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此用户名 ：'"+name+"'与以有账号重复");
                    messageList.add(map);
                }
                if(username.equalsIgnoreCase(userList.get(0).getName())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此账号 ：'"+username+"'与以有用户名称重复");
                    messageList.add(map);
                }
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("保存失败");
        }
        if(b){
            //用户名变化：只要和已经有的name、username一样都不行
            example.or().andUsernameEqualTo(name);
            example.or().andNameEqualTo(name);
            List<TAuUser> userList = tAuUserMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(userList)){
                if(name.equalsIgnoreCase(userList.get(0).getUsername())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此账号 ：'"+username+"'已存在");
                    messageList.add(map);
                }
                if(name.equalsIgnoreCase(userList.get(0).getName())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此用户名 ：'"+name+"'已存在");
                    messageList.add(map);
                }
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("保存失败");
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }



}
