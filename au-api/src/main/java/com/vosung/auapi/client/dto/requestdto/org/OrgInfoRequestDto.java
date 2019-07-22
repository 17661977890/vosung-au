package com.vosung.auapi.client.dto.requestdto.org;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 组织机构查看详情入参
 * @Author 彬
 * @Date 2019/4/24
 */
@Data
public class OrgInfoRequestDto implements Serializable{

    private static final long serialVersionUID = 4098472570615935280L;
    /**
     * 组织机构id
     */
    @NotNull(message = "组织机构id不能为空")
    private Integer id;
}
