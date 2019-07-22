package com.vosung.auapi.client.dto.requestdto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 重置密码入参
 * @Author 彬
 * @Date 2019/5/5
 */
@Data
public class ResetPasswordRequestDto implements Serializable{
    private static final long serialVersionUID = 8210346793553956994L;

    @NotNull(message = "用户id不能为空")
    private Integer id;

    @NotNull(message = "新密码不能为空")
    private String newPassword;

    @NotNull(message = "确认新密码不能为空")
    private String confirmNewPassword;
}
