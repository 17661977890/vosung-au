package com.vosung.auapi.client.dto.requestdto.rolesubsystem;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 彬
 * @Date 2019/6/18
 */
@Data
public class PermissionGroupDto implements Serializable {
    private static final long serialVersionUID = -7643458486121592741L;
    /**
     * 权限组编码
     */
    private String permissionGroupCode;
    /**
     * 权限状态
     */
    private String permissionStatus;
}
