package com.vosung.authapp.role.service;

import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequest;
import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequestDto;
import com.vosung.auapi.client.dto.requestdto.rolepermission.UserPermissionRequest;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

/**
 * 角色业务对象授权
 * @Author 彬
 * @Date 2019/5/28
 */
public interface RoleBuObjService {

    /**
     * 角色业务对象授权--------------------会影响子系统授权情况
     * @param roleBuObjRequestDto
     * @return
     */
    RestResponse saveRoleBuObj(RoleBuObjRequestDto roleBuObjRequestDto);

    /**
     * 根据角色id 查询业务对象授权list(有权业务对象包含权限项list)
     * @param roleId
     * @return
     */
    RestResponse getBuObjListByRoleId(Integer roleId);

    /**
     * 获取全部的业务对象（已绑定权限对象）----角色授权过的为已设置，并查授权表得权限项id，状态
     * @param roleId
     * @return
     */
    RestResponse getAllBuObjList(Integer roleId);

    /**
     * 查询权限(角色的)
     * @param restRequest
     * @return
     */
    RestResponse getBuObjPermissionListByRoleId(RestRequest<RoleBuObjRequest> restRequest);

    /**
     * 查询用户权限列表
     * @param restRequest
     * @return
     */
    RestResponse getUserPermissionByUserId(RestRequest<UserPermissionRequest> restRequest);
}
