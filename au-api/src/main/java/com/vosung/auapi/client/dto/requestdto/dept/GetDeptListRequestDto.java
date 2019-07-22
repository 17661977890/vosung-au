package com.vosung.auapi.client.dto.requestdto.dept;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 获取部门列表入参
 * @Author 彬
 * @Date 2019/4/25
 */
@Data
public class GetDeptListRequestDto implements Serializable{

    private static final long serialVersionUID = 2610626123405244099L;
    private Integer id;
    /**
     * 创建组织
     */
    private Integer createOrgId;
    /**
     * 使用组织
     */
    private Integer useOrgId;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 助记码
     */
    private String mnemonicCode;
    /**
     * 部门全称
     */
    private String deptFullName;
    /**
     * 部门分组id
     */
    private Integer deptGroupId;
    /**
     * 父级部门id
     */
    private Integer parentDeptId;
    /**
     * 生效日期
     */
    private Date effectDate;
    /**
     * 失效日期
     */
    private Date abateDate;
    /**
     * 部门属性
     */
    private String deptProperty;
    /**
     * 数据状态
     */
    private String dataState;
    /**
     * 禁用状态
     */
    private String prohibitState;
    /**
     * 禁用人
     */
    private Integer prohibitUser;
    /**
     * 禁用时间
     */
    private Date prohibitTime;
    /**
     * 审核人
     */
    private Integer reviewUser;
    /**
     * 审核时间
     */
    private Date reviewTime;
    /**
     * 删除标识
     */
    private String isDelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建用户
     */
    private Integer createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改用户
     */
    private Integer updateUser;
    /**
     * 描述
     */
    private String summary;

}
