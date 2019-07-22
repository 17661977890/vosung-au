package com.vosung.auapi.client.dto.requestdto.dept;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查看部门详情的入参类
 * @Author 彬
 * @Date 2019/4/25
 */
@Data
public class DeptInfoRequestDto implements Serializable {
    private static final long serialVersionUID = -2541084599414296346L;

    @NotNull(message = "部门id不能为空")
    private Integer id;
}
