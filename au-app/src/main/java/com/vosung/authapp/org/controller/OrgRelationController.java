package com.vosung.authapp.org.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.OrgRelationApi;
import com.vosung.auapi.client.dto.requestdto.orgrelation.DeleteOrgRelationRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelation.OrgRelationRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/4/28
 */
@Slf4j
@RequestMapping("auth/OrgRelationController")
@RestController
public class OrgRelationController extends BaseController {
    @Autowired
    private OrgRelationApi orgRelationApi;

    /**
     * 新增组织关系
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/addOrgRelation",method = RequestMethod.POST)
    public RestResponse addOrgRelation(@RequestBody @Valid RestRequest<OrgRelationRequestDto> restRequest) {
        log.info("保存组织机构隶属关系入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationApi.addOrgRelation(restRequest);
        log.info("保存组织机构隶属关系出参："+JSON.toJSONString(restRequest));
        return restResponse;
    }

    /**
     * 删除组织关系
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deleteOrgRelation",method = RequestMethod.POST)
    public RestResponse deleteOrgRelation(@RequestBody @Valid RestRequest<DeleteOrgRelationRequestDto> restRequest) {
        log.info("删除组织机构隶属关系入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationApi.deleteOrgRelation(restRequest);
        log.info("删除组织机构隶属关系出参："+JSON.toJSONString(restRequest));
        return restResponse;
    }
}
