package com.vosung.authapp.org.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserRoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.OrgUserRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.UserRoleDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.orguserrole.*;
import com.vosung.auapi.client.dto.responsedto.user.UserResponseDto;
import com.vosung.auapi.client.entity.TAuRole;
import com.vosung.auapi.client.entity.TAuUser;
import com.vosung.auapi.client.entity.TAuUserOrgRole;
import com.vosung.auapi.client.entity.TAuUserOrgRoleExample;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.FieldMessageCls;
import com.vosung.authapp.common.constant.UserHolder;
import com.vosung.authapp.org.service.OrgUserRoleService;
import com.vosung.authapp.role.mapper.TAuRoleMapper;
import com.vosung.authapp.user.mapper.TAuUserMapper;
import com.vosung.authapp.user.mapper.TAuUserOrgRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 组织用户维护业务层实现类
 * @Author 彬
 * @Date 2019/4/30
 */
@Slf4j
@Service
public class OrgUserRoleServiceImpl extends BaseServiceImpl implements OrgUserRoleService{
    @Autowired
    private TAuUserOrgRoleMapper tAuUserOrgRoleMapper;
    @Autowired
    private TAuUserMapper tAuUserMapper;
    @Autowired
    private TAuRoleMapper tAuRoleMapper;

    @Autowired
    private UserHolder userHolder;
    @Autowired
    private TAuUserOrgRoleMapper userOrgRoleMapper;

    /**
     * 根据组织id 查用户列表（用户中含有角色列表）
     * @param getOrgUserRequestDto
     * @return
     */
    @Override
    public RestResponse getUserHaveRoleListByOrgId(GetUserRoleListRequestDto getOrgUserRequestDto) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<UserRoleResponseDto> userRoleResponseDtos = new ArrayList<>();
        try {
            //展示用户列表
            TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
            TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
            criteria.andOrgIdEqualTo(getOrgUserRequestDto.getOrgId());
            List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
            List<Integer> userIds = new ArrayList<>();
            //userId去重
            for (TAuUserOrgRole t:tAuUserOrgRoleList) {
                if (!userIds.contains(t.getUserId())) {
                    userIds.add(t.getUserId());
                }
            }
            for (Integer userId:userIds) {
                UserRoleResponseDto userRoleResponseDto = new UserRoleResponseDto();
                TAuUser tAuUser = tAuUserMapper.selectByPrimaryKey(userId);

                    BeanUtils.copyProperties(userRoleResponseDto,tAuUser);

                userRoleResponseDtos.add(userRoleResponseDto);
            }
            for(UserRoleResponseDto userRoleResponseDto:userRoleResponseDtos){
                GetUserRoleListRequestDto getUserRoleListRequestDto =new GetUserRoleListRequestDto();
                getUserRoleListRequestDto.setUserId(userRoleResponseDto.getId());
                getUserRoleListRequestDto.setOrgId(getOrgUserRequestDto.getOrgId());
                RestResponse restResponse = getRoleListByOrgUserId(getUserRoleListRequestDto);
                OrgUserRoleResponseDto orgUserRoleResponseDto = (OrgUserRoleResponseDto)restResponse.getBody();
                if(orgUserRoleResponseDto.getRoleList() == null){
                    orgUserRoleResponseDto.setRoleList(new ArrayList<>());
                }
                userRoleResponseDto.setList(orgUserRoleResponseDto.getRoleList());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("根据组织查询对应的用户列表（含角色列表）成功");
        return RestResponse.resultSuccess(userRoleResponseDtos,restResponseHeader);
    }

    /**
     * 根据组织和用户查询角色列表
     * @param getUserRoleListRequestDto
     * @return
     */
    @Override
    public RestResponse getRoleListByOrgUserId(GetUserRoleListRequestDto getUserRoleListRequestDto) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        OrgUserRoleResponseDto responseDto = new OrgUserRoleResponseDto();
        List<Integer> roleIds = new ArrayList<>();
        List<TAuRole> roleList = new ArrayList<>();
        //展示角色列表
        TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
        TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andOrgIdEqualTo(getUserRoleListRequestDto.getOrgId());
        criteria.andUserIdEqualTo(getUserRoleListRequestDto.getUserId());
        List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
        //一般不会有重复数据，避免bug或者脏数据多过滤一波
        for (TAuUserOrgRole t:tAuUserOrgRoleList) {
            if (t.getRoleId()!=null && !roleIds.contains(t.getRoleId())) {
                roleIds.add(t.getRoleId());
            }
        }
        for (Integer roleId:roleIds) {
            TAuRole tAuRole = tAuRoleMapper.selectByPrimaryKey(roleId);
            roleList.add(tAuRole);
        }
        responseDto.setRoleList(roleList);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("根据用户和组织id查询角色列表成功");
        return RestResponse.resultSuccess(responseDto,restResponseHeader);
    }

