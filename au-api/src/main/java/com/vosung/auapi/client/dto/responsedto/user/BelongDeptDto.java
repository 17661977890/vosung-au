package com.vosung.auapi.client.dto.responsedto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 所属部门出参
 * @Author 彬
 * @Date 2019/5/11
 */
@Data
public class BelongDeptDto implements Serializable {
    private static final long serialVersionUID = -4388895574386442639L;

    private Integer id;

    private String deptName;
}
