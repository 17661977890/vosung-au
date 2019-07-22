package com.vosung.auapi.client.dto.requestdto.orguserrole;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取组织下用户（角色）入参
 * @Author 彬
 * @Date 2019/4/30
 */
@Data
public class GetUserRoleListRequestDto implements Serializable{

    private static final long serialVersionUID = 7147407109504585336L;

    private Integer orgId;

    private Integer userId;
}
