package com.vosung.auapi.client.dto.requestdto.orgrelation;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class DeleteOrgRelationRequestDto implements Serializable{

    private static final long serialVersionUID = 7277716614075722333L;
    @NotNull(message = "组织关系id不能为空")
    private Integer id;
}
