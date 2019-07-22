package com.vosung.auapi.client.dto.requestdto.rolesubsystem;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 子系统授权
 * @Author 彬
 * @Date 2019/6/18
 */
@Data
public class RoleSubsystemRequestDto implements Serializable {
    private static final long serialVersionUID = 2240240245774310319L;

    private Integer roleId;
    /**
     * 子系统授权list
     */
    private List<SubsystemDto> subsystemDtos;
}
