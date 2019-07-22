package com.vosung.auapi.client.dto.requestdto.employee;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 新增员工时，员工任岗列表入参
 * @Author 彬
 * @Date 2019/5/8
 */
@Data
public class PostDto implements Serializable{
    private static final long serialVersionUID = 2063162559753614593L;
    /**
     * 任岗信息id
     */
    private Integer id;
    /**
     * 工作组织--不能为空
     */
    private Integer workOrgId;
    /**
     * 工作组织名：查看详情出参
     */
    private String workOrgName;
    /**
     * 所属部门--不能为空
     */
    private Integer belongDeptId;
    /**
     * 所属部门名：查看详情出参
     */
    private String belongDeptName;
    /**
     * 就任岗位--不能为空
     */
    private Integer directorPositionId;
    /**
     * 任岗名
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
    /**
     * 禁用状态：查询详情接口出参
     */
    private String prohibitState;
    /**
     * 数据状态：任岗信息新增修改，选择返现员工已经任岗list
     */
    private String dataState;
    /**
     * 部门全称：查询详情接口出参
     */
    private String deptFullName;
}
