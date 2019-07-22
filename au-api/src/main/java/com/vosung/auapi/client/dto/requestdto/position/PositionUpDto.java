package com.vosung.auapi.client.dto.requestdto.position;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 上级汇报关系
 * @Author 彬
 * @Date 2019/5/13
 */
@Data
public class PositionUpDto implements Serializable {
    private static final long serialVersionUID = -1881533609202223006L;

    private Integer id;
    /**
     * 岗位id:查询出参属性，新增没有
     */
    private Integer positionId;
    /**
     * 上级岗位id
     */
    private Integer superiorPositionId;
    /**
     * 汇报类型（字典项）
     */
    private String reportType;
    /**
     * 生效日期
     */
    private Date effectDate;
    /**
     * 失效日期
     */
    private Date abateDate;
    /**
     * 关系描述
     */
    private String summary;
    /**
     * 是否有效
     */
    private String isEffect;
}
