package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemListRequest;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemRequestDto;
import com.vosung.auapi.client.hystrix.PermissionItemApiHystrix;
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
 * 权限项接口api
 * @Author 彬
 * @Date 2019/5/6
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = PermissionItemApiHystrix.class)
public interface PermissionItemApi {

    /**
     * 保存权限项（新增/修改）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/permissionItemController/savePermissionItem",method = RequestMethod.POST)
    RestResponse savePermissionItem(@RequestBody @Valid RestRequest<PermissionItemRequestDto> restRequest);

    /**
     * 根据id查看权限项详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/permissionItemController/getPermissionItemInfoById",method = RequestMethod.POST)
    RestResponse getPermissionItemInfoById(@RequestParam("id") Integer id);

    /**
     * 查询权限项列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/permissionItemController/getPermissionItemList",method = RequestMethod.POST)
    RestResponse getPermissionItemList(@RequestBody RestRequest<PermissionItemListRequest> restRequest);

    /**
     * 逻辑删除权限项
     * @param itemIds
     * @return
     */
    @RequestMapping(value = "api/permissionItemController/deletePermissionItem",method = RequestMethod.POST)
    RestResponse deletePermissionItem(@RequestParam("itemIds") String itemIds);
}
