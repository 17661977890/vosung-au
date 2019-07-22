package com.vosung.auapi.client.dto.requestdto.employee;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 保存员工信息入参类
 * @Author 彬
 * @Date 2019/5/8
 */
@Data
public class EmployeeRequestDto implements Serializable{
    private static final long serialVersionUID = -3528262986774745729L;

    private Integer id;
    /**
     * 员工姓名
     */
    @NotNull(message = "员工姓名不能为空")
    private String employeeName;
    /**
     * 员工编号
     */
    @NotNull(message = "员工编号不能为空")
    private String employeeCode;
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
     * 描述
     */
    private String summary;
    /**
     * 创建组织
     */
    @NotNull(message = "创建组织不能为空")
    private Integer createOrgId;
    /**
     * 使用组织
     */
    @NotNull(message = "使用组织不能为空")
    private Integer useOrgId;
    /**
     * 员工任岗列表
     */
    private List<PostDto> postDtoList;
    /**
     * 财务信息列表
     */
    private List<EmployeeCardDto> employeeCardDtoList;
}
