package com.vosung.auapi.client.dto.requestdto.rolepermission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询用户权限入参
 * @Author 彬
 * @Date 2019/6/11
 */
@Data
public class UserPermissionRequest implements Serializable {
    private static final long serialVersionUID = -6984488106223625692L;
    @NotNull(message = "请选择用户")
    private Integer userId;
}
