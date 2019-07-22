package com.vosung.auapi.client.dto.responsedto.sysdict;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.entity.TSysDictItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 字典查询列表出参
 * @Author 彬
 * @Date 2019/6/17
 */
@Data
public class SysDictResponse implements Serializable{
    private static final long serialVersionUID = -7458430892837861254L;

    //字段列表
    private List<ViewColumn> columnList;
    //字典项分页详情
    private PageInfo<DictItemResponseDto> pageInfo;
    //字典项总数量
    private Integer Sum;
}
