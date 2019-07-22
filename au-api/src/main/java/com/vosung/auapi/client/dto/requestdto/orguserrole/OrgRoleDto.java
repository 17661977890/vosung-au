package com.vosung.auapi.client.dto.requestdto.orguserrole;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组织机构和角色对应类
 * @Author 彬
 * @Date 2019/5/5
 */
@Data
public class OrgRoleDto implements Serializable{
    private static final long serialVersionUID = 6065896789508678843L;
    /**
     * 组织id
     */
    private Integer orgId;
    /**
     * 角色id list
     */
    private List<Integer> roleIds;
}
