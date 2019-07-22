package com.vosung.authapp.resource.client.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.ButtonApi;
import com.vosung.auapi.client.dto.requestdto.button.ButtonListRequest;
import com.vosung.auapi.client.dto.requestdto.button.ButtonRequestDto;
import com.vosung.auapi.client.dto.requestdto.button.UpdateButtonStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.resource.service.ButtonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 按钮的相关api实现类
 * @Author 彬
 * @Date 2019/5/22
 */
@Slf4j
@RestController
public class ButtonApiImpl extends BaseApiImpl implements ButtonApi{
    @Autowired
    private ButtonService buttonService;

    /**
     * 保存按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveButton(@RequestBody @Valid RestRequest<ButtonRequestDto> restRequest) {
        log.info("保存按钮信息入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.saveButton(restRequest.getBody());
        log.info("保存菜单信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看按钮详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getButtonInfoById(Integer id) {
        log.info("查看按钮信息详情入参："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.getButtonInfoById(id);
        log.info("查看按钮信息详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 提交按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse commitButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("提交菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.commitButton(restRequest.getBody());
        log.info("提交菜单出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 撤销按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse abolishButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("撤销按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.abolishButton(restRequest.getBody());
        log.info("撤销按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 审核按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse auditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("审核按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.auditButton(restRequest.getBody());
        log.info("审核按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 反审核按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse reverseAuditButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("反审核按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.reverseAuditButton(restRequest.getBody());
        log.info("反审核按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 删除按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deleteButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("删除按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.deleteButton(restRequest.getBody());
        log.info("删除按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 禁用按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("禁用按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.forbiddenButton(restRequest.getBody());
        log.info("禁用按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
    /**
     * 反禁按钮
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenButton(@RequestBody @Valid RestRequest<UpdateButtonStatusDto> restRequest) {
        log.info("反禁按钮入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.unForbiddenButton(restRequest.getBody());
        log.info("反禁按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询菜单下的有效按钮
     * @param menuId
     * @return
     */
    @Override
    public RestResponse getButtonListByMenuId(Integer menuId) {
        log.info("查询菜单下的有效按钮入参："+ menuId);
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.getButtonListByMenuId(menuId);
        log.info("查询菜单下的有效按钮出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看按钮列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getButtonList(@Valid RestRequest<ButtonListRequest> restRequest) {
        log.info("查询按钮列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = buttonService.getButtonList(restRequest);
        log.info("查询按钮列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


}
