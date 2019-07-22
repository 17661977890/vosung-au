package com.vosung.auapi.client.dto.requestdto.datarule;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据规则条件dto
 * @Author 彬
 * @Date 2019/7/5
 */
@Data
public class DataRuleConditionDto implements Serializable {
    private static final long serialVersionUID = -6508760988121374788L;

    /**
     * 修改有值(详情出参有值)
     */
    private Integer id;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 关联表id
     */
    private Integer contactTableId;
    /**
     * 关联字段
     */
    private String contactColumn;
    /**
     * 左边括号
     */
    private String leftBrackets;
    /**
     * 右边括号
     */
    private String rightBrackets;
    /**
     * 字段名称（保存冗余字段显示用）
     */
    private String columnName;
    /**
     * 字段code
     */
    private String columnCode;
    /**
     * 比较关系（枚举项）
     */
    private String compare;
    /**
     * 值
     */
    private String conditionValue;
    /**
     * 逻辑（并且and | 或者or）
     */
    private String logic;
    /**
     * 排序
     */
    private String sort;

}
