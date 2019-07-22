package com.vosung.auapi.client.dto.responsedto.dept;


import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.entity.TAuDepartemnt;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 部门列表出参
 * @Author 彬
 * @Date 2019/4/25
 */
@Data
public class DeptResponseDto implements Serializable{
    private static final long serialVersionUID = 907986234353320777L;

    //字段列表
    private List<ViewColumn> columnList;
    /**
     * 分页列表（里面含有总数信息，只是当前页的）
     */
    private PageInfo<DeptInfoResponseDto> pageInfo;
    /**
     * 条件查询总数
     */
    private Integer sum;
}
