package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequest;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequestDto;
import com.vosung.auapi.client.hystrix.RoleSubsystemApiHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 子系统授权api
 * @Author 彬
 * @Date 2019/6/24
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = RoleSubsystemApiHystrix.class)
public interface RoleSubsystemApi {
    /**
     * 子系统授权api
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleSubsystemController/saveRoleSubsystem",method = RequestMethod.POST)
    RestResponse saveRoleSubsystem(@RequestBody RestRequest<RoleSubsystemRequestDto> restRequest);

    /**
     * 根据角色获取子系统授权列表
     * @param roleId
     * @return
     */
    @RequestMapping(value = "api/roleSubsystemController/getSubsystemPermissionListByRoleId",method = RequestMethod.POST)
    RestResponse getSubsystemPermissionListByRoleId(@RequestParam("roleId") Integer roleId);

    /**
     * 获取全部子系统（含已授权的）
     * @param roleId
     * @return
     */
    @RequestMapping(value = "api/roleSubsystemController/getAllSubsystemList",method = RequestMethod.POST)
    RestResponse getAllSubsystemList(@RequestParam("roleId") Integer roleId);

    /**
     * 查询权限（角色的--子系统）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleSubsystemController/getSubsystemPermissionReportByRoleId",method = RequestMethod.POST)
    RestResponse getSubsystemPermissionReportByRoleId(@RequestBody @Valid RestRequest<RoleSubsystemRequest> restRequest);

}
