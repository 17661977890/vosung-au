package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.datarule.DataRuleRequestDto;
import com.vosung.auapi.client.dto.requestdto.datarule.GetDataRuleListDto;
import com.vosung.auapi.client.dto.requestdto.datarule.UpdateDataRuleStatusDto;
import com.vosung.auapi.client.hystrix.ButtonApiHystrix;
import com.vosung.auapi.client.hystrix.DataRuleApiHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 数据规则api
 * @Author 彬
 * @Date 2019/7/4
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = DataRuleApiHystrix.class)
public interface DataRuleApi {

    /**
     * 保存数据规则信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/dataRuleController/saveDataRule",method = RequestMethod.POST)
    RestResponse saveDataRule(@RequestBody @Valid RestRequest<DataRuleRequestDto> restRequest);

    /**
     * 查数据规则列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/dataRuleController/getDataRuleList",method = RequestMethod.POST)
    RestResponse getDataRuleList(@RequestBody @Valid RestRequest<GetDataRuleListDto> restRequest);

    /**
     * 查看数据规则详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/dataRuleController/getDataRuleInfo",method = RequestMethod.POST)
    RestResponse getDataRuleInfo(@RequestParam("id") Integer id);

    /**
     * 删除数据规则
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/dataRuleController/deleteDataRule",method = RequestMethod.POST)
    RestResponse deleteDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest);

    /**
     * 禁用数据规则
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/dataRuleController/forbiddenDataRule",method = RequestMethod.POST)
    RestResponse forbiddenDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest);

    /**
     * 反禁用数据规则
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/dataRuleController/unForbiddenDataRule",method = RequestMethod.POST)
    RestResponse unForbiddenDataRule(@RequestBody @Valid RestRequest<UpdateDataRuleStatusDto> restRequest);
}
