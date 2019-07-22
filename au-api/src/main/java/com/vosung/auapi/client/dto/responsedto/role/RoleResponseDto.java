package com.vosung.auapi.client.dto.responsedto.role;
import com.vosung.auapi.client.entity.TAuUser;
import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询角色详情出参
 * @Author 彬
 * @Date 2019/5/6
 */
@Data
public class RoleResponseDto implements Serializable {
    private static final long serialVersionUID = -8566720036457807536L;
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 角色编码
     */
    @FiledMessage(name = "角色编码")
    private String roleCode;
    /**
     * 角色名称
     */
    @FiledMessage(name = "角色姓名")
    private String roleName;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 角色类型名称
     */
    @FiledMessage(name = "角色类型")
    private String roleTypeName;
    /**
     * 角色属性
     */
    private String property;
    /**
     * 角色属性名称
     */
    @FiledMessage(name = "角色属性")
    private String propertyName;
    /**
     * 所属组织
     */
    private Integer useOrgId;
    /**
     * 所属组织名
     */
    @FiledMessage(name = "所属组织")
    private String useOrgName;
    /**
     * 描述
     */
    @FiledMessage(name = "角色描述")
    private String summary;
    /**
     * 该角色下得用户列表
     */
    private List<TAuUser> userList;
}
