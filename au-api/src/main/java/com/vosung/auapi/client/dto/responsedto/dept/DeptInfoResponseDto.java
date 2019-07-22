package com.vosung.auapi.client.dto.responsedto.dept;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查看部门详情出参类
 * @Author 彬
 * @Date 2019/4/25
 */
@Data
public class DeptInfoResponseDto implements Serializable{

    private static final long serialVersionUID = 2704470117149633141L;
    private Integer id;
    /**
     * 创建组织
     */
    private Integer createOrgId;
    /**
     * 创建组织名称
     */
    @FiledMessage(name = "创建组织")
    private String createOrgName;
    /**
     * 使用组织
     */
    private Integer useOrgId;
    /**
     * 使用组织名称
     */
    @FiledMessage(name = "使用组织")
    private String useOrgName;
    /**
     * 部门编码
     */
    @FiledMessage(name = "部门编码")
    private String deptCode;
    /**
     * 部门名称
     */
    @FiledMessage(name = "部门名称")
    private String deptName;
    /**
     * 助记码
     */
    @FiledMessage(name = "助记码")
    private String mnemonicCode;
    /**
     * 部门全称
     */
    @FiledMessage(name = "部门全称")
    private String deptFullName;
    /**
     * 部门分组id
     */
    private Integer deptGroupId;
    /**
     * 部门分组名称
     */
    private String deptGroupName;

    /**
     * 父级部门id
     */
    private Integer parentDeptId;
    /**
     * 父级部门名称
     */
    @FiledMessage(name = "上级部门")
    private String parentDeptName;

    /**
     * 生效日期
     */
    private Date effectDate;
    /**
     * 失效日期
     */
    private Date abateDate;
    /**
     * 部门属性code
     */
    private String deptProperty;
    /**
     * 部门属性名称
     */
    private String deptPropertyName;
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
