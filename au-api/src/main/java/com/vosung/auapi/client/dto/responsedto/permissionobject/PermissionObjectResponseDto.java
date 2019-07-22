package com.vosung.auapi.client.dto.responsedto.permissionobject;

import com.vosung.auapi.client.entity.TAuPermissionItem;
import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 保存权限对象入参类
 * @Author 彬
 * @Date 2019/5/6
 */
@Data
public class PermissionObjectResponseDto implements Serializable{
    private static final long serialVersionUID = 757626689122591226L;

    private Integer id;
    /**
     * 子系统Id
     */
    private Integer subsystemId;
    /**
     * 子系统名称---需调bo接口
     */
    private String subsystemName;
    /**
     * 权限对象编码
     */
    @FiledMessage(name = "权限对象编码")
    private String permissionObjectCode;
    /**
     * 权限对象名称
     */
    @FiledMessage(name = "权限对象名称")
    private String permissionObjectName;

    @FiledMessage(name = "是否系统预置")
    private String isSystemInit;
    /**
     * 关联权限项
     */
    private List<TAuPermissionItem> permissionItemList;
}
