package com.vosung.auapi.client.dto.requestdto.permissionitem;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限项列表入参
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class PermissionItemListRequest implements Serializable {
    private static final long serialVersionUID = 4900968528714268085L;

    private Integer id;

    private String permissionCode;

    private String permissionName;

    private String permissionGroupCode;

    private String isSystemInit;

    private String summary;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

}
