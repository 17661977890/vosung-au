package com.vosung.auapi.client.dto.responsedto.post;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 员工任岗明细列表---》出参
 * @Author 彬
 * @Date 2019/5/28
 */
@Data
public class PostListResponse implements Serializable {
    private static final long serialVersionUID = -2034836352595229049L;

    //字段列表
    private List<ViewColumn> columnList;
    //任岗分页详情
    private PageInfo<PostListDto> pageInfo;
    //任岗总数量
    private Integer Sum;
}
