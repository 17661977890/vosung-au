package com.vosung.authapp.resource.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.ButtonApi;
import com.vosung.auapi.client.dto.requestdto.button.ButtonListRequest;
import com.vosung.auapi.client.dto.requestdto.button.ButtonRequestDto;
import com.vosung.auapi.client.dto.requestdto.button.UpdateButtonStatusDto;
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
 * 按钮维护web
 * @Author 彬
 * @Date 2019/5/22
 */
@Slf4j
@RequestMapping("auth/buttonController")
@RestController
public class ButtonController extends BaseController {

    @Autowired
    private ButtonApi buttonApi;

    /**
     * 保存按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveButton",method = RequestMethod.POST)
    public RestResponse saveButton(@RequestBody @Valid RestRequest<ButtonRequestDto> restRequest) {
        log.info("保存按钮信息入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.saveButton(restRequest);
        log.info("保存菜单信息出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 查看按钮详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getButtonInfoById",method = RequestMethod.POST)
    public RestResponse getButtonInfoById(Integer id) {
        log.info("查看按钮信息详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.getButtonInfoById(id);
        log.info("查看按钮信息详情出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 提交按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/commitButton",method = RequestMethod.POST)
    public RestResponse commitButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("提交菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.commitButton(restRequest);
        log.info("提交菜单出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 撤销按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/abolishButton",method = RequestMethod.POST)
    public RestResponse abolishButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("撤销按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.abolishButton(restRequest);
        log.info("撤销按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 审核按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/auditButton",method = RequestMethod.POST)
    public RestResponse auditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("审核按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.auditButton(restRequest);
        log.info("审核按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 反审核按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/reverseAuditButton",method = RequestMethod.POST)
    public RestResponse reverseAuditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("反审核按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.reverseAuditButton(restRequest);
        log.info("反审核按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 删除按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deleteButton",method = RequestMethod.POST)
    public RestResponse deleteButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("删除按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.deleteButton(restRequest);
        log.info("删除按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 禁用按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenButton",method = RequestMethod.POST)
    public RestResponse forbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("禁用按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.forbiddenButton(restRequest);
        log.info("禁用按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 反禁按钮
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenButton",method = RequestMethod.POST)
    public RestResponse unForbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("反禁按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.unForbiddenButton(restRequest);
        log.info("反禁按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 查询菜单下的有效按钮
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/getButtonListByMenuId",method = RequestMethod.POST)
    public RestResponse getButtonListByMenuId(Integer menuId) {
        log.info("查询菜单下的有效按钮入参 ："+ menuId);
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.getButtonListByMenuId(menuId);
        log.info("查询菜单下的有效按钮出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看按钮列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getButtonList",method = RequestMethod.POST)
    public RestResponse getButtonList(@RequestBody @Valid RestRequest<ButtonListRequest> restRequest) {
        log.info("查询按钮列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonApi.getButtonList(restRequest);
        log.info("查询按钮列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

}
