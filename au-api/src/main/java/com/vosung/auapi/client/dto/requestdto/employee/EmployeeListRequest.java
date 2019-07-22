package com.vosung.auapi.client.dto.requestdto.employee;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工列表入参
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class EmployeeListRequest implements Serializable {
    private static final long serialVersionUID = -1201329104469431134L;

    private Integer id;

    private String employeeName;

    private String employeeCode;

    private String phone;

    private String mobile;

    private String email;

    private String isHrEmployee;

    private String fromSHr;

    private String contactAddress;

    private String image;

    private String nationality;

    private String nation;

    private String nativePlace;

    private String originPlace;

    private String stature;

    private String bloodType;

    private String highestEducation;

    private String age;

    private String maritalStatus;

    private Date birthDate;

    private String nameUsed;

    private Date workDate;

    private String dataState;

    private String summary;

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

    private Integer createOrgId;

    private Integer useOrgId;

    private String sex;
}
