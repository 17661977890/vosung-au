package com.vosung.authapp.role.service;

import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequest;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import io.swagger.models.auth.In;

/**
 * 子系统授权业务层
 * @Author 彬
 * @Date 2019/6/18
 */
public interface RoleSubsystemService {

    /**
     * 子系统授权
     * @param roleSubsystemRequestDto
     * @return
     */
    RestResponse saveRoleSubsystem(RoleSubsystemRequestDto roleSubsystemRequestDto);

    /**
     * 根据角色获取子系统授权列表
     * @param roleId
     * @return
     */
    RestResponse getSubsystemPermissionListByRoleId(Integer roleId);

    /**
     * 获取全部子系统（含已授权的）
     * @param roleId
     * @return
     */
    RestResponse getAllSubsystemList(Integer roleId);

    /**
     * 查询权限（角色的--子系统）
     * @param restRequest
     * @return
     */
    RestResponse getSubsystemPermissionReportByRoleId(RestRequest<RoleSubsystemRequest> restRequest);

}
