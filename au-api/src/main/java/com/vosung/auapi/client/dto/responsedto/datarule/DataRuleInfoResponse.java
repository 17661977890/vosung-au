package com.vosung.auapi.client.dto.responsedto.datarule;

import com.vosung.auapi.client.dto.requestdto.datarule.DataRuleConditionDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 数据规则详情出参类
 * @Author 彬
 * @Date 2019/7/8
 */
@Data
public class DataRuleInfoResponse implements Serializable {
    private static final long serialVersionUID = 7688972633823365110L;

    /**
     * 修改传id
     */
    private Integer id;
    /**
     * 数据规则code
     */
    private String dataRuleCode;
    /**
     * 数据规则name
     */
    private String dataRuleName;
    /**
     * 业务对象id
     */
    private Integer businessObjectId;
    /**
     * 业务对象名称
     */
    private String businessObjectName;
    /**
     * 数据规则条件列表
     */
    private List<DataRuleConditionDto> dataRuleConditionDtoList;
}
