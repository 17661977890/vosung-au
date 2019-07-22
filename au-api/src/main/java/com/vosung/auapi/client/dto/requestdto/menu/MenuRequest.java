package com.vosung.auapi.client.dto.requestdto.menu;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单列表请求
 * @Author 彬
 * @Date 2019/5/29
 */
@Data
public class MenuRequest implements Serializable {
    private static final long serialVersionUID = -5219913152558211501L;

    private Integer id;

    private String menuCode;

    private String menuName;

    private String menuIcon;

    private Integer parentId;

    private Integer sort;

    private Integer level;

    private String menuPath;

    private String menuComponent;

    private String summary;

    private String dataState;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private Integer reviewUser;

    private Date reviewTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}
