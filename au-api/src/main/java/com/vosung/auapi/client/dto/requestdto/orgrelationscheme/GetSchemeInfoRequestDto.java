package com.vosung.auapi.client.dto.requestdto.orgrelationscheme;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 获取方案详情入参类
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class GetSchemeInfoRequestDto implements Serializable{

    private static final long serialVersionUID = 8693456560521477853L;

    @NotNull(message = "组织机构方案id不能为空")
    private Integer id;
}
