package com.vosung.auapi.client.dto.requestdto.rolepermission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 角色业务对象授权入参
 * @Author 彬
 * @Date 2019/5/28
 */
@Data
public class RoleBuObjRequestDto implements Serializable {
    private static final long serialVersionUID = -6623405921635558014L;

    private Integer roleId;

    private List<BusinessObjectDto> businessObjectList;
}
