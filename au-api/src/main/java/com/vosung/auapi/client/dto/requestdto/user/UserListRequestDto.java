package com.vosung.auapi.client.dto.requestdto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查用户列表入参
 * @Author 彬
 * @Date 2019/5/25
 */
@Data
public class UserListRequestDto implements Serializable {
    private static final long serialVersionUID = 946361157222253026L;

    private Integer id;

    private String username;

    private String password;

    private String userType;

    private String name;

    private Integer createOrgId;

    private Integer permitOrgId;

    private Integer belongDeptId;

    private Integer belongStationId;

    private String email;

    private String sex;

    private String phone;

    private Integer contactStaff;

    private String contactStaffType;

    private String summary;

    private String image;

    private Integer liftBanBy;

    private Date liftBanTime;

    private String dataState;

    private String prohibitState;

    private Integer reviewUser;

    private Date reviewTime;

    private Integer prohibitUser;

    private Date prohibitTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
