package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.button.ButtonListRequest;
import com.vosung.auapi.client.dto.requestdto.button.ButtonRequestDto;
import com.vosung.auapi.client.dto.requestdto.button.UpdateButtonStatusDto;
import com.vosung.auapi.client.hystrix.ButtonApiHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.omg.CORBA.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 按钮相关api
 * @Author 彬
 * @Date 2019/5/22
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = ButtonApiHystrix.class)
public interface ButtonApi {
    /**
     * 保存按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/saveButton",method = RequestMethod.POST)
    RestResponse saveButton(@RequestBody @Valid RestRequest<ButtonRequestDto> restRequest);

    /**
     * 查看按钮详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/buttonController/getButtonInfoById",method = RequestMethod.POST)
    RestResponse getButtonInfoById(@RequestParam("id") Integer id);

    /**
     * 提交按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/commitButton",method = RequestMethod.POST)
    RestResponse commitButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest);
    /**
     * 撤销按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/abolishButton",method = RequestMethod.POST)
    RestResponse abolishButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest);
    /**
     * 审核按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/auditButton",method = RequestMethod.POST)
    RestResponse auditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest);
    /**
     * 反审核按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/reverseAuditButton",method = RequestMethod.POST)
    RestResponse reverseAuditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest);
    /**
     * 提交按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/deleteButton",method = RequestMethod.POST)
    RestResponse deleteButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest);
    /**
     * 禁用按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/forbiddenButton",method = RequestMethod.POST)
    RestResponse forbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest);
    /**
     * 反禁按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/unForbiddenButton",method = RequestMethod.POST)
    RestResponse unForbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest);

    /**
     * 查菜单下有效按钮
     * @param menuId
     * @return
     */
    @RequestMapping(value = "api/buttonController/getButtonListByMenuId",method = RequestMethod.POST)
    RestResponse getButtonListByMenuId(@RequestParam("menuId") Integer menuId);

    /**
     * 获取按钮列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/buttonController/getButtonList",method = RequestMethod.POST)
    RestResponse getButtonList(@RequestBody @Valid RestRequest<ButtonListRequest> restRequest);
}
