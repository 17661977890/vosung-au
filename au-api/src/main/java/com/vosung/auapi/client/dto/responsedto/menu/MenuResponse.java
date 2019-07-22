package com.vosung.auapi.client.dto.responsedto.menu;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.employee.EmployeeResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单列表出参
 * @Author 彬
 * @Date 2019/5/29
 */
@Data
public class MenuResponse implements Serializable {
    private static final long serialVersionUID = -2875079101016486755L;

    //字段列表
    private List<ViewColumn> columnList;
    //菜单分页详情
    private PageInfo<MenuResponseDto> pageInfo;
    //菜单总数量
    private Integer Sum;
}
