package com.vosung.auapi.client.dto.requestdto.orgrelationscheme;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 修改方案的入参
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class UpdateSchemeRequestDto implements Serializable{
    private static final long serialVersionUID = -1775802663405225116L;

    @NotNull(message = "组织方案id字符串集合不能为空")
    private String orgSchemeIds;

}
