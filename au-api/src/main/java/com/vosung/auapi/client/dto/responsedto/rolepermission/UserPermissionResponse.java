package com.vosung.auapi.client.dto.responsedto.rolepermission;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查看角色权限列表出参
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class UserPermissionResponse implements Serializable {

    private static final long serialVersionUID = 917694615947364825L;
    //字段列表
    private List<ViewColumn> columnList;
    //拥有权限分页详情
    private PageInfo<UserPermissionDto> pageInfo;
    //权限总数量
    private Integer Sum;
}
