package com.vosung.authapp.permission.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.PermissionItemApi;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemListRequest;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 权限项web层
 * @Author 彬
 * @Date 2019/5/6
 */
@Slf4j
@RequestMapping("auth/permissionItemController")
@RestController
public class PermissionItemController extends BaseController{
    @Autowired
    private PermissionItemApi permissionItemApi;

    /**
     * 保存权限项
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/savePermissionItem",method = RequestMethod.POST)
    public RestResponse savePermissionItem(@RequestBody @Valid RestRequest<PermissionItemRequestDto> restRequest) {
        log.info("保存权限项入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = permissionItemApi.savePermissionItem(restRequest);
        log.info("保存权限项出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看权限项详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPermissionItemInfoById",method = RequestMethod.POST)
    public RestResponse getPermissionItemInfoById(@RequestParam("id") Integer id) {
        log.info("查看权限项详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionItemApi.getPermissionItemInfoById(id);
        log.info("查看权限项详情出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询权限项列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getPermissionItemList",method = RequestMethod.POST)
    public RestResponse getPermissionItemList(@RequestBody RestRequest<PermissionItemListRequest> restRequest) {
        log.info("查询权限项列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = permissionItemApi.getPermissionItemList(restRequest);
        log.info("查询权限项列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除权限项
     * @param itemIds
     * @return
     */
    @RequestMapping(value = "/deletePermissionItem",method = RequestMethod.POST)
    public RestResponse deletePermissionItem(@RequestParam("itemIds") String itemIds) {
        log.info("删除权限项入参 ："+ itemIds);
        RestResponse restResponse = new RestResponse();
        restResponse = permissionItemApi.deletePermissionItem(itemIds);
        log.info("删除权限项出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
