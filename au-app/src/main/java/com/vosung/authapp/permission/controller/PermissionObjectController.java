package com.vosung.authapp.permission.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.PermissionObjectApi;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequest;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 权限对象web层
 * @Author 彬
 * @Date 2019/5/6
 */
@Slf4j
@RequestMapping("auth/permissionObjectController")
@RestController
public class PermissionObjectController extends BaseController{
    @Autowired
    private PermissionObjectApi permissionObjectApi;

    /**
     * 保存权限对象
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/savePermissionObject",method = RequestMethod.POST)
    public RestResponse savePermissionObject(@RequestBody @Valid RestRequest<PermissionObjectRequestDto> restRequest) {
        log.info("保存权限对象入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectApi.savePermissionObject(restRequest);
        log.info("保存权限对象出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id 查询权限对象详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPermissionObjectInfoById",method = RequestMethod.POST)
    public RestResponse getPermissionObjectInfoById(@RequestParam("id") Integer id) {
        log.info("查询权限对象详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectApi.getPermissionObjectInfoById(id);
        log.info("查询权限对象详情出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 权限对象列表出参
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getPermissionObjectList",method = RequestMethod.POST)
    public RestResponse getPermissionObjectList(@RequestBody RestRequest<PermissionObjectRequest> restRequest) {
        log.info("查询权限对象列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectApi.getPermissionObjectList(restRequest);
        log.info("查询权限对象列表出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 根据权限对象id查询权限项列表
     * @param permissionObjectId
     * @return
     */
    @RequestMapping(value = "/getPermissionItemListByPOId",method = RequestMethod.POST)
    public RestResponse getPermissionItemListByPOId(Integer permissionObjectId) {
        log.info("根据权限对象id查询权限项列表入参 ："+ permissionObjectId);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectApi.getPermissionItemListByPOId(permissionObjectId);
        log.info("根据权限对象id查询权限项列表出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除权限对象，以及与权限项的关系数据，（如果绑定了业务对象，调接口解除与业务对象关系）
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deletePOAndRelatedPI",method = RequestMethod.POST)
    public RestResponse deletePOAndRelatedPI(String ids) {
        log.info("逻辑删除权限对象入参 ："+ ids);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectApi.deletePOAndRelatedPI(ids);
        log.info("逻辑删除权限对象出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
