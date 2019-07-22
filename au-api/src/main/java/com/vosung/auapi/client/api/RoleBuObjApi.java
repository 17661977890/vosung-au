package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequest;
import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequestDto;
import com.vosung.auapi.client.dto.requestdto.rolepermission.UserPermissionRequest;
import com.vosung.auapi.client.hystrix.RoleBuObjApiHystrix;
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
 * 角色授权接口
 * @Author 彬
 * @Date 2019/5/28
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = RoleBuObjApiHystrix.class)
public interface RoleBuObjApi {


    /**
     * 角色业务对象授权
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleBuObjController/saveRoleBuObj",method = RequestMethod.POST)
    RestResponse saveRoleBuObj(@RequestBody @Valid RestRequest<RoleBuObjRequestDto> restRequest);

    /**
     * 根据角色查已授权的业务对象（含权限项list）
     * @param roleId
     * @return
     */
    @RequestMapping(value = "api/roleBuObjController/getBuObjListByRoleId",method = RequestMethod.POST)
    RestResponse getBuObjListByRoleId(@RequestParam("roleId") Integer roleId);

    /**
     * 查所有业务对象list
     * @param roleId
     * @return
     */
    @RequestMapping(value = "api/roleBuObjController/getAllBuObjList",method = RequestMethod.POST)
    RestResponse getAllBuObjList(@RequestParam("roleId") Integer roleId);

    /**
     * 查询权限(角色的)
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleBuObjController/getBuObjPermissionListByRoleId",method = RequestMethod.POST)
    RestResponse getBuObjPermissionListByRoleId(@RequestBody @Valid RestRequest<RoleBuObjRequest> restRequest);


    /**
     * 查询用户权限列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleBuObjController/getUserPermissionByUserId",method = RequestMethod.POST)
    RestResponse getUserPermissionByUserId(@RequestBody @Valid RestRequest<UserPermissionRequest> restRequest);
}
