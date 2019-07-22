package com.vosung.authapp.role.service;

import com.vosung.auapi.client.dto.requestdto.datarule.DataRuleRequestDto;
import com.vosung.auapi.client.dto.requestdto.datarule.GetDataRuleListDto;
import com.vosung.auapi.client.dto.requestdto.datarule.UpdateDataRuleStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

import javax.validation.Valid;

/**
 * 数据规则业务层接口
 * @Author 彬
 * @Date 2019/7/4
 */
public interface DataRuleService {

    /**
     * 保存数据规则-----同时保存数据规则条件（绑定关系）
     * @param dataRuleRequestDto
     * @return
     */
    RestResponse saveDataRule(DataRuleRequestDto dataRuleRequestDto);

    /**
     * 查数据规则列表
     * @param restRequest
     * @return
     */
    RestResponse getDataRuleList(RestRequest<GetDataRuleListDto> restRequest);

    /**
     * 查看数据规则详情
     * @param id
     * @return
     */
    RestResponse getDataRuleInfo(Integer id);

    /**
     * 删除数据规则
     * @param body
     * @return
     */
    RestResponse deleteDataRule(UpdateDataRuleStatusDto body);

    /**
     * 禁用数据规则
     * @param body
     * @return
     */
    RestResponse forbiddenDataRule(UpdateDataRuleStatusDto body);

    /**
     * 反禁用数据规则
     * @param body
     * @return
     */
    RestResponse unForbiddenDataRule(UpdateDataRuleStatusDto body);
}
