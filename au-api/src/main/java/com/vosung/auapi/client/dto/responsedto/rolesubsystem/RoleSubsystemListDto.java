package com.vosung.auapi.client.dto.responsedto.rolesubsystem;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * 子系统授权list
 * @Author 彬
 * @Date 2019/6/24
 */
@Data
public class RoleSubsystemListDto implements Serializable {
    private static final long serialVersionUID = -5798333500572822370L;

    /**
     * 业务领域id
     */
    private Integer businessDomainId;
    /**
     * 业务领域名称
     */
    @FiledMessage(name = "业务领域")
    private String businessDomainName;
    /**
     * 子系统id
     */
    private Integer subsystemId;
    /**
     * 子系统名称
     */
    @FiledMessage(name = "子系统")
    private String subsystemName;

    /**
     * 权限项组code
     */
    private Integer permissionGroupCode;
    /**
     * 权限项名称
     */
    @FiledMessage(name = "权限项组")
    private String permissionGroupName;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    @FiledMessage(name = "角色")
    private String roleName;


    @FiledMessage(name = "权限状态")
    private String permissionStatus;
}
