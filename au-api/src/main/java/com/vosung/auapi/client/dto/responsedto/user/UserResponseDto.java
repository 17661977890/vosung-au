package com.vosung.auapi.client.dto.responsedto.user;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户列表出参
 * @Author 彬
 * @Date 2019/5/25
 */
@Data
public class UserResponseDto implements Serializable {
    private static final long serialVersionUID = 2941234849290563364L;

    private Integer id;
    @FiledMessage(name = "用户编码")
    private String username;
    @FiledMessage(name = "用户类型")
    private String userType;
    @FiledMessage(name = "用户名称")
    private String name;

    private Integer belongDeptId;
    @FiledMessage(name = "所属部门")
    private String belongDeptName;
    @FiledMessage(name = "用户说明")
    private String summary;
    @FiledMessage(name = "移动电话")
    private String phone;
    @FiledMessage(name = "禁用状态")
    private String prohibitState;

}
