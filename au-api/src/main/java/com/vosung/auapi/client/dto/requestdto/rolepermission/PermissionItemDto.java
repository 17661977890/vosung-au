package com.vosung.auapi.client.dto.requestdto.rolepermission;

import lombok.Data;

import java.io.Serializable;

/**
 * 业务对象（权限对象）对应的---权限项
 * @Author 彬
 * @Date 2019/5/28
 */
@Data
public class PermissionItemDto implements Serializable {
    private static final long serialVersionUID = 7868722141072830499L;
    /**
     * 权限项id
     */
    private Integer permissionItemId;

    private String url;
    /**
     * 权限状态 1：有权 0：无权 2：禁止
     */
    private String permissionStatus;
}
