package com.vosung.auapi.client.dto.requestdto.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改角色入参类
 * @Author 彬
 * @Date 2019/5/6
 */
@Data
public class UpdateRoleStatusRequestDto implements Serializable {
    private static final long serialVersionUID = 5798613148076986434L;

    /**
     * 角色id 串（逗号分隔）
     */
    @NotNull(message = "操作得角色id不能为空")
    private String roleIds;
}
