package com.vosung.auapi.client.dto.responsedto.deptgroup;

import lombok.Data;

import java.util.List;

/**
 * 部门分组树形Tree出参
 * @Author 彬
 * @Date 2019/4/26
 */
@Data
public class DeptGroupTreeDto extends DeptGroupResponseDto {
    private static final long serialVersionUID = -4467233632528465212L;

    /**
     * 部门分组子节点
     */
    private List<DeptGroupTreeDto> children;
    /**
     * 是否拥有子节点
     */
    private Boolean hasChildrens;

}
