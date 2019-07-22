package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequest;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequestDto;
import com.vosung.auapi.client.hystrix.PermissionObjectApiHystrix;
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
 * 权限对象接口api
 * @Author 彬
 * @Date 2019/5/6
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = PermissionObjectApiHystrix.class)
public interface PermissionObjectApi {

    /**
     * 保存权限对象
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/permissionObjectController/savePermissionObject",method = RequestMethod.POST)
    RestResponse savePermissionObject(@RequestBody @Valid RestRequest<PermissionObjectRequestDto> restRequest);

    /**
     * 根据id查看权限对象详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/permissionObjectController/getPermissionObjectInfoById",method = RequestMethod.POST)
    RestResponse getPermissionObjectInfoById(@RequestParam("id") Integer id);

    /**
     * 权限对象列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/permissionObjectController/getPermissionObjectList",method = RequestMethod.POST)
    RestResponse getPermissionObjectList(@RequestBody RestRequest<PermissionObjectRequest> restRequest);

    /**
     * 根据权限对象id
     * @param permissionObjectId
     * @return
     */
    @RequestMapping(value = "api/permissionObjectController/getPermissionItemListByPOId",method = RequestMethod.POST)
    RestResponse getPermissionItemListByPOId(@RequestParam("permissionObjectId") Integer permissionObjectId);

    /**
     * 删除权限对象，以及与权限项的关系数据，（如果绑定了业务对象，调接口解除与业务对象关系）
     * @param ids
     * @return
     */
    @RequestMapping(value = "api/permissionObjectController/deletePOAndRelatedPI",method = RequestMethod.POST)
    RestResponse deletePOAndRelatedPI(@RequestParam("ids") String ids);
}
