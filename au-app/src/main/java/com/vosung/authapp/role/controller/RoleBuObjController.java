package com.vosung.authapp.role.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.RoleBuObjApi;
import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequest;
import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequestDto;
import com.vosung.auapi.client.dto.requestdto.rolepermission.UserPermissionRequest;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色业务对象授权web
 * @Author 彬
 * @Date 2019/5/28
 */
@Slf4j
@RequestMapping("auth/roleBuObjController")
@RestController
public class RoleBuObjController extends BaseController {
    @Autowired
    private RoleBuObjApi roleBuObjApi;

    /**
     * 角色业务对象授权
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveRoleBuObj",method = RequestMethod.POST)
    public RestResponse saveRoleBuObj(@RequestBody @Valid RestRequest<RoleBuObjRequestDto> restRequest) {
        log.info("角色业务对象授权入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getBody().getRoleId()==null || restRequest.getBody().getBusinessObjectList()==null){
            throw new BusinessException("3011111321",getMessage("3011111321"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleBuObjApi.saveRoleBuObj(restRequest);
        log.info("角色业务对象授权出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 根据角色查询授权的业务对象列表（含权限项list）
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getBuObjListByRoleId",method = RequestMethod.POST)
    public RestResponse getBuObjListByRoleId(@RequestParam("roleId") Integer roleId) {
        log.info("根据角色查询授权的业务对象列表 ："+ roleId);
        RestResponse restResponse = new RestResponse();
        restResponse = roleBuObjApi.getBuObjListByRoleId(roleId);
        log.info("根据角色查询授权的业务对象列表 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询所有已绑定权限对象的业务对象列表
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/getAllBuObjList",method = RequestMethod.POST)
    public RestResponse getAllBuObjList(@RequestParam("roleId") Integer roleId) {
        log.info("查询所有已绑定权限对象的业务对象列表入参 ："+ roleId);
        RestResponse restResponse = new RestResponse();
        restResponse = roleBuObjApi.getAllBuObjList(roleId);
        log.info("查询所有已绑定权限对象的业务对象列表出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看角色权限列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getBuObjPermissionListByRoleId",method = RequestMethod.POST)
    public RestResponse getBuObjPermissionListByRoleId(@RequestBody @Valid RestRequest<RoleBuObjRequest> restRequest) {
        log.info("查看角色权限列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleBuObjApi.getBuObjPermissionListByRoleId(restRequest);
        log.info("查看角色权限列表出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看用户权限列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getUserPermissionByUserId",method = RequestMethod.POST)
    public RestResponse getUserPermissionByUserId(@RequestBody @Valid RestRequest<UserPermissionRequest> restRequest) {
        log.info("查看用户权限列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = roleBuObjApi.getUserPermissionByUserId(restRequest);
        log.info("查看用户权限列表出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
