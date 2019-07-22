package com.vosung.auapi.client.dto.responsedto.role;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.user.UserResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色列表出参
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class RoleResponse implements Serializable {
    private static final long serialVersionUID = -3251107441443744551L;

    //字段列表
    private List<ViewColumn> columnList;
    //角色分页详情
    private PageInfo<RoleResponseDto> pageInfo;
    //角色总数量
    private Integer Sum;
}
