package com.vosung.auapi.client.dto.requestdto.roleresource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查角色有权菜单下的有权按钮
 * @Author 彬
 * @Date 2019/5/23
 */
@Data
public class ButtonResourceRequestDto implements Serializable {
    private static final long serialVersionUID = -5657799551225221427L;

    /**
     * 角色id：如果为空，就只是查角色下边的有效按钮
     */
    private String roleIds;
    /**
     * 菜单id------------点击菜单时候查菜单里得按钮
     */
    private Integer menuId;
    /**
     * 父按钮id------------点击单元大按钮时候查所有子按钮
     */
    private Integer parentButtonId;
}
