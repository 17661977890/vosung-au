package com.vosung.auapi.client.dto.requestdto.role;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色列表入参
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class RoleListRequestDto implements Serializable {
    private static final long serialVersionUID = -8726405092905986733L;

    private Integer id;

    private String roleCode;

    private String roleName;

    private String roleType;

    private String property;

    private Integer createOrgId;

    private Integer useOrgId;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private String summary;

}
