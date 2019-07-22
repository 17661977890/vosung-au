package com.vosung.auapi.client.dto.requestdto.rolesubsystem;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询权限（角色--子系统）
 * @Author 彬
 * @Date 2019/6/24
 */
@Data
public class RoleSubsystemRequest implements Serializable {
    private static final long serialVersionUID = 8161350364558016224L;


    @NotNull(message = "请选择角色")
    private Integer roleId;
}
