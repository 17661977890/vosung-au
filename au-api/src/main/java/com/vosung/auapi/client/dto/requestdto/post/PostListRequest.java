package com.vosung.auapi.client.dto.requestdto.post;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任岗列表 多条件请求入参
 * @Author 彬
 * @Date 2019/5/28
 */
@Data
public class PostListRequest implements Serializable {
    private static final long serialVersionUID = 9200350181108764597L;

    private Integer id;

    private Integer employeeId;

    private Integer workOrgId;

    private Integer belongDeptId;

    private Integer directorPositionId;

    private Date directorPositionStartDate;

    private String isMainPosition;

    private String isDelete;

    private String dataState;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private Integer reviewUser;

    private Date reviewTime;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
