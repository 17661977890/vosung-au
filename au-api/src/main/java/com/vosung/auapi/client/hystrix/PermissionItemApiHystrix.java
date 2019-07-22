package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.PermissionItemApi;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemListRequest;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 权限项接口熔断处理
 * @Author 彬
 * @Date 2019/5/6
 */
@Component
public class PermissionItemApiHystrix extends BaseHystrix implements PermissionItemApi{
    @Override
    public RestResponse savePermissionItem(@RequestBody @Valid RestRequest<PermissionItemRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getPermissionItemInfoById(@RequestParam("id") Integer id) {
        return getError();
    }

    @Override
    public RestResponse getPermissionItemList(RestRequest<PermissionItemListRequest> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deletePermissionItem(@RequestParam("itemIds") String itemIds) {
        return getError();
    }
}
