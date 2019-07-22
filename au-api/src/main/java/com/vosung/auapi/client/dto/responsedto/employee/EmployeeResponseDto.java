package com.vosung.auapi.client.dto.responsedto.employee;

import com.vosung.auapi.client.dto.requestdto.employee.EmployeeCardDto;
import com.vosung.auapi.client.dto.requestdto.employee.PostDto;
import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询员工详情入参类
 * @Author 彬
 * @Date 2019/5/8
 */
@Data
public class EmployeeResponseDto implements Serializable{
    private static final long serialVersionUID = -3528262986774745729L;

    private Integer id;
    /**
     * 员工姓名
     */
    @FiledMessage(name = "员工姓名")
    private String employeeName;
    /**
     * 员工编号
     */
    @FiledMessage(name = "员工编号")
    private String employeeCode;
    /**
     * 移动电话
     */
    @FiledMessage(name = "移动电话")
    private String phone;
    /**
     * 固定电话
     */
    @FiledMessage(name = "固定电话")
    private String mobile;
    /**
     * 邮箱
     */
    @FiledMessage(name = "邮箱")
    private String email;
    /**
     * 联系地址
     */
    @FiledMessage(name = "联系地址")
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
    private Integer createOrgId;
    /**
     * 创建组织名
     */
    @FiledMessage(name = "创建组织")
    private String createOrgName;
    /**
     * 使用组织
     */
    private Integer useOrgId;
    /**
     * 使用组织名
     */
    @FiledMessage(name = "使用组织")
    private String userOrgName;

    /**
     * =========列表需要===========
     */
    //主任岗位
    @FiledMessage(name = "主任岗位")
    private String mainPositionName;
    @FiledMessage(name = "主任岗开始日期")
    private Date directorPositionStartDate;
    @FiledMessage(name = "数据状态")
    private String dataStateName;
    @FiledMessage(name = "禁用状态")
    private String prohibitState;

    private String dataState;

    private String sex;

    /**
     * 员工任岗列表
     */
    private List<PostDto> postDtoList;
    /**
     * 财务信息列表
     */
    private List<EmployeeCardDto> employeeCardDtoList;
}
