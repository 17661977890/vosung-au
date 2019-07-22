package com.vosung.auapi.client.dto.requestdto.permissionitem;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限项保存入参类
 * @Author 彬
 * @Date 2019/5/6
 */
@Data
public class PermissionItemRequestDto implements Serializable{

    private static final long serialVersionUID = -3698947968644553321L;

    private Integer id;
    /**
     * 权限项编码
     */
    @NotNull(message = "权限项编码不能为空")
    private String permissionCode;
    /**
     * 权限项名称
     */
    @NotNull(message = "权限项名称不能为空")
    private String permissionName;
    /**
     * 权限项组编码
     */
    @NotNull(message = "权限项组编码不能为空")
    private String permissionGroupCode;
    /**
     * 描述
     */
    private String summary;
}
