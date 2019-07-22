package com.vosung.authapp.org.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.OrgRelationApi;
import com.vosung.auapi.client.dto.requestdto.orgrelation.DeleteOrgRelationRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelation.OrgRelationRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.org.service.OrgRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 组织机构隶属关系api实现类
 * @Author 彬
 * @Date 2019/4/28
 */
@Slf4j
@RestController
public class OrgRelationApiImpl extends BaseApiImpl implements OrgRelationApi {
    @Autowired
    private OrgRelationService orgRelationService;

    /**
     * 新增组织关系
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse addOrgRelation(@RequestBody @Valid RestRequest<OrgRelationRequestDto> restRequest) {
        log.info("新增组织机构隶属关系入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationService.addOrgRelation(restRequest.getBody());
        log.info("新增组织机构隶属关系出参："+JSON.toJSONString(restRequest));
        return restResponse;
    }

    /**
     * 根据主键id 删除组织关系（如果删除的组织关系id的组织id 是父节点，那子节点也都删除）
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deleteOrgRelation(@RequestBody @Valid RestRequest<DeleteOrgRelationRequestDto> restRequest) {
        log.info("删除组织机构隶属关系入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationService.deleteOrgRelation(restRequest.getBody());
        log.info("删除组织机构隶属关系出参："+JSON.toJSONString(restRequest));
        return restResponse;
    }
}
