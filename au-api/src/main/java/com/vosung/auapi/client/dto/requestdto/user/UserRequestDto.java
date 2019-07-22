package com.vosung.auapi.client.dto.requestdto.user;

import com.vosung.auapi.client.dto.requestdto.orguserrole.OrgRoleDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户新增入参
 * @Author 彬
 * @Date 2019/4/29
 */
@Data
public class UserRequestDto implements Serializable{

    private static final long serialVersionUID = -4136215732759816399L;

    private Integer id;
    /**
     * 用户账号
     */
    @NotNull(message = "用户账号不能为空")
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型(内部、外部：暂不知什么逻辑)
     */
    private String userType;
    /**
     * 名称
     */
    @NotNull(message = "用户名称不能为空")
    private String name;
    /**
     * 创建组织（登录用户的所属组织）
     */
    private Integer createOrgId;
    /**
     * 许可分组（暂不使用）
     */
    private Integer permitOrgId;
    /**
     * 所属部门
     */
    private Integer belongDeptId;
    /**
     * 所属岗位（暂不使用）
     */
    private Integer belongStationId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别（0：未知 1：女 2：男）
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 联系员工
     */
    private Integer contactStaff;
    /**
     * 联系类型（目前只有员工：EMPLOYEE）
     */
    private String contactStaffType;
    /**
     * 描述
     */
    private String summary;
    /**
     * 头像
     */
    private String image;
    /**
     * 组织角色关联列表
     */
    private List<OrgRoleDto> orgRoleDtoList;
}
