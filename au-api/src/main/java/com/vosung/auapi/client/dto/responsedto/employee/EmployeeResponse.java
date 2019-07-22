package com.vosung.auapi.client.dto.responsedto.employee;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 员工列表
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class EmployeeResponse implements Serializable {
    private static final long serialVersionUID = -7992472261842079609L;

    //字段列表
    private List<ViewColumn> columnList;
    //岗位分页详情
    private PageInfo<EmployeeResponseDto> pageInfo;
    //岗位总数量
    private Integer Sum;
}
