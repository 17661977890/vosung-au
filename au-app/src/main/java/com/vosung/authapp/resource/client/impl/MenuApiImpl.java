package com.vosung.authapp.resource.client.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.MenuApi;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequest;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequestDto;
import com.vosung.auapi.client.dto.requestdto.menu.UpdateMenuStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.resource.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 菜单api实现类
 * @Author 彬
 * @Date 2019/5/16
 */
@Slf4j
@RestController
public class MenuApiImpl extends BaseApiImpl implements MenuApi{
    @Autowired
    private MenuService menuService;

    /**
     * 菜单保存
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveMenu(@RequestBody @Valid RestRequest<MenuRequestDto> restRequest) {
        log.info("保存菜单信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.saveMenu(restRequest.getBody());
        log.info("保存菜单信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看菜单详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getMenuInfoById(@RequestParam("id") Integer id) {
        log.info("查看菜单详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.getMenuInfoById(id);
        log.info("查看菜单详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 提交菜单
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse commitMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("提交菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.commitMenu(restRequest.getBody());
        log.info("提交菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销菜单
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse abolishMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("撤销菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.abolishMenu(restRequest.getBody());
        log.info("撤销菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核菜单
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse auditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("审核菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.auditMenu(restRequest.getBody());
        log.info("审核菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核菜单
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse reverseAuditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("反审核菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.reverseAuditMenu(restRequest.getBody());
        log.info("反审核菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除菜单
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deleteMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("删除菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.deleteMenu(restRequest.getBody());
        log.info("删除菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用菜单
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("禁用菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.forbiddenMenu(restRequest.getBody());
        log.info("禁用菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用菜单
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest) {
        log.info("反禁用菜单入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.unForbiddenMenu(restRequest.getBody());
        log.info("反禁用菜单出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 展示菜单树
     * @return
     */
    @Override
    public RestResponse getAllMenuTree() {
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.getAllMenuTree();
        log.info("展示菜单树出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 菜单列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getMenuList(@RequestBody @Valid RestRequest<MenuRequest> restRequest) {
        log.info("查询菜单列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = menuService.getMenuList(restRequest);
        log.info("查询菜单列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
