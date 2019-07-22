package com.vosung.auapi.client.dto.responsedto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 显示字段
 * @Author 彬
 * @Date 2019/5/24
 */
@Data
public class ViewColumn implements Serializable {
    private static final long serialVersionUID = -9119318006929374592L;
    /**
     * 字段code
     */
    private String columnCode;
    /**
     * 字段名
     */
    private String columnName;
}
