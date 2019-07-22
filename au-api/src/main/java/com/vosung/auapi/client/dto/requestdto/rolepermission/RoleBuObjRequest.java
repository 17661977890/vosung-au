package com.vosung.auapi.client.dto.requestdto.rolepermission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查看角色权限
 * @Author 彬
 * @Date 2019/6/11
 */
@Data
public class RoleBuObjRequest implements Serializable {
    private static final long serialVersionUID = 1516724799910907211L;

    @NotNull(message = "请选择角色")
    private Integer roleId;
}