    /**
     * 用户组织维护保存
     * @param orgUserRequestDto
     * @return
     */
    @Override
    public RestResponse saveOrgUserRole(OrgUserRequestDto orgUserRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        CountDto countDto = new CountDto();
        //根据组织id查，之前有没有数据
        TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
        TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andOrgIdEqualTo(orgUserRequestDto.getOrgId());
        List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
        Integer record=0;
        Integer deleteCount=0;
        if(CollectionUtils.isEmpty(tAuUserOrgRoleList)){
            //之前没有数据
            if(!CollectionUtils.isEmpty(orgUserRequestDto.getUserRoleDtoList())){
                //如果传参用户和角色的对应list不为空，即新增数据
                for (UserRoleDto userRoleDto:orgUserRequestDto.getUserRoleDtoList()) {
                    for (Integer roleId:userRoleDto.getRoleIds()) {
                        TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                        tAuUserOrgRole.setOrgId(orgUserRequestDto.getOrgId());
                        tAuUserOrgRole.setUserId(userRoleDto.getUserId());
                        tAuUserOrgRole.setRoleId(roleId);
                        tAuUserOrgRole.setCreateTime(new Date());
                        tAuUserOrgRole.setCreateUser(loginUserId);
                        tAuUserOrgRole.setUpdateTime(new Date());
                        tAuUserOrgRole.setUpdateUser(loginUserId);
                        tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                        record++;
                    }
                }
                log.info("============新增组织用户维护关系数量："+record);
            }
            restResponseHeader.setMessage("保存组织用户维护关系成功---新增操作");
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
        }else {
            //之前有数据
            if(CollectionUtils.isEmpty(orgUserRequestDto.getUserRoleDtoList()) && orgUserRequestDto.getOrgId()!=null){
                //传参为空，即删除所有之前数据
                deleteCount = tAuUserOrgRoleMapper.deleteByExample(example);
                log.info("传参用户和角色对应关系全为空，删除组织id为："+orgUserRequestDto.getOrgId()+" 组织维护关系数量："+deleteCount);
                restResponseHeader.setMessage("保存组织用户维护关系成功---删除操作");
                restResponseHeader.setCode(ConstantUtil.SUCCESS);
            }else if(!CollectionUtils.isEmpty(orgUserRequestDto.getUserRoleDtoList()) && orgUserRequestDto.getOrgId()!=null){
                List<Integer> userIds = new ArrayList<>();
                Map<Integer,List<Integer>> map = new HashMap();
                for (UserRoleDto userRoleDto: orgUserRequestDto.getUserRoleDtoList()) {
                    if(!userIds.contains(userRoleDto.getUserId())){
                        userIds.add(userRoleDto.getUserId());
                        map.put(userRoleDto.getUserId(),userRoleDto.getRoleIds());
                        log.info("传参中用户与角色的对应关系："+ JSON.toJSONString(map));
                    }

                }
                //传参不为空,查关联用户list变化
                GetUserRoleListRequestDto getUserRoleListRequestDto = new GetUserRoleListRequestDto();
                getUserRoleListRequestDto.setOrgId(orgUserRequestDto.getOrgId());
                RestResponse restResponse = getUserRoleListByOrgId(getUserRoleListRequestDto);
                OrgUserRoleResponseDto responseDto = (OrgUserRoleResponseDto)restResponse.getBody();
                List<TAuUser> userList = responseDto.getUserList();
                List<Integer> userIdList = new ArrayList<>();
                for (TAuUser tAuUser: userList) {
                    userIdList.add(tAuUser.getId());
                }
                //先看传参中用户对应原有用户
                for (TAuUser tAuUser: userList) {
                    if(userIds.contains(tAuUser.getId())){
                        //传参用户id，之前数据也有，再看角色变化
                        getUserRoleListRequestDto.setUserId(tAuUser.getId());
                        restResponse = getRoleListByOrgUserId(getUserRoleListRequestDto);
                        responseDto = (OrgUserRoleResponseDto)restResponse.getBody();
                        List<TAuRole> roleList = responseDto.getRoleList();
                        List<Integer> roleIdList = new ArrayList<>();
                        for (TAuRole tAuRole: roleList) {
                            roleIdList.add(tAuRole.getId());
                        }
                        //开始角色比较
                        for (TAuRole tAuRole: roleList) {
                            if(!(map.get(tAuUser.getId())).contains(tAuRole.getId())){
                                //传参用户下角色id，之前有，不操作
                                //之前有，但是传参没有。
                                criteria.andUserIdEqualTo(tAuUser.getId());
                                criteria.andRoleIdEqualTo(tAuRole.getId());
                                deleteCount = tAuUserOrgRoleMapper.deleteByExample(example);
                                log.info("删除组织id为："+orgUserRequestDto.getOrgId()+" 用户id为："+tAuUser.getId()+"角色id为："+tAuRole.getId()+" 的组织维护关系数量："+deleteCount);
                            }
                        }
                        for (Integer roleId:map.get(tAuUser.getId())) {
                            if(!roleIdList.contains(roleId)){
                                //传参多与原有，新增
                                TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                                tAuUserOrgRole.setOrgId(orgUserRequestDto.getOrgId());
                                tAuUserOrgRole.setUserId(tAuUser.getId());
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
                        criteria.andUserIdEqualTo(tAuUser.getId());
                        deleteCount = tAuUserOrgRoleMapper.deleteByExample(example);
                        log.info("删除组织id为："+orgUserRequestDto.getOrgId()+" 用户id为："+tAuUser.getId()+" 的组织维护关系数量："+deleteCount);
                    }
                }
                //再看原有用户的对应传参用户
                for (Integer userId: userIds) {
                    if (!userIdList.contains(userId)){
                        //传参在原有中不存在，新增数据
                        if(CollectionUtils.isEmpty(map.get(userId))){
                            //对应用户角色为空---保存用户组织关系
                            TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                            tAuUserOrgRole.setOrgId(orgUserRequestDto.getOrgId());
                            tAuUserOrgRole.setUserId(userId);
                            tAuUserOrgRole.setCreateTime(new Date());
                            tAuUserOrgRole.setCreateUser(loginUserId);
                            tAuUserOrgRole.setUpdateTime(new Date());
                            tAuUserOrgRole.setUpdateUser(loginUserId);
                            tAuUserOrgRoleMapper.insertSelective(tAuUserOrgRole);
                            record++;
                            log.info("对应用户角色,用户id："+userId+"之前不存在，新增组织用户关系");
                        }else {
                            //对应用户角色list存在
                            for (Integer roleId:map.get(userId)) {
                                TAuUserOrgRole tAuUserOrgRole = new TAuUserOrgRole();
                                tAuUserOrgRole.setOrgId(orgUserRequestDto.getOrgId());
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
                restResponseHeader.setMessage("保存组织用户维护关系成功---修改调整操作");
                restResponseHeader.setCode(ConstantUtil.SUCCESS);
            }
        }
        countDto.setAddCount(record);
        countDto.setDeleteCount(deleteCount);
        return RestResponse.resultSuccess(countDto,restResponseHeader);
    }

    /**
     * 查询用户（授权处）
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getUserListBy(RestRequest<GetUserListRequestDto> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        UserListResponse userResponse = new UserListResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(UserListDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            List<UserListDto> userListDtos = userOrgRoleMapper.getUserListBy(restRequest.getBody());
            userResponse.setColumnList(viewColumnList);
            userResponse.setPageInfo(new PageInfo<>(userListDtos));
            userResponse.setSum((int) new PageInfo<>(userListDtos).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询用户列表成功 ");
        return RestResponse.resultSuccess(userResponse,restResponseHeader);
    }

    /**
     * 根据组织id查用户角色列表------用户列表和角色列表分开的（用户组织维护保存调用此方法）
     * @param getOrgUserRequestDto
     * @return
     */
    public RestResponse getUserRoleListByOrgId(GetUserRoleListRequestDto getOrgUserRequestDto) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        OrgUserRoleResponseDto responseDto = new OrgUserRoleResponseDto();
        //展示用户列表
        TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
        TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
        criteria.andOrgIdEqualTo(getOrgUserRequestDto.getOrgId());
        List<TAuUserOrgRole> tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
        List<Integer> userIds = new ArrayList<>();
        List<Integer> roleIds = new ArrayList<>();
        List<TAuUser> userList = new ArrayList<>();
        List<TAuRole> roleList = new ArrayList<>();
        //userId去重
        for (TAuUserOrgRole t:tAuUserOrgRoleList) {
            if (!userIds.contains(t.getUserId())) {
                userIds.add(t.getUserId());
            }
        }
        for (Integer userId:userIds) {
            TAuUser tAuUser = tAuUserMapper.selectByPrimaryKey(userId);
            userList.add(tAuUser);
        }
        responseDto.setUserList(userList);
        //展示用户组织 对应的角色列表:默认展示第一个用户的角色
        if(!CollectionUtils.isEmpty(userList)){
            criteria.andOrgIdEqualTo(getOrgUserRequestDto.getOrgId());
            criteria.andUserIdEqualTo(userList.get(0).getId());
            tAuUserOrgRoleList = tAuUserOrgRoleMapper.selectByExample(example);
        }
        for (TAuUserOrgRole t:tAuUserOrgRoleList) {
            if (!roleIds.contains(t.getRoleId())) {
                roleIds.add(t.getRoleId());
            }
        }
        for (Integer roleId:roleIds) {
            TAuRole tAuRole = tAuRoleMapper.selectByPrimaryKey(roleId);
            roleList.add(tAuRole);
        }
        responseDto.setRoleList(roleList);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询组织下的用户角色列表成功");
        return RestResponse.resultSuccess(responseDto,restResponseHeader);
    }


}
