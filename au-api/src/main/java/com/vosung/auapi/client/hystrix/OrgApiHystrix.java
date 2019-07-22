package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.OrgApi;
import com.vosung.auapi.client.dto.requestdto.org.GetOrgListRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.UpdateOrgDataStateDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 组织机构熔断处理
 * @Author 彬
 * @Date 2019/4/26
 */
@Component
public class OrgApiHystrix extends BaseHystrix implements OrgApi {


    @Override
    public RestResponse saveNewOrg(@RequestBody @Valid RestRequest<OrgRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getOrgList(@RequestBody @Valid RestRequest<GetOrgListRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getOrgInfoById(@RequestBody @Valid RestRequest<OrgInfoRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getSelectableOrgList() {
        return getError();
    }

    @Override
    public RestResponse getLegalPersonOrgList() {
        return getError();
    }

    @Override
    public RestResponse commitOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        return null;
    }

    @Override
    public RestResponse deleteOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        return getError();
    }
}
