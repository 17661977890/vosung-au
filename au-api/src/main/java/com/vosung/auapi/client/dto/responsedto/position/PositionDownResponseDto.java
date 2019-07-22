package com.vosung.auapi.client.dto.responsedto.position;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 下级汇报
 * @Author 彬
 * @Date 2019/6/19
 */
@Data
public class PositionDownResponseDto implements Serializable {
    private static final long serialVersionUID = 6257623585969552370L;


    private Integer id;

    private Integer positionId;

    private Integer downPositionId;

    /**
     * 下级级岗位名称
     */
    private String positionCode;
    /**
     * 下级级岗位编码
     */
    private String positionName;

    private String createOrgName;

    private String useOrgName;

    private String belongDeptName;

    private String reportType;
    /**
     * 汇报类型名
     */
    private String reportTypeName;

    private Date effectDate;

    private Date abateDate;

    private String summary;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private Integer reviewUser;

    private Date reviewTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private String isEffect;
}
