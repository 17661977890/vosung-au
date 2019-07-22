package com.vosung.auapi.client.dto.requestdto.datarule;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改数据规则状态入参
 * @Author 彬
 * @Date 2019/7/8
 */
@Data
public class UpdateDataRuleStatusDto implements Serializable {
    private static final long serialVersionUID = 8468192147015844651L;

    private String dataRuleIds;
}
