package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.roleresource.ButtonResourceRequestDto;
import com.vosung.auapi.client.dto.requestdto.roleresource.RoleResourceRequestDto;
import com.vosung.auapi.client.hystrix.RoleResourceApiHystrix;
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
 * 角色资源授权api
 * @Author 彬
 * @Date 2019/5/16
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = RoleResourceApiHystrix.class)
public interface RoleResourceApi {

    /**
     * 批量保存 角色资源授权
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleResourceController/saveRoleResource",method = RequestMethod.POST)
    RestResponse saveRoleResource(@RequestBody @Valid RestRequest<RoleResourceRequestDto> restRequest);

    /**
     * 获取角色拥有菜单
     * @param roleId
     * @return
     */
    @RequestMapping(value = "api/roleResourceController/getMenuResourceListByRoleId",method = RequestMethod.POST)
    RestResponse getMenuResourceListByRoleId(@RequestParam("roleId") Integer roleId);


    /**
     * 查询用户拥有的菜单（一个用户多个角色，查询拥有的菜单）
     * @param roleIds
     * @return
     */
    @RequestMapping(value = "api/roleResourceController/getMenuTreeByRoleIds",method = RequestMethod.POST)
    RestResponse getMenuTreeByRoleIds(@RequestParam("roleIds")String roleIds);

    /**
     * 根据角色id 菜单id 查询按钮列表（无角色id：有效 有角色：有权会标识）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleResourceController/getButtonResourceListByRoleIdMenuId",method = RequestMethod.POST)
    RestResponse getButtonResourceListByRoleIdMenuId(@RequestBody @Valid RestRequest<ButtonResourceRequestDto> restRequest);
}
