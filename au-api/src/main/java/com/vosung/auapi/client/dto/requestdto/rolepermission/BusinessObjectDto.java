package com.vosung.auapi.client.dto.requestdto.rolepermission;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 业务对象（已关联权限对象）关联权限项列表
 * @Author 彬
 * @Date 2019/5/28
 */
@Data
public class BusinessObjectDto implements Serializable {
    private static final long serialVersionUID = 8195452966845882545L;
    /**
     * 业务领域id
     */
    private Integer businessDomainId;
    /**
     * 子系统id
     */
    private Integer subsystemId;
    /**
     * 业务对象id
     */
    private Integer businessObjectId;
    /**
     * 权限对象id
     */
    private Integer permissionObjectId;
    /**
     * 权限项列表
     */
    private List<PermissionItemDto> permissionItemDtoList;

}
