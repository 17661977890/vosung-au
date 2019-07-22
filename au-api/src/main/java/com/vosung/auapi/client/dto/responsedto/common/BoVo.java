package com.vosung.auapi.client.dto.responsedto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 值对象  --- 数据组装（子系统授权用）
 * @Author 彬
 * @Date 2019/6/20
 */
@Data
public class BoVo implements Serializable {

    private static final long serialVersionUID = 1467205396569538662L;
    //子系统id
    private Integer subsystemId;
    //业务对象id
    private Integer businessObjectId;
    //权限对象id
    private Integer permissionObjectId;
    //权限项id
    private Integer permissionItemId;
    //权限组code
    private String permissionGroupCode;
    //操作项url
    private String url;
}
