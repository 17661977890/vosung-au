package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.PermissionItemApi;
import com.vosung.auapi.client.api.PermissionObjectApi;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequest;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 权限对象熔断处理
 * @Author 彬
 * @Date 2019/5/6
 */
@Component
public class PermissionObjectApiHystrix extends BaseHystrix implements PermissionObjectApi{
    @Override
    public RestResponse savePermissionObject(@RequestBody @Valid RestRequest<PermissionObjectRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getPermissionObjectInfoById(@RequestParam("id") Integer id) {
        return getError();
    }

    @Override
    public RestResponse getPermissionObjectList(RestRequest<PermissionObjectRequest> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getPermissionItemListByPOId(@RequestParam("permissionObjectId") Integer permissionObjectId) {
        return getError();
    }

    @Override
    public RestResponse deletePOAndRelatedPI( @RequestParam("ids") String ids) {
        return getError();
    }
}
