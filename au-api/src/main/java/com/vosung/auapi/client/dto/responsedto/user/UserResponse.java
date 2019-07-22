package com.vosung.auapi.client.dto.responsedto.user;

import com.github.pagehelper.PageInfo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户列表出参
 * @Author 彬
 * @Date 2019/5/25
 */
@Data
public class UserResponse implements Serializable{

    private static final long serialVersionUID = 2280783630657212270L;
    //字段列表
    private List<ViewColumn> columnList;
    //用户分页详情
    private PageInfo<UserResponseDto> pageInfo;
    //用户总数量
    private Integer Sum;
}
