package com.vosung.auapi.client.dto.responsedto.rolesubsystem;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.rolepermission.RoleBuObjListDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询权限（角色----子系统）
 * @Author 彬
 * @Date 2019/6/24
 */
@Data
public class RoleSubsystemRseponse implements Serializable{

    private static final long serialVersionUID = 2679342107611346023L;
    //字段列表
    private List<ViewColumn> columnList;
    //拥有权限组分页详情
    private PageInfo<RoleSubsystemListDto> pageInfo;
    //权限总数量
    private Integer Sum;
}
