package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.DataRuleApi;
import com.vosung.auapi.client.dto.requestdto.datarule.DataRuleRequestDto;
import com.vosung.auapi.client.dto.requestdto.datarule.GetDataRuleListDto;
import com.vosung.auapi.client.dto.requestdto.datarule.UpdateDataRuleStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/7/4
 */
@Component
public class DataRuleApiHystrix extends BaseHystrix implements DataRuleApi{
    @Override
    public RestResponse saveDataRule(@RequestBody @Valid RestRequest<DataRuleRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getDataRuleList(@RequestBody @Valid RestRequest<GetDataRuleListDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getDataRuleInfo(@RequestParam("id") Integer id) {
        return getError();
    }

    @Override
    public RestResponse deleteDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest) {
        return getError();
    }
}
