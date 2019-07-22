package com.vosung.auapi.client.dto.requestdto.dept;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateDeptItem implements Serializable{
    private static final long serialVersionUID = -3673219863668452821L;
    //部门id
        @NotNull(message = "部门id不能为空")
        private Integer id;
        //部门编码
        @NotNull(message = "部门编码不能为空")
        private String deptCode;
        //部门名称
        @NotNull(message = "部门数据状态不能为空")
        private String dataState;
}