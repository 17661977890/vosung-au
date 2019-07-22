package com.vosung.auapi.client.dto.responsedto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * bo 接口返回假数据结构
 * @Author 彬
 * @Date 2019/6/20
 */
@Data
public class SpVo implements Serializable {
    private static final long serialVersionUID = 2947550555306317281L;

    private Integer subsystemId;

    private Integer businessObjectId;

    private Integer permissionObjectId;

    private Integer permissionItemId;
    //权限组code
    private String permissionGroupCode;

    private String url;
}
