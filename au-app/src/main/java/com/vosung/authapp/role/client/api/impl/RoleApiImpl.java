package com.vosung.authapp.role.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.RoleApi;
import com.vosung.auapi.client.dto.requestdto.role.RoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.RoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.UpdateRoleStatusRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 角色api实现类
 * @Author 彬
 * @Date 2019/5/5
 */
@Slf4j
@RestController
public class RoleApiImpl extends BaseApiImpl implements RoleApi {
    @Autowired
    private RoleService roleService;

    /**
     * 保存角色
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveRole(@RequestBody @Valid RestRequest<RoleRequestDto> restRequest) {
        log.info("保存角色入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleService.saveRole(restRequest.getBody());
        log.info("保存角色出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据角色id 查看详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getRoleInfoById(@RequestParam("id") Integer id) {
        log.info("根据角色id 查看详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = roleService.getRoleInfoById(id);
        log.info("根据角色id 查看详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 角色列表查询
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getRoleList(@RequestBody RestRequest<RoleListRequestDto> restRequest) {
        log.info("查询角色列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleService.getRoleList(restRequest);
        log.info("查询角色列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除角色
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deleteRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest) {
        log.info("删除角色入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleService.deleteRole(restRequest.getBody());
        log.info("删除角色出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用角色状态
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest) {
        log.info("禁用角色状态入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleService.forbiddenRole(restRequest.getBody());
        log.info("禁用角色状态出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用角色状态
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest) {
        log.info("反禁用角色状态入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleService.unForbiddenRole(restRequest.getBody());
        log.info("反禁用角色状态出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
