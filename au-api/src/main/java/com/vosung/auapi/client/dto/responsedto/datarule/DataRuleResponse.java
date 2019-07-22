package com.vosung.auapi.client.dto.responsedto.datarule;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.user.UserResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 列表出差
 * @Author 彬
 * @Date 2019/7/8
 */
@Data
public class DataRuleResponse implements Serializable{
    private static final long serialVersionUID = 577947602580643041L;

    //字段列表
    private List<ViewColumn> columnList;
    //数据规则列表分页详情
    private PageInfo<DataRuleListResponseDto> pageInfo;
    //数据规则总数量
    private Integer Sum;
}
