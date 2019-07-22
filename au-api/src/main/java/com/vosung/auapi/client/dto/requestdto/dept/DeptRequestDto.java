package com.vosung.auapi.client.dto.requestdto.dept;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门新增入参类
 * @Author 彬
 * @Date 2019/4/24
 */
@Data
public class DeptRequestDto implements Serializable{
    private static final long serialVersionUID = 7044708400114810162L;

    private Integer id;
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
     * 部门编码（自动生成/或前端编写）
     */
    private String deptCode;
    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不能为空")
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
     * 描述
     */
    private String summary;
}
