package com.vosung.auapi.client.dto.requestdto.deptgroup;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门分组保存入参类
 * @Author 彬
 * @Date 2019/4/26
 */
@Data
public class DeptGroupRequestDto implements Serializable{

    private static final long serialVersionUID = 1167159618486258131L;

    private Integer id;
    /**
     * 上级分组信息
     */
    private Integer parentGroupId;
    /**
     * 部门分组名（可重复）
     */
    @NotNull(message = "部门分组名不能为空")
    private String deptGroupName;
    /**
     * 部门编码 --不能重复（判重）
     */
    @NotNull(message = "部门分组编码不能为空")
    private String deptGroupCode;
    /**
     * 描述
     */
    private String summary;
}
