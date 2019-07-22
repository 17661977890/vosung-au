package com.vosung.auapi.client.dto.responsedto.org;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.entity.TAuOrganization;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组织机构列表查询出参类
 * @Author 彬
 * @Date 2019/4/22
 */
@Data
public class OrgResponse implements Serializable{
    private static final long serialVersionUID = 8922532354231371974L;
    //字段列表
    private List<ViewColumn> columnList;
    //组织机构分页详情
    private PageInfo<OrgResponseDto> pageInfo;
    //组织机构总数量
    private Integer sum;
}
