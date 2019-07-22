package com.vosung.auapi.client.dto.requestdto.orguserrole;

import io.swagger.models.auth.In;
import jdk.nashorn.internal.ir.IdentNode;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户和角色 关系dto
 * @Author 彬
 * @Date 2019/5/11
 */
@Data
public class UserRoleDto implements Serializable {
    private static final long serialVersionUID = -7771198182324224758L;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 角色id list
     */
    private List<Integer> roleIds;
}
