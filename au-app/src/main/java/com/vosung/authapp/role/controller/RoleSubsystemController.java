package com.vosung.authapp.role.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.RoleSubsystemApi;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequest;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/6/24
 */
@Slf4j
@RequestMapping("auth/roleSubsystemController")
@RestController
public class RoleSubsystemController extends BaseController {
    @Autowired
    private RoleSubsystemApi roleSubsystemApi;

    /**
     * 子系统授权
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveRoleSubsystem",method = RequestMethod.POST)
    public RestResponse saveRoleSubsystem(@RequestBody RestRequest<RoleSubsystemRequestDto> restRequest) {
        log.info("角色子系统授权入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getBody().getRoleId()==null || restRequest.getBody().getSubsystemDtos()==null){
            throw new BusinessException("3011111321",getMessage("3011111321"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleSubsystemApi.saveRoleSubsystem(restRequest);
        log.info("角色子系统授权出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 根据角色查询授权的子系统列表
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getSubsystemPermissionListByRoleId",method = RequestMethod.POST)
    public RestResponse getSubsystemPermissionListByRoleId(@RequestParam("roleId") Integer roleId) {
        log.info("根据角色查询授权的子系统列表入参 ："+ roleId);
        RestResponse restResponse = new RestResponse();
        restResponse = roleSubsystemApi.getSubsystemPermissionListByRoleId(roleId);
        log.info("根据角色查询授权的子系统列表出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查所有子系统列表（含已授权的）
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getAllSubsystemList",method = RequestMethod.POST)
    public RestResponse getAllSubsystemList(@RequestParam("roleId") Integer roleId) {
        log.info("查所有子系统列表（含已授权的）入参 ："+ roleId);
        RestResponse restResponse = new RestResponse();
        restResponse = roleSubsystemApi.getAllSubsystemList(roleId);
        log.info("查所有子系统列表（含已授权的）出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看角色权限组列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getSubsystemPermissionReportByRoleId",method = RequestMethod.POST)
    public RestResponse getSubsystemPermissionReportByRoleId(@RequestBody @Valid RestRequest<RoleSubsystemRequest> restRequest) {
        log.info("查看角色权限组列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleSubsystemApi.getSubsystemPermissionReportByRoleId(restRequest);
        log.info("查看角色权限组列表出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

}
