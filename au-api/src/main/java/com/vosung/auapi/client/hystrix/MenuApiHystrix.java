package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.MenuApi;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequest;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequestDto;
import com.vosung.auapi.client.dto.requestdto.menu.UpdateMenuStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/5/16
 */
@Component
public class MenuApiHystrix extends BaseHystrix implements MenuApi{
    @Override
    public RestResponse saveMenu(@RequestBody @Valid RestRequest<MenuRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getMenuInfoById(@RequestParam("id") Integer id) {
        return getError();
    }

    @Override
    public RestResponse commitMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishMenu(@RequestBody  @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deleteMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getAllMenuTree() {
        return getError();
    }

    @Override
    public RestResponse getMenuList(@Valid RestRequest<MenuRequest> restRequest) {
        return getError();
    }
}
