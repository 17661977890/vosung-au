package com.vosung.authapp.resource.service;

import com.vosung.auapi.client.dto.requestdto.menu.MenuRequest;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequestDto;
import com.vosung.auapi.client.dto.requestdto.menu.UpdateMenuStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;


/**
 * 菜单维护管理业务层
 * @Author 彬
 * @Date 2019/5/16
 */
public interface MenuService {
    /**
     * 保存菜单
     * @param menuRequestDto
     * @return
     */
    RestResponse saveMenu(MenuRequestDto menuRequestDto);

    /**
     * 查看菜单详情
     * @param id
     * @return
     */
    RestResponse getMenuInfoById(Integer id);

    /**
     * 提交菜单
     * @param body
     * @return
     */
    RestResponse commitMenu(UpdateMenuStatusDto body);
    /**
     * 撤销菜单
     * @param body
     * @return
     */
    RestResponse abolishMenu( UpdateMenuStatusDto body);
    /**
     * 审核菜单
     * @param body
     * @return
     */
    RestResponse auditMenu(UpdateMenuStatusDto body);
    /**
     * 反审核菜单
     * @param body
     * @return
     */
    RestResponse reverseAuditMenu(UpdateMenuStatusDto body);
    /**
     * 提交菜单
     * @param body
     * @return
     */
    RestResponse deleteMenu(UpdateMenuStatusDto body);
    /**
     * 禁用菜单
     * @param body
     * @return
     */
    RestResponse forbiddenMenu(UpdateMenuStatusDto body);
    /**
     * 反禁菜单
     * @param body
     * @return
     */
    RestResponse unForbiddenMenu(UpdateMenuStatusDto body);

    /**
     * 展示所有菜单---树结构
     * @return
     */
    RestResponse getAllMenuTree();

    /**
     * 菜单列表
     * @param restRequest
     * @return
     */
    RestResponse getMenuList(RestRequest<MenuRequest> restRequest);
}
