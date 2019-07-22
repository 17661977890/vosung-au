package com.vosung.auapi.client.dto.requestdto.roleresource;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 * @Author 彬
 * @Date 2019/5/16
 */
@Data
public class MenuButton implements Serializable {
    private static final long serialVersionUID = 7355682766014021729L;
    /**
     * 菜单id
     */
    private Integer menuId;
    /**
     * 按钮id list
     */
    private List<Integer> buttonIds;
}
