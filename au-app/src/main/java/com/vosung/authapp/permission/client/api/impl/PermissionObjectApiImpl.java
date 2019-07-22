package com.vosung.authapp.permission.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.PermissionObjectApi;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequest;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.permission.service.PermissionObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 权限对象api实现类
 * @Author 彬
 * @Date 2019/5/6
 */
@Slf4j
@RestController
public class PermissionObjectApiImpl extends BaseApiImpl implements PermissionObjectApi {
    @Autowired
    private PermissionObjectService permissionObjectService;

    /**
     * 保存权限对象
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse savePermissionObject(@RequestBody @Valid RestRequest<PermissionObjectRequestDto> restRequest) {
        log.info("保存权限对象入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectService.savePermissionObject(restRequest.getBody());
        log.info("保存权限对象出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id查询权限对象详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getPermissionObjectInfoById(@RequestParam("id") Integer id) {
        log.info("查询权限对象详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectService.getPermissionObjectInfoById(id);
        log.info("查询权限对象详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 权限对象列表出参
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getPermissionObjectList(@RequestBody RestRequest<PermissionObjectRequest> restRequest) {
        log.info("查询权限对象列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectService.getPermissionObjectList(restRequest);
        log.info("查询权限对象列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据权限对象id查询权限项列表
     * @param permissionObjectId
     * @return
     */
    @Override
    public RestResponse getPermissionItemListByPOId(Integer permissionObjectId) {
        log.info("根据权限对象id查询权限项列表入参 ："+ permissionObjectId);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectService.getPermissionItemListByPOId(permissionObjectId);
        log.info("根据权限对象id查询权限项列表出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除权限对象，以及与权限项的关系数据，（如果绑定了业务对象，调接口解除与业务对象关系）
     * @param ids
     * @return
     */
    @Override
    public RestResponse deletePOAndRelatedPI(String ids) {
        log.info("逻辑删除权限对象入参 ："+ ids);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionObjectService.deletePOAndRelatedPI(ids);
        log.info("逻辑删除权限对象出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
