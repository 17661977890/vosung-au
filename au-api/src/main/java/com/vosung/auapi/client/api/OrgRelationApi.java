package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.orgrelation.DeleteOrgRelationRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelation.OrgRelationRequestDto;
import com.vosung.auapi.client.hystrix.OrgRelationHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 组织关系api
 * @Author 彬
 * @Date 2019/4/28
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = OrgRelationHystrix.class)
public interface OrgRelationApi {

    /**
     * 新增组织关系api
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/OrgRelationController/addOrgRelation",method = RequestMethod.POST)
    RestResponse addOrgRelation(@RequestBody @Valid RestRequest<OrgRelationRequestDto> restRequest);

    /**
     * 根据主键id 删除组织关系（如果删除的组织关系id的组织id 是父节点，那子节点也都删除）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/OrgRelationController/deleteOrgRelation",method = RequestMethod.POST)
    RestResponse deleteOrgRelation(@RequestBody @Valid RestRequest<DeleteOrgRelationRequestDto> restRequest);
}
