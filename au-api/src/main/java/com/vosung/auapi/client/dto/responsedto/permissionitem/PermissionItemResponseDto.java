package com.vosung.auapi.client.dto.responsedto.permissionitem;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限项查询出参类
 * @Author 彬
 * @Date 2019/5/6
 */
@Data
public class PermissionItemResponseDto implements Serializable{

    private static final long serialVersionUID = -3698947968644553321L;

    private Integer id;
    /**
     * 权限项编码
     */
    @FiledMessage(name = "权限项编码")
    private String permissionCode;
    /**
     * 权限项名称
     */
    @FiledMessage(name = "权限项名称")
    private String permissionName;
    /**
     * 权限项组id（将权限项组置为字典项，存字典code值）
     */
    private String permissionGroupCode;
    /**
     * 权限项组名称
      */
    @FiledMessage(name = "权限项组")
    private String permissionGroupName;
    /**
     * 描述
     */
    @FiledMessage(name = "描述")
    private String summary;

    @FiledMessage(name = "系统预置")
    private String isSystemInit;
}
