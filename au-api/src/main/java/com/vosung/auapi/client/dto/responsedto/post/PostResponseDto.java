package com.vosung.auapi.client.dto.responsedto.post;

import com.vosung.auapi.client.dto.requestdto.employee.PostDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 员工任岗明细详情出参类（根据任岗id查详情）
 * @Author 彬
 * @Date 2019/5/10
 */
@Data
public class PostResponseDto implements Serializable{

    private static final long serialVersionUID = 4663903480738641927L;

    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 员工名
     */
    private String employeeName;
    /**
     * 员工编号
     */
    private String employeeCode;
    /**
     * 工作组织（使用组织/创建组织）
     */
    private Integer workOrgId;
    /**
     * 创建组织名
     */
    private String createOrgName;
    /**
     * 使用组织名
     */
    private String useOrgName;
    /**
     * 所属部门
     */
    private Integer belongDeptId;
    /**
     * 部门名称
     */
    private String belongDeptName;
    /**
     * 任岗id
     */
    private Integer directorPositionId;
    /**
     * 任岗名称
     */
    private String directorPositionName;
    /**
     * 任岗开始时间
     */
    private Date directorPositionStartDate;
    /**
     * 是否主任岗
     */
    private String isMainPosition;

    //==========员工信息================
    /**
     * 性别(0:未知1：女2：男)
     */
    private String sex;
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


    //==========任岗列表信息================
    /**
     * 员工任岗列表(根据组织id和员工id查询的结果)
     */
    private List<PostDto> postDtoList;
}
