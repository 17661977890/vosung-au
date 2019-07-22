package com.vosung.auapi.client.dto.requestdto.permissionobject;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 保存权限对象入参类
 * @Author 彬
 * @Date 2019/5/6
 */
@Data
public class PermissionObjectRequestDto implements Serializable{
    private static final long serialVersionUID = 757626689122591226L;

    private Integer id;
    /**
     * 子系统Id
     */
    @NotNull(message = "子系统id不能为空")
    private Integer subsystemId;
    /**
     * 权限对象编码
     */
    @NotNull(message = "权限对象编码不能为空")
    private String permissionObjectCode;
    /**
     * 权限对象名称
     */
    @NotNull(message = "权限对象名称不能为空")
    private String permissionObjectName;
    /**
     * 关联权限项
     */
    private List<Integer> permissionItemIds;
}
