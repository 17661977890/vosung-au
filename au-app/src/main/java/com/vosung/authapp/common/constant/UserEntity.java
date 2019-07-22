package com.vosung.authapp.common.constant;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/6/11 0011
 * @modified By:
 * @version: 1.0
 **/
@Data
@NoArgsConstructor
public class UserEntity {


    private Integer userId;

    private String username;

    private List<String> roles;

    public UserEntity(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UserEntity(Integer userId, String username, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    /**
     * 判断用户是否拥有角色
     * @return
     */
    public boolean isRoles() {
        return !CollectionUtils.isEmpty(this.roles);
    }


    /**
     * 判断是否是超级管理员
     * @return
     */
//    public boolean isAdmin() {
//        if (this.isRoles() && this.getRoles().contains(SysConstant.SUPER_ADMIN_ROLE_CODE)) {
//            return true;
//        }
//        return false;
//    }

}
