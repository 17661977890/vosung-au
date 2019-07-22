package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.OrgRelationSchemeApi;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.OrgRelationSchemeRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.UpdateSchemeRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 组织机构隶属关系方案熔断处理
 * @Author 彬
 * @Date 2019/4/26
 */
@Component
public class OrgRelationSchemeHystrix extends BaseHystrix implements OrgRelationSchemeApi{
    @Override
    public RestResponse saveOrgRelationScheme(@Valid @RequestBody RestRequest<OrgRelationSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getOrgSchemeList(@RequestBody RestRequest<GetSchemeListRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse commitScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deleteScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getSchemeInfoById(@RequestBody @Valid RestRequest<GetSchemeInfoRequestDto> restRequest) {
        return getError();
    }

}
