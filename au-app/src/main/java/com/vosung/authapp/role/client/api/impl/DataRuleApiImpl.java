package com.vosung.authapp.role.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.DataRuleApi;
import com.vosung.auapi.client.dto.requestdto.datarule.DataRuleRequestDto;
import com.vosung.auapi.client.dto.requestdto.datarule.GetDataRuleListDto;
import com.vosung.auapi.client.dto.requestdto.datarule.UpdateDataRuleStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.role.service.DataRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 数据规则api实现类
 * @Author 彬
 * @Date 2019/7/4
 */
@Slf4j
@RestController
public class DataRuleApiImpl extends BaseApiImpl implements DataRuleApi {
    @Autowired
    private DataRuleService dataRuleService;

    /**
     * 保存数据规则信息
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveDataRule(@RequestBody @Valid RestRequest<DataRuleRequestDto> restRequest) {
        log.info("保存数据规则信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = dataRuleService.saveDataRule(restRequest.getBody());
        log.info("保存数据规则信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询数据规则列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getDataRuleList(@RequestBody @Valid RestRequest<GetDataRuleListDto> restRequest) {
        log.info("查询数据规则列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = dataRuleService.getDataRuleList(restRequest);
        log.info("查询数据规则列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看数据规则详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getDataRuleInfo(@RequestParam("id") Integer id) {
        log.info("查看数据规则详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = dataRuleService.getDataRuleInfo(id);
        log.info("查看数据规则详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除数据规则
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deleteDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest) {
        log.info("删除数据规则入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = dataRuleService.deleteDataRule(restRequest.getBody());
        log.info("删除数据规则出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用角色状态
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest) {
        log.info("禁用数据规则状态入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = dataRuleService.forbiddenDataRule(restRequest.getBody());
        log.info("禁用数据规则状态出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用角色状态
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest) {
        log.info("反禁用数据规则状态入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = dataRuleService.unForbiddenDataRule(restRequest.getBody());
        log.info("反禁用数据规则状态出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
