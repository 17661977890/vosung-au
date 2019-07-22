package com.vosung.authapp.role.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.RoleResourceApi;
import com.vosung.auapi.client.dto.requestdto.roleresource.ButtonResourceRequestDto;
import com.vosung.auapi.client.dto.requestdto.roleresource.RoleResourceRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.role.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 角色资源授权api实现类
 * @Author 彬
 * @Date 2019/5/16
 */
@Slf4j
@RestController
public class RoleResourceApiImpl extends BaseApiImpl implements RoleResourceApi {
    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 批量保存 角色资源授权
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveRoleResource(@RequestBody @Valid RestRequest<RoleResourceRequestDto> restRequest) {
        log.info("角色资源授权入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceService.saveRoleResource(restRequest.getBody());
        log.info("角色资源授权出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看某个角色拥有菜单
     * @param roleId
     * @return
     */
    @Override
    public RestResponse getMenuResourceListByRoleId(Integer roleId) {
        log.info("查看角色拥有菜单入参 ："+ roleId);
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceService.getMenuResourceListByRoleId(roleId);
        log.info("查看角色拥有菜单出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查多个角色拥有菜单
     * @param roleIds
     * @return
     */
    @Override
    public RestResponse getMenuTreeByRoleIds(String roleIds) {
        log.info("查看多个角色拥有菜单入参 ："+ roleIds);
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceService.getMenuTreeByRoleIds(roleIds);
        log.info("查看多个角色拥有菜单出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看角色下菜单下按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getButtonResourceListByRoleIdMenuId(@RequestBody @Valid RestRequest<ButtonResourceRequestDto> restRequest) {
        log.info("查看(角色下有权|菜单下或父按钮下)按钮列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleResourceService.getButtonResourceListByRoleIdMenuId(restRequest.getBody());
        log.info("查看(角色下有权|菜单下或父按钮下)按钮列表出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
