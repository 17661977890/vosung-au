package com.vosung.auapi.client.dto.responsedto.button;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 彬
 * @Date 2019/5/29
 */
@Data
public class ButtonResponseDto implements Serializable {
    private static final long serialVersionUID = 3906961274205658901L;

    private Integer id;
    @FiledMessage(name = "按钮编码")
    private String buttonCode;
    @FiledMessage(name = "按钮名称")
    private String buttonName;
    @FiledMessage(name = "按钮类型")
    private String buttonType;

    private String buttonIcon;

    private Integer menuId;
    @FiledMessage(name = "所属菜单")
    private String menuName;

    private String dataState;

    @FiledMessage(name = "数据状态")
    private String dataStateName;
    @FiledMessage(name = "禁用状态")
    private String prohibitState;
}
