package com.vosung.auapi.client.dto.requestdto.datarule;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 数据规则保存入参类
 * @Author 彬
 * @Date 2019/7/5
 */
@Data
public class DataRuleRequestDto implements Serializable {
    private static final long serialVersionUID = -1458474311209477819L;

    /**
     * 修改传id
     */
    private Integer id;
    /**
     * 数据规则code
     */
    @NotNull(message = "数据规则编码不能为空")
    private String dataRuleCode;
    /**
     * 数据规则name
     */
    @NotNull(message = "数据规则名称不能为空")
    private String dataRuleName;
    /**
     * 业务对象id
     */
    @NotNull(message = "业务对象id不能为空")
    private Integer businessObjectId;
    /**
     * 数据规则条件列表
     */
    private List<DataRuleConditionDto> dataRuleConditionDtoList;
}
