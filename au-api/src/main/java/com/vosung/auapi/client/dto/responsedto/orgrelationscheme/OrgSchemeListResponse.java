package com.vosung.auapi.client.dto.responsedto.orgrelationscheme;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组织机构方案列表
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class OrgSchemeListResponse implements Serializable {
    private static final long serialVersionUID = -5633143890387063663L;

    //字段列表
    private List<ViewColumn> columnList;
    //方案分页详情
    private PageInfo<GetSchemeInfoResponseDto> pageInfo;
    //方案总数量
    private Integer Sum;
}
