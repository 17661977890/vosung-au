package com.vosung.auapi.client.dto.responsedto.rolepermission;

import lombok.Data;

import java.io.Serializable;

/**
 * 业务对象（绑定权限对象）的权限项列表（授权状态）
 * @Author 彬
 * @Date 2019/6/10
 */
@Data
public class BuObjPermissionItemDto implements Serializable{
    private static final long serialVersionUID = 4068050996230314024L;
    /**
     * 权限项id
     */
    private Integer permissionItemId;
    /**
     * 权限项名称
     */
    private String permissionItemName;
    /**
     * 权限组名称
     */
    private String permissionGroupName;
    /**
     * 权限状态
     */
    private String permissionStatus;

    private String permissionGroupCode;
}
