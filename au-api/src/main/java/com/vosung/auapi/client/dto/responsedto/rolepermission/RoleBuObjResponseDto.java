package com.vosung.auapi.client.dto.responsedto.rolepermission;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色业务对象授权（根据角色差有权业务对象出参）
 * @Author 彬
 * @Date 2019/6/10
 */
@Data
public class RoleBuObjResponseDto implements Serializable {
    private static final long serialVersionUID = 1901398134537094305L;
    /**
     * 业务领域id
     */
    private Integer businessDomainId;
    /**
     * 业务领域名称
     */
    private String businessDomainName;
    /**
     * 子系统id
     */
    private Integer subsystemId;
    /**
     * 子系统名称
     */
    private String subsystemName;
    /**
     * 业务对象id
     */
    private Integer businessObjectId;
    /**
     * 业务对象名称
     */
    private String businessObjectName;
    /**
     * 权限对象id
     */
    private Integer permissionObjectId;
    /**
     * 是否设置
     */
    private String isSetting;
    /**
     * 业务对象（权限对象）对应的权限项list
     */
    private List<BuObjPermissionItemDto> permissionItemDtoList;
}
