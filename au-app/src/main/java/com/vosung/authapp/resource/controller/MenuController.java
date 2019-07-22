package com.vosung.authapp.resource.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.MenuApi;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequest;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequestDto;
import com.vosung.auapi.client.dto.requestdto.menu.UpdateMenuStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 菜单web层
 * @Author 彬
 * @Date 2019/5/16
 */
@Slf4j
@RestController
@RequestMapping("auth/menuController")
public class MenuController extends BaseController {

    @Autowired
    private MenuApi menuApi;

    /**
     * 菜单保存
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveMenu",method = RequestMethod.POST)
    public RestResponse saveMenu(@RequestBody @Valid RestRequest<MenuRequestDto> restRequest) {
        log.info("保存菜单信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.saveMenu(restRequest);
        log.info("保存菜单信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看菜单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMenuInfoById",method = RequestMethod.POST)
    public RestResponse getMenuInfoById(@RequestParam("id") Integer id) {
        log.info("查看菜单详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.getMenuInfoById(id);
        log.info("查看菜单详情出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 提交菜单
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/commitMenu",method = RequestMethod.POST)
    public RestResponse commitMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("提交菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.commitMenu(restRequest);
        log.info("提交菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销菜单
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/abolishMenu",method = RequestMethod.POST)
    public RestResponse abolishMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("撤销菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.abolishMenu(restRequest);
        log.info("撤销菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核菜单
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/auditMenu",method = RequestMethod.POST)
    public RestResponse auditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("审核菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.auditMenu(restRequest);
        log.info("审核菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核菜单
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/reverseAuditMenu",method = RequestMethod.POST)
    public RestResponse reverseAuditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("反审核菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.reverseAuditMenu(restRequest);
        log.info("反审核菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除菜单
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deleteMenu",method = RequestMethod.POST)
    public RestResponse deleteMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("删除菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.deleteMenu(restRequest);
        log.info("删除菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用菜单
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenMenu",method = RequestMethod.POST)
    public RestResponse forbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("禁用菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.forbiddenMenu(restRequest);
        log.info("禁用菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用菜单
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenMenu",method = RequestMethod.POST)
    public RestResponse unForbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("反禁用菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.unForbiddenMenu(restRequest);
        log.info("反禁用菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 展示所有可用菜单树
     * @return
     */
    @RequestMapping(value = "/getAllMenuTree",method = RequestMethod.POST)
    public RestResponse getAllMenuTree() {
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.getAllMenuTree();
        log.info("展示菜单树出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 菜单列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getMenuList",method = RequestMethod.POST)
    public RestResponse getMenuList(@RequestBody @Valid RestRequest<MenuRequest> restRequest) {
        log.info("查询菜单列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuApi.getMenuList(restRequest);
        log.info("查询菜单列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
