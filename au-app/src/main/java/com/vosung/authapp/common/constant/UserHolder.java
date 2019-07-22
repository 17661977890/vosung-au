package com.vosung.authapp.common.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 彬
 * @Date 2019/5/30
 */
@Component
public class UserHolder {
    @Autowired
    private RedisUtil redisUtil;

    private static final String USER_HOLDER = "UserHolder";

    public String getUserId(){
        String userId = (String) redisUtil.hget(USER_HOLDER,"userId");
        return userId;
    }

    public String getUserName(){
        String userName = (String) redisUtil.hget(USER_HOLDER,"userName");
        return userName;
    }

    public String getRoles(){
        String roles = (String) redisUtil.hget(USER_HOLDER,"roles");
        return roles;
    }

    public String getIsSuperAdmin(){
        String isSuperAdmin = (String) redisUtil.hget(USER_HOLDER,"isSuperAdmin");
        return isSuperAdmin;
    }
}
