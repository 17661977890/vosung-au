package com.vosung.auapi.client.dto.requestdto.employee;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 修改人员信息入参
 * @Author 彬
 * @Date 2019/5/9
 */
@Data
public class EmployeeDetailRequestDto implements Serializable {
    private static final long serialVersionUID = 2123501552917888941L;

    private Integer id;
    /**
     * 员工姓名
     */
    @NotNull(message = "员工姓名不能为空")
    private String employeeName;
    /**
     * 移动电话
     */
    private String phone;
    /**
     * 固定电话
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系地址
     */
    private String contactAddress;
    /**
     * 头像
     */
    private String image;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 民族
     */
    private String nation;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 出生地
     */
    private String originPlace;
    /**
     * 身高
     */
    private String stature;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 最高学历
     */
    private String highestEducation;
    /**
     * 年龄
     */
    private String age;
    /**
     * 婚姻状况
     */
    private String maritalStatus;
    /**
     * 出生日期
     */
    private Date birthDate;
    /**
     * 曾用名
     */
    private String nameUsed;
    /**
     * 参加工作时间
     */
    private Date workDate;
    /**
     * 性别
     */
    private String sex;
    /**
     * 证件列表
     */
    private List<PapersDto> papersDtoList;

}
