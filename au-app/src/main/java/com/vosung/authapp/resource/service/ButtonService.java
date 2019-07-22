package com.vosung.authapp.resource.service;

import com.vosung.auapi.client.dto.requestdto.button.ButtonListRequest;
import com.vosung.auapi.client.dto.requestdto.button.ButtonRequestDto;
import com.vosung.auapi.client.dto.requestdto.button.UpdateButtonStatusDto;
import com.vosung.auapi.client.dto.requestdto.menu.UpdateMenuStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import io.swagger.models.auth.In;

/**
 * 按钮业务层接口
 * @Author 彬
 * @Date 2019/5/22
 */
public interface ButtonService {

    /**
     * 保存按钮
     * @param buttonRequestDto
     * @return
     */
    RestResponse saveButton(ButtonRequestDto buttonRequestDto);

    /**
     * 根据按钮id查看详情
     * @param id
     * @return
     */
    RestResponse getButtonInfoById(Integer id);

    /**
     * 提交按钮
     * @param body
     * @return
     */
    RestResponse commitButton(UpdateButtonStatusDto body);
    /**
     * 撤销按钮
     * @param body
     * @return
     */
    RestResponse abolishButton( UpdateButtonStatusDto body);
    /**
     * 审核按钮
     * @param body
     * @return
     */
    RestResponse auditButton(UpdateButtonStatusDto body);
    /**
     * 反审核按钮
     * @param body
     * @return
     */
    RestResponse reverseAuditButton(UpdateButtonStatusDto body);
    /**
     * 提交按钮
     * @param body
     * @return
     */
    RestResponse deleteButton(UpdateButtonStatusDto body);
    /**
     * 禁用按钮
     * @param body
     * @return
     */
    RestResponse forbiddenButton(UpdateButtonStatusDto body);
    /**
     * 反禁按钮
     * @param body
     * @return
     */
    RestResponse unForbiddenButton(UpdateButtonStatusDto body);

    /**
     * 获取菜单下的所有有效按钮（未删除，未禁用，已审核）
     * @param menuId
     * @return
     */
    RestResponse getButtonListByMenuId(Integer menuId);

    /**
     * 获取按钮列表
     * @param restRequest
     * @return
     */
    RestResponse getButtonList(RestRequest<ButtonListRequest> restRequest);
}
