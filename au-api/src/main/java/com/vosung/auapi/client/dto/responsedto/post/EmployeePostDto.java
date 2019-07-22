package com.vosung.auapi.client.dto.responsedto.post;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工|任岗=》关联查询dto
 * @Author 彬
 * @Date 2019/5/10
 */
@ToString
@Data
public class EmployeePostDto implements Serializable{
    private static final long serialVersionUID = -3999068251189506743L;
    /**
     * 任岗数据
     */
    private Integer id;

    private Integer employeeId;

    private Integer workOrgId;

    private Integer belongDeptId;

    private Integer directorPositionId;

    private Date directorPositionStartDate;

    private String isMainPosition;

    private String dataState;

    private String prohibitState;

    /**
     * 员工数据
     */
    private String employeeName;

    private String employeeCode;

    private String phone;

    private String mobile;

    private String email;

    private String isHrEmployee;

    private String fromSHr;

    private String contactAddress;

    private String image;

}
