package com.vosung.auapi.client.dto.responsedto.orguserrole;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询用户list（授权处接口）
 * @Author 彬
 * @Date 2019/7/1
 */
@Data
public class UserListDto implements Serializable {
    private static final long serialVersionUID = 1630386123556755381L;
    //用户名
    @FiledMessage(name = "用户名称")
    private String name;
    //用户id
    private Integer userId;
    //组织id
    private Integer orgId;
    //组织编码
    @FiledMessage(name = "组织编码")
    private String orgCode;
    //组织名
    @FiledMessage(name = "组织名称")
    private String orgName;
    //角色code
    @FiledMessage(name = "角色编码")
    private String roleCode;
    //角色名称
    @FiledMessage(name = "角色名称")
    private String roleName;
    //角色id
    private Integer roleId;
    //禁用状态
    @FiledMessage(name = "禁用状态")
    private String prohibitState;
}
