package com.vosung.authapp.role.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.RoleResourceApi;
import com.vosung.auapi.client.dto.requestdto.roleresource.ButtonResourceRequestDto;
import com.vosung.auapi.client.dto.requestdto.roleresource.RoleResourceRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色资源授权web
 * @Author 彬
 * @Date 2019/5/16
 */
@Slf4j
@RequestMapping("auth/roleResourceController")
@RestController
public class RoleResourceController extends BaseController {
    @Autowired
    private RoleResourceApi roleResourceApi;

    /**
     * 批量保存 角色资源授权
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveRoleResource",method = RequestMethod.POST)
    public RestResponse saveRoleResource(@RequestBody @Valid RestRequest<RoleResourceRequestDto> restRequest) {
        log.info("角色资源授权入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceApi.saveRoleResource(restRequest);
        log.info("角色资源授权出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看角色拥有菜单
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getMenuResourceListByRoleId",method = RequestMethod.POST)
    public RestResponse getMenuResourceListByRoleId(@RequestParam("roleId") Integer roleId) {
        log.info("查看角色拥有菜单入参 ："+ roleId);
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceApi.getMenuResourceListByRoleId(roleId);
        log.info("查看角色拥有菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查多个角色拥有菜单
     * @param roleIds
     * @return
     */
    @RequestMapping(value = "/getMenuTreeByRoleIds",method = RequestMethod.POST)
    public RestResponse getMenuTreeByRoleIds(@RequestParam("roleIds") String roleIds) {
        log.info("查看多个角色拥有菜单入参："+ roleIds);
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceApi.getMenuTreeByRoleIds(roleIds);
        log.info("查看多个角色拥有菜单出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 查看(角色下|菜单下或父按钮下)按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getButtonResourceListByRoleIdMenuId",method = RequestMethod.POST)
    public RestResponse getButtonResourceListByRoleIdMenuId(@RequestBody @Valid RestRequest<ButtonResourceRequestDto> restRequest) {
        log.info("查看(角色下|菜单下或父按钮下)菜单下按钮入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceApi.getButtonResourceListByRoleIdMenuId(restRequest);
        log.info("查看(角色下|菜单下或父按钮下)菜单下按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
