package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.OrgRelationApi;
import com.vosung.auapi.client.dto.requestdto.orgrelation.DeleteOrgRelationRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelation.OrgRelationRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 组织机构隶属关系api 熔断处理
 * @Author 彬
 * @Date 2019/4/28
 */
@Component
public class OrgRelationHystrix extends BaseHystrix implements OrgRelationApi {
    @Override
    public RestResponse addOrgRelation(@RequestBody @Valid RestRequest<OrgRelationRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deleteOrgRelation(@RequestBody @Valid RestRequest<DeleteOrgRelationRequestDto> restRequest) {
        return getError();
    }
}
