package com.vosung.auapi.client.dto.requestdto.employee;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 岗位汇报体系，添加任岗信息，显示未任岗员工list 初始入参
 * @Author 彬
 * @Date 2019/5/28
 */
@Data
public class UNPostEmployeeRequest implements Serializable {
    private static final long serialVersionUID = -5990031585731554045L;
    /**
     * 岗位id
     */
    @NotNull(message = "就任岗位id 不能为空")
    private Integer positionId;

    /**
     * 任岗的员工idlist（后端可以查询获得）
     */
    private List<Integer> employeeIdList;
}
