package com.vosung.auapi.client.dto.responsedto.post;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;


/**
 * 员工任岗明细详情出参类---（查岗位下的员工任岗列表）
 * @Author 彬
 * @Date 2019/5/10
 */
@Data
public class PostListDto implements Serializable{

    private static final long serialVersionUID = 4663903480738641927L;
    /**
     * ===========任岗表信息=========
     */
    /**
     * 任岗id
     */
    private Integer id;
    /**
     * 员工id
     */
    private Integer employeeId;
    /**
     * 员工名
     */
    @FiledMessage(name = "员工姓名")
    private String employeeName;
    /**
     * 员工编号
     */
    @FiledMessage(name = "员工编号")
    private String employeeCode;
    /**
     * 工作组织（使用组织/创建组织）
     */
    private Integer workOrgId;
    /**
     * 创建组织名
     */
    @FiledMessage(name = "创建组织")
    private String createOrgName;
    /**
     * 使用组织名
     */
    @FiledMessage(name = "使用组织")
    private String useOrgName;
    /**
     * 所属部门
     */
    private Integer belongDeptId;
    /**
     * 部门名称
     */
    @FiledMessage(name = "所属部门")
    private String belongDeptName;
    /**
     * 任岗id
     */
    private Integer directorPositionId;
    /**
     * 任岗名称
     */
    @FiledMessage(name = "就任岗位")
    private String directorPositionName;
    /**
     * 数据状态
     */
    private String dataState;
    @FiledMessage(name = "数据状态")
    private String dataStateName;
    /**
     * 禁用状态
     */
    @FiledMessage(name = "禁用状态")
    private String prohibitState;
    /**
     * ============员工表信息==========
     */
    //hr职员
    @FiledMessage(name = "hr职员任职")
    private String isHrEmployee;
    //来源于shr
    @FiledMessage(name = "来源于hr")
    private String fromSHr;

}
