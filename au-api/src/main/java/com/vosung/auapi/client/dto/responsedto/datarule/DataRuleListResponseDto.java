package com.vosung.auapi.client.dto.responsedto.datarule;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * 列表出参
 * @Author 彬
 * @Date 2019/7/8
 */
@Data
public class DataRuleListResponseDto implements Serializable {
    private static final long serialVersionUID = 3088623966574518066L;

    private Integer id;

    @FiledMessage(name = "编码")
    private String dataRuleCode;

    @FiledMessage(name = "名称")
    private String dataRuleName;

    private Integer businessObjectId;

    @FiledMessage(name = "业务对象")
    private String businessObjectName;

    @FiledMessage(name = "系统预设")
    private String isSystemInit;

    @FiledMessage(name = "禁用状态")
    private String prohibitState;
}
