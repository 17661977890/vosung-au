package com.vosung.auapi.client.dto.responsedto.position;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.orgrelationscheme.GetSchemeInfoResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 岗位多列表查询
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class PositionListResponse  implements Serializable{
    private static final long serialVersionUID = -1876422752879063517L;

    //字段列表
    private List<ViewColumn> columnList;
    //岗位分页详情
    private PageInfo<PositionResponseDto> pageInfo;
    //岗位总数量
    private Integer Sum;
}
