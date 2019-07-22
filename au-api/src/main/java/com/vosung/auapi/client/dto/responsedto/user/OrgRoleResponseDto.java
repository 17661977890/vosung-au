package com.vosung.auapi.client.dto.responsedto.user;

import com.vosung.auapi.client.entity.TAuRole;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询用户详情---组织信息（含角色列表）
 * @Author 彬
 * @Date 2019/6/4
 */
@Data
public class OrgRoleResponseDto implements Serializable {
    private static final long serialVersionUID = 7929243914985681735L;


    private Integer id;

    private String orgCode;

    private String orgName;
    /**
     * 用户组织--对应的角色列表
     */
    private List<TAuRole> list;
}
