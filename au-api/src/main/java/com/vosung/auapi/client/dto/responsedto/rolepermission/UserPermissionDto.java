package com.vosung.auapi.client.dto.responsedto.rolepermission;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询用户权限列表
 * @Author 彬
 * @Date 2019/6/11
 */
@Data
public class UserPermissionDto implements Serializable {
    private static final long serialVersionUID = -5462172161719139172L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    @FiledMessage(name = "用户")
    private String name;

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
     * 业务对象id
     */
    private Integer businessObjectId;
    /**
     * 业务对象名称
     */
    @FiledMessage(name = "业务对象")
    private String businessObjectName;
    /**
     * 权限项id
     */
    private Integer permissionItemId;
    /**
     * 权限项名称
     */
    @FiledMessage(name = "权限")
    private String permissionName;
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
