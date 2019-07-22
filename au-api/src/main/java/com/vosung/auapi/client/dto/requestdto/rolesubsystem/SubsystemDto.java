package com.vosung.auapi.client.dto.requestdto.rolesubsystem;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 子系统
 * @Author 彬
 * @Date 2019/6/18
 */
@Data
public class SubsystemDto implements Serializable {
    private static final long serialVersionUID = -7757377952335317067L;

    /**
     * 业务领域id
     */
    private Integer businessDomainId;
    /**
     * 子系统id
     */
    private Integer subsystemId;
    /**
     * 权限组授权list
     */
    private List<PermissionGroupDto> permissionGroupDtoList ;
}
