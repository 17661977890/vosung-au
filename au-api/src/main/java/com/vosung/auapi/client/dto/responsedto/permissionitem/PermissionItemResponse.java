package com.vosung.auapi.client.dto.responsedto.permissionitem;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限项列表查询
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class PermissionItemResponse implements Serializable {
    private static final long serialVersionUID = 4927843582841423096L;

    //字段列表
    private List<ViewColumn> columnList;
    //权限项分页详情
    private PageInfo<PermissionItemResponseDto> pageInfo;
    //权限项总数量
    private Integer Sum;
}
