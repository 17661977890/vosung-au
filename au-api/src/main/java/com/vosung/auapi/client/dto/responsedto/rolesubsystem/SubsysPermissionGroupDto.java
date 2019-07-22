package com.vosung.auapi.client.dto.responsedto.rolesubsystem;

import lombok.Data;

import java.io.Serializable;

/**
 * 子系统对应的权限组list
 * @Author 彬
 * @Date 2019/6/21
 */
@Data
public class SubsysPermissionGroupDto implements Serializable {
    private static final long serialVersionUID = 478328995524127161L;
    /**
     * 权限组code
     */
    private String permissionGroupCode;
    /**
     * 权限组名称
     */
    private String permissionGroupName;
    /**
     * 权限状态
     */
    private String permissionStatus;
}
