package com.vosung.auapi.client.dto.responsedto.orguserrole;

import com.vosung.auapi.client.entity.TAuRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色列表
 * @Author 彬
 * @Date 2019/6/4
 */
@Data
public class UserRoleResponseDto implements Serializable {
    private static final long serialVersionUID = -1578831716320797159L;

    private Integer id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 创建组织
     */
    private Integer createOrgId;
    /**
     * 所属部门
     */
    private Integer belongDeptId;
    /**
     * 所属部门名
     */
    private String belongDeptName;
    /**
     * 所属岗位id
     */
    private Integer belongStationId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 数据状态
     */
    private String dataState;
    /**
     * 禁用状态
     */
    private String prohibitState;

    /**
     * 组织下用户对应的角色列表
     */
    private List<TAuRole> list;
}
