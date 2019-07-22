package com.vosung.auapi.client.dto.responsedto.button;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 按钮列表出参
 * @Author 彬
 * @Date 2019/5/29
 */
@Data
public class ButtonResponse implements Serializable {
    private static final long serialVersionUID = -3386889698122702748L;

    //字段列表
    private List<ViewColumn> columnList;
    //菜单分页详情
    private PageInfo<ButtonResponseDto> pageInfo;
    //菜单总数量
    private Integer Sum;
}
