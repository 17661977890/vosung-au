package com.vosung.authapp.permission.service;

import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemListRequest;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import io.swagger.models.auth.In;

/**
 * 权限项管理业务层
 * @Author 彬
 * @Date 2019/5/6
 */
public interface PermissionItemService{

    /**
     * 保存权限项（新增/修改）
     * @param permissionItemRequestDto
     * @return
     */
    RestResponse savePermissionItem(PermissionItemRequestDto permissionItemRequestDto);

    /**
     * 根据id查权限项详情
     * @param id
     * @return
     */
    RestResponse getPermissionItemInfoById(Integer id);

    /**
     * 查询权限项列表
     * @param restRequest
     * @return
     */
    RestResponse getPermissionItemList(RestRequest<PermissionItemListRequest> restRequest);

    /**
     * （逻辑）删除权限项
     * @param itemIds
     * @return
     */
    RestResponse deletePermissionItem(String itemIds);
}
