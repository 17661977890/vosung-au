package com.vosung.auapi.client.dto.responsedto.deptgroup;

import lombok.Data;

import java.io.Serializable;

/**
 * 查看部门分组详情出参
 * @Author 彬
 * @Date 2019/4/26
 */
@Data
public class DeptGroupResponseDto implements Serializable{
    private static final long serialVersionUID = -7394297138806417167L;

    private Integer id;
    /**
     * 上级分组信息
     */
    private Integer parentGroupId;
    /**
     * 上级分组名称
     */
    private String parentGroupName;
    /**
     * 部门分组名
     */
    private String deptGroupName;
    /**
     * 部门编码
     */
    private String deptGroupCode;
    /**
     * 描述
     */
    private String summary;
}
