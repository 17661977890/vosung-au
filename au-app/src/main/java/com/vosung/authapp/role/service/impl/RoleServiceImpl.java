package com.vosung.authapp.role.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.role.RoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.RoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.UpdateRoleStatusRequestDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.role.RoleResponse;
import com.vosung.auapi.client.dto.responsedto.role.RoleResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.FieldMessageCls;
import com.vosung.authapp.common.constant.RedisUtil;
import com.vosung.authapp.common.constant.UserHolder;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.role.mapper.TAuRoleMapper;
import com.vosung.authapp.role.mapper.TAuRolePermissionObjectMapper;
import com.vosung.authapp.role.mapper.TAuSubsystemPermissionMapper;
import com.vosung.authapp.role.service.RoleService;
import com.vosung.authapp.user.mapper.TAuUserMapper;
import com.vosung.authapp.user.mapper.TAuUserOrgRoleMapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 角色管理业务实现类
 * @Author 彬
 * @Date 2019/5/5
 */
@Slf4j
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{

    @Autowired
    private TAuRoleMapper tAuRoleMapper;
    @Autowired
    private TAuUserOrgRoleMapper tAuUserOrgRoleMapper;
    @Autowired
    private TAuUserMapper tAuUserMapper;
    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private TAuRolePermissionObjectMapper rolePermissionObjectMapper;
    @Autowired
    private TAuSubsystemPermissionMapper subsystemPermissionMapper;

    /**
     * 保存角色
     * @param roleRequestDto
     * @return
     */
    @Override
    public RestResponse saveRole(RoleRequestDto roleRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuRole tAuRole = new TAuRole();
        Integer record = 0;
        try {
            BeanUtils.copyProperties(tAuRole,roleRequestDto);
            if(roleRequestDto.getId() == null){
                //新增角色 判重编码和名称
                RestResponse restResponse = checkAddIsExist(roleRequestDto.getRoleCode(),roleRequestDto.getRoleName());
                List<Map> messageList = (List<Map>) restResponse.getBody();
                if(!CollectionUtils.isEmpty(messageList)){
                    return restResponse;
                }
                tAuRole.setCreateOrgId(1);
                tAuRole.setCreateUser(loginUserId);
                tAuRole.setCreateTime(new Date());
                tAuRole.setUpdateTime(new Date());
                tAuRole.setUpdateUser(loginUserId);
                //新增返回主键id
                record = tAuRoleMapper.insertSelective(tAuRole);
            }else {
                //修改角色 改变进行判重
                TAuRole tAuRole1 = tAuRoleMapper.selectByPrimaryKey(roleRequestDto.getId());
                if(tAuRole1 == null){
                    throw new BusinessException("3011111171",getMessage("3011111171"));
                }
                Boolean a = !tAuRole1.getRoleCode().equalsIgnoreCase(roleRequestDto.getRoleCode());
                Boolean b = !tAuRole1.getRoleName().equalsIgnoreCase(roleRequestDto.getRoleName());
                if(a || b){
                    //只要修改编码或者名称，就判重
                    RestResponse restResponse = checkUpdateIsExist(roleRequestDto.getRoleCode(),roleRequestDto.getRoleName(),a,b);
                    List<Map> messageList = (List<Map>) restResponse.getBody();
                    if(!CollectionUtils.isEmpty(messageList)){
                        return restResponse;
                    }
                }
                tAuRole.setUpdateTime(new Date());
                tAuRole.setUpdateUser(1);
                record = tAuRoleMapper.updateByPrimaryKeySelective(tAuRole);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存角色信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据角色id查看详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getRoleInfoById(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        TAuRole tAuRole = tAuRoleMapper.selectByPrimaryKey(id);
        try {
            BeanUtils.copyProperties(roleResponseDto,tAuRole);
            roleResponseDto.setRoleTypeName((String) redisUtil.hget(SysDictEnum.ROLE_TYPE.getCode(),roleResponseDto.getRoleType()));
            roleResponseDto.setPropertyName((String) redisUtil.hget(SysDictEnum.ROLE_ATTRIBUTE.getCode(),roleResponseDto.getProperty()));
            if(roleResponseDto.getUseOrgId()!=null){
                TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(roleResponseDto.getUseOrgId());
                if(tAuOrganization!=null){
                    roleResponseDto.setUseOrgName(tAuOrganization.getOrgName());
                }
            }
            TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
            TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(id);
            List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
            List<Integer> userIds = new ArrayList<>();
            List<TAuUser> userList = new ArrayList<>();
            for (TAuUserOrgRole t:tAuUserOrgRoleList) {
                //userId去重（重复情况：同一用户不同组织同一角色）
                if(!userIds.contains(t.getUserId())){
                    userIds.add(t.getUserId());
                }
            }
            for (Integer userId:userIds) {
                TAuUser tAuUser = tAuUserMapper.selectByPrimaryKey(userId);
                userList.add(tAuUser);
            }
            roleResponseDto.setUserList(userList);
            restResponseHeader.setMessage("查询角色详情成功");
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResponse.resultSuccess(roleResponseDto,restResponseHeader);
    }

    /**
     * 角色列表查询
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getRoleList(RestRequest<RoleListRequestDto> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        RoleResponse roleResponse = new RoleResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(RoleResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            TAuRoleExample example = new TAuRoleExample();
            TAuRoleExample.Criteria criteria = example.createCriteria();
            //显示未删除的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            if(restRequest.getBody().getProhibitState()!=null){
                criteria.andProhibitStateEqualTo(restRequest.getBody().getProhibitState());
            }
            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuRole> tAuRoleList =  tAuRoleMapper.selectByExample(example);
            List<RoleResponseDto> roleResponseDtos = new ArrayList<>();

            for (TAuRole tAuRole: tAuRoleList) {
                RoleResponseDto roleResponseDto =new RoleResponseDto();
                BeanUtils.copyProperties(roleResponseDto,tAuRole);
                roleResponseDto.setRoleTypeName((String) redisUtil.hget(SysDictEnum.ROLE_TYPE.getCode(),roleResponseDto.getRoleType()));
                roleResponseDto.setPropertyName((String) redisUtil.hget(SysDictEnum.ROLE_ATTRIBUTE.getCode(),roleResponseDto.getProperty()));
                if(roleResponseDto.getUseOrgId()!=null){
                    TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(roleResponseDto.getUseOrgId());
                    if(tAuOrganization!=null){
                        roleResponseDto.setUseOrgName(tAuOrganization.getOrgName());
                    }
                }
                roleResponseDtos.add(roleResponseDto);
            }
            Integer sum = tAuRoleMapper.countByExample(example);
            roleResponse.setColumnList(viewColumnList);
            roleResponse.setPageInfo(new PageInfo<>(roleResponseDtos));
            roleResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询角色列表成功 ");
        return RestResponse.resultSuccess(roleResponse,restResponseHeader);
    }

    /**
     * 修改角色状态（禁用/反禁/删除）
     * @param updateRoleStatusRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse deleteRole(UpdateRoleStatusRequestDto updateRoleStatusRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] roleIds = updateRoleStatusRequestDto.getRoleIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<roleIds.length;i++){
            Map map = new HashMap();
            TAuRole tAuRole = tAuRoleMapper.selectByPrimaryKey(Integer.valueOf(roleIds[i]));
            //删除
            if(ConstantUtil.YES_OR_NO_N.equals(tAuRole.getIsDelete())){
                tAuRole.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuRole.setUpdateTime(new Date());
                tAuRole.setUpdateUser(loginUserId);
                tAuRoleMapper.updateByPrimaryKeySelective(tAuRole);
                map.put("message","角色编码为："+tAuRole.getRoleCode()+" 删除成功");
                map.put("code",ConstantUtil.SUCCESS);
                //todo 同时删除组织用户角色关系表数据
                TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
                TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
                criteria.andRoleIdEqualTo(Integer.valueOf(roleIds[i]));
                Integer count = tAuUserOrgRoleMapper.deleteByExample(example);
                log.info("删除角色id："+Integer.valueOf(roleIds[i])+"同时，删除用户组织角色关系数据数量："+count);
                //todo 删除授权表角色得相关数据（业务对象授权表/子系统授权表）
                TAuRolePermissionObjectExample example1 = new TAuRolePermissionObjectExample();
                TAuRolePermissionObjectExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andRoleIdEqualTo(Integer.valueOf(roleIds[i]));
                Integer count1  = rolePermissionObjectMapper.deleteByExample(example1);
                log.info("删除角色id："+Integer.valueOf(roleIds[i])+"同时，删除业务对象授权数据数量："+count1);
                TAuSubsystemPermissionExample example2 = new TAuSubsystemPermissionExample();
                TAuSubsystemPermissionExample.Criteria criteria2 = example2.createCriteria();
                criteria2.andRoleIdEqualTo(Integer.valueOf(roleIds[i]));
                Integer count2 = subsystemPermissionMapper.deleteByExample(example2);
                log.info("删除角色id："+Integer.valueOf(roleIds[i])+"同时，删除子系统授权数据数量："+count2);

            }
            messageList.add(map);

        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 修改角色状态（反禁）
     * @param updateRoleStatusRequestDto
     * @return
     */
    @Override
    public RestResponse unForbiddenRole(UpdateRoleStatusRequestDto updateRoleStatusRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] roleIds = updateRoleStatusRequestDto.getRoleIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<roleIds.length;i++){
            Map map = new HashMap();
            TAuRole tAuRole = tAuRoleMapper.selectByPrimaryKey(Integer.valueOf(roleIds[i]));
            //反禁用
            if(ConstantUtil.YES_OR_NO_Y.equals(tAuRole.getProhibitState())){
                tAuRole.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuRole.setUpdateTime(new Date());
                tAuRole.setUpdateUser(loginUserId);
                tAuRoleMapper.updateByPrimaryKeySelective(tAuRole);
                map.put("message","角色编码为："+tAuRole.getRoleCode()+" 反禁用成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                map.put("message","角色编码为："+tAuRole.getRoleCode()+" 已处于反禁用状态");
                map.put("code",ConstantUtil.ERROR);
            }
            messageList.add(map);

        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 修改角色状态（禁用）
     * @param updateRoleStatusRequestDto
     * @return
     */
    @Override
    public RestResponse forbiddenRole(UpdateRoleStatusRequestDto updateRoleStatusRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] roleIds = updateRoleStatusRequestDto.getRoleIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<roleIds.length;i++){
            Map map = new HashMap();
            TAuRole tAuRole = tAuRoleMapper.selectByPrimaryKey(Integer.valueOf(roleIds[i]));
            //禁用
            if(ConstantUtil.YES_OR_NO_N.equals(tAuRole.getProhibitState())){
                tAuRole.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuRole.setProhibitUser(loginUserId);
                tAuRole.setProhibitTime(new Date());
                tAuRole.setUpdateTime(new Date());
                tAuRole.setUpdateUser(loginUserId);
                tAuRoleMapper.updateByPrimaryKeySelective(tAuRole);
                map.put("message","角色编码为："+tAuRole.getRoleCode()+" 的角色禁用成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                map.put("message","角色编码为："+tAuRole.getRoleCode()+" 的角色已被禁用");
                map.put("code",ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 角色新增判重
     * @param roleCode
     * @param roleName
     * @return
     */
    private RestResponse checkAddIsExist(String roleCode,String roleName){
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuRoleExample example = new TAuRoleExample();
        TAuRoleExample.Criteria criteria =example.createCriteria();
        criteria.andRoleCodeEqualTo(roleCode);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        TAuRoleExample.Criteria criteria2 =example.createCriteria();
        criteria2.andRoleNameEqualTo(roleName);
        criteria2.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        example.or(criteria2);
        List<TAuRole> roleList = tAuRoleMapper.selectByExample(example);
        List<Map> messageList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)){
            if(roleCode.equalsIgnoreCase(roleList.get(0).getRoleCode())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此角色编码：'"+roleCode+"'已被使用");
                messageList.add(map);
            }
            if(roleName.equalsIgnoreCase(roleList.get(0).getRoleName())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此角色名称：'"+roleName+"'已被使用");
                messageList.add(map);
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("新增失败");
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 角色修改判重
     * @param roleCode
     * @param roleName
     * @param a
     * @param b
     * @return
     */
    private RestResponse checkUpdateIsExist(String roleCode,String roleName,boolean a,boolean b){
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        TAuRoleExample example = new TAuRoleExample();
        if(a){
            //角色编码变化
            TAuRoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleCodeEqualTo(roleCode);
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            List<TAuRole> roleList = tAuRoleMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(roleList)){
                if(roleCode.equalsIgnoreCase(roleList.get(0).getRoleCode())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此角色编码 ：'"+roleCode+"'与被使用");
                    messageList.add(map);
                }
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("保存失败");
        }
        if(b){
            //角色名变化
            TAuRoleExample.Criteria criteria = example.createCriteria();
            criteria.andRoleNameEqualTo(roleName);
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            List<TAuRole> roleList = tAuRoleMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(roleList)){
                if(roleName.equalsIgnoreCase(roleList.get(0).getRoleName())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此角色名 ：'"+roleName+"'已被使用");
                    messageList.add(map);
                }
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("保存失败");
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

}
