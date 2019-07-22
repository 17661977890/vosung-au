package com.vosung.auapi.client.dto.responsedto.rolesubsystem;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查子系统授权数据
 * @Author 彬
 * @Date 2019/6/21
 */
@Data
public class RoleSubsystemResponseDto implements Serializable {
    private static final long serialVersionUID = -7679462062043739884L;

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
     * 是否设置
     */
    private String isSetting;
    /**
     * 子系统对应的权限组list(目前权限组只有5个)
     */
    private List<SubsysPermissionGroupDto> permissionGroupDtoList;
}
