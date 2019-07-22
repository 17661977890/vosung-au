package com.vosung.auapi.client.dto.responsedto.position;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 上级汇报关系
 * @Author 彬
 * @Date 2019/6/19
 */
@Data
public class PositionUpResponseDto implements Serializable{
    private static final long serialVersionUID = -2425467232074362189L;

    private Integer id;

    private Integer positionId;

    private Integer superiorPositionId;
    /**
     * 上级岗位名称
     */
    private String positionCode;
    /**
     * 上级岗位编码
     */
    private String positionName;
    /**
     * 创建组织
     */
    private String createOrgName;
    /**
     * 使用组织
     */
    private String useOrgName;
    /**
     * 所属部门
     */
    private String belongDeptName;

    private String reportType;
    /**
     * 汇报类型名
     */
    private String reportTypeName;

    private Date effectDate;

    private Date abateDate;

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

    private String isEffect;


}
