package com.vosung.auapi.client.dto.responsedto.orguserrole;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询用户出参（授权处）
 * @Author 彬
 * @Date 2019/7/2
 */
@Data
public class UserListResponse implements Serializable {
    private static final long serialVersionUID = -8156020390115756911L;

    //字段列表
    private List<ViewColumn> columnList;
    //角色分页详情
    private PageInfo<UserListDto> pageInfo;
    //角色总数量
    private Integer Sum;
}
