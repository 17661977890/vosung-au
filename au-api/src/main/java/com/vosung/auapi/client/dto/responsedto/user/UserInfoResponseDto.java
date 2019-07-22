package com.vosung.auapi.client.dto.responsedto.user;

import com.vosung.auapi.client.dto.responsedto.orguserrole.OrgUserRoleResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询用户详情出参
 * @Author 彬
 * @Date 2019/5/5
 */
@Data
public class UserInfoResponseDto implements Serializable {
    private static final long serialVersionUID = 4137024785805812306L;

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
     * 联系对象
     */
    private Integer contactStaff;
    /**
     * 联系对象名
     */
    private String contactStaffName;
    /**
     * 联系对象类型（待确定：目前员工）
     */
    private String contactStaffType;
    /**
     * 描述（用户说明）
     */
    private String summary;
    /**
     * 用户头像
     */
    private String image;
    /**
     * 反禁用人
     */
    private Integer liftBanBy;
    /**
     * 反禁用时间
     */
    private Date liftBanTime;
    /**
     * 数据状态
     */
    private String dataState;
    /**
     * 禁用状态
     */
    private String prohibitState;
    /**
     * 审核用户
     */
    private Integer reviewUser;
    /**
     * 审核时间
     */
    private Date reviewTime;
    /**
     * 禁用用户
     */
    private Integer prohibitUser;
    /**
     * 禁用时间
     */
    private Date prohibitTime;
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
     * 组织用户角色关系
     */
    private List<OrgRoleResponseDto> list;
}
