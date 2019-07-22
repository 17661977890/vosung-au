package com.vosung.auapi.client.dto.responsedto.orguserrole;

import com.vosung.auapi.client.entity.TAuOrganization;
import com.vosung.auapi.client.entity.TAuRole;
import com.vosung.auapi.client.entity.TAuUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 获取组织下用户（角色）出参
 * @Author 彬
 * @Date 2019/4/30
 */
@Data
public class OrgUserRoleResponseDto implements Serializable{

    private static final long serialVersionUID = 6440933746285899471L;

    /**
     * 用户列表
     */
    private List<TAuUser> userList;
    /**
     * 角色列表
     */
    private List<TAuRole> roleList;
    /**
     * 组织列表
     */
    private List<TAuOrganization> orgList;
}
