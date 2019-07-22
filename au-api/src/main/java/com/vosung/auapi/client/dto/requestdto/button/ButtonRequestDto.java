package com.vosung.auapi.client.dto.requestdto.button;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 按钮保存入参
 * @Author 彬
 * @Date 2019/5/22
 */
@Data
public class ButtonRequestDto implements Serializable {
    private static final long serialVersionUID = -4573634962222387435L;

    private Integer id;
    /**
     * 按钮编码
     */
    @NotNull(message = "按钮编码不能为空")
    private String buttonCode;
    /**
     * 按钮名称
     */
    @NotNull(message = "按钮名称不能为空")
    private String buttonName;
    /**
     * 按钮类型
     */
    private String buttonType;
    /**
     * 按钮icon
     */
    private String buttonIcon;
    /**
     * 所属菜单
     */
    @NotNull(message = "所属菜单id不能为空")
    private Integer menuId;
    /**
     * 父按钮id
     */
    private Integer parentId;
}
