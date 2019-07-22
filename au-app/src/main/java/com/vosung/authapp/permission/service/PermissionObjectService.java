package com.vosung.authapp.permission.service;

import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequest;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

/**
 * 权限对象管理业务层
 * @Author 彬
 * @Date 2019/5/6
 */
public interface PermissionObjectService {
    /**
     * 保存权限对象
     * @param permissionObjectRequestDto
     * @return
     */
    RestResponse savePermissionObject(PermissionObjectRequestDto permissionObjectRequestDto);

    /**
     * 根据id 查询权限对象详情
     * @param id
     * @return
     */
    RestResponse getPermissionObjectInfoById(Integer id);

    /**
     * 权限对象列表
     * @param restRequest
     * @return
     */
    RestResponse getPermissionObjectList(RestRequest<PermissionObjectRequest> restRequest);

    /**
     * 根据权限对象id 获取所关联权限项列表
     * @param permissionObjectId
     * @return
     */
    RestResponse getPermissionItemListByPOId(Integer permissionObjectId);

    /**
     * 删除权限对象，以及与权限项的关系数据，（如果绑定了业务对象，调接口解除与业务对象关系）
     * @param ids
     * @return
     */
    RestResponse deletePOAndRelatedPI(String ids);
}
