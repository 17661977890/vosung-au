package com.vosung.auapi.client.dto.requestdto.deptgroup;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查看部门分组详情入参
 * @Author 彬
 * @Date 2019/4/26
 */
@Data
public class DeptGroupInfoRequestDto implements Serializable{
    private static final long serialVersionUID = -7345445666061330579L;

    @NotNull(message = "部门分组id不能为空")
    private Integer id;
}
