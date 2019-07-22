package com.vosung.auapi.client.dto.requestdto.permissionobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *权限对象列表入参
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class PermissionObjectRequest implements Serializable {
    private static final long serialVersionUID = -9000888867175661233L;

    private Integer id;

    private Integer subsystemId;

    private String permissionObjectCode;

    private String permissionObjectName;

    private String isSystemInit;

    private String remark;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
