package com.vosung.auapi.client.dto.requestdto.button;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 按钮列表
 * @Author 彬
 * @Date 2019/5/29
 */
@Data
public class ButtonListRequest implements Serializable {
    private static final long serialVersionUID = 4090411743808057115L;

    private Integer id;

    private String buttonCode;

    private String buttonName;

    private String buttonType;

    private String buttonIcon;

    private Integer menuId;

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
    /**
     * 模糊匹配文本
     */
    private String queryText;
}
