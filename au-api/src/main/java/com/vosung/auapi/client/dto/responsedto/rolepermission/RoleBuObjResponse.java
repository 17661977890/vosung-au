package com.vosung.auapi.client.dto.responsedto.rolepermission;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.role.RoleResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查看角色权限列表出参
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class RoleBuObjResponse implements Serializable {
    private static final long serialVersionUID = -3251107441443744551L;

    //字段列表
    private List<ViewColumn> columnList;
    //拥有权限分页详情
    private PageInfo<RoleBuObjListDto> pageInfo;
    //权限总数量
    private Integer Sum;
}
