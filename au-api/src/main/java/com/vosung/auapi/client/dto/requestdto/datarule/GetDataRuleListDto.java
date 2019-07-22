package com.vosung.auapi.client.dto.requestdto.datarule;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查数据规则列表入参类
 * @Author 彬
 * @Date 2019/7/8
 */
@Data
public class GetDataRuleListDto implements Serializable {

    private static final long serialVersionUID = 3323351566147836437L;

    private Integer id;

    private String dataRuleCode;

    private String dataRuleName;

    private Integer businessObjectId;

    private String isSystemInit;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
}
