package com.vosung.auapi.client.dto.requestdto.orgrelationscheme;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改方案
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class UpdateSchemeItemDto implements Serializable{

    private static final long serialVersionUID = -2029369465154117507L;
    @NotNull(message = "方案id不能为空")
    private Integer id;
    @NotNull(message = "方案编码不能为空")
    private String schemeCode;
    @NotNull(message = "数据状态不能为空")
    private String dataState;
}
