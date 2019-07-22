package com.vosung.auapi.client.dto.responsedto.permissionobject;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.permissionitem.PermissionItemResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限对象列表
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class PermissionObjectResponse implements Serializable{
    private static final long serialVersionUID = 6699642570602908236L;

    //字段列表
    private List<ViewColumn> columnList;
    //权限对象分页详情
    private PageInfo<PermissionObjectResponseDto> pageInfo;
    //权限对象总数量
    private Integer Sum;
}
