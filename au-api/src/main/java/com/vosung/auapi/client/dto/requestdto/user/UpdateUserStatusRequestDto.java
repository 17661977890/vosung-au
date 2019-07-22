package com.vosung.auapi.client.dto.requestdto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 修改用户状态入参
 * @Author 彬
 * @Date 2019/5/5
 */
@Data
public class UpdateUserStatusRequestDto implements Serializable {
    private static final long serialVersionUID = -2753849793606820454L;

    /**
     * 用户id 串（逗号分隔）
     */
    @NotNull(message = "请选择操作用户")
    private String userIds;
}
