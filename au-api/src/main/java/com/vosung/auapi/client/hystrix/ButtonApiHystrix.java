package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.ButtonApi;
import com.vosung.auapi.client.dto.requestdto.button.ButtonListRequest;
import com.vosung.auapi.client.dto.requestdto.button.ButtonRequestDto;
import com.vosung.auapi.client.dto.requestdto.button.UpdateButtonStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 按钮熔断
 * @Author 彬
 * @Date 2019/5/22
 */
@Component
public class ButtonApiHystrix extends BaseHystrix implements ButtonApi{

    @Override
    public RestResponse saveButton(@RequestBody @Valid RestRequest<ButtonRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getButtonInfoById(Integer id) {
        return getError();
    }

    @Override
    public RestResponse commitButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deleteButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getButtonListByMenuId(Integer menuId) {
        return getError();
    }

    @Override
    public RestResponse getButtonList(@Valid RestRequest<ButtonListRequest> restRequest) {
        return getError();
    }
}
