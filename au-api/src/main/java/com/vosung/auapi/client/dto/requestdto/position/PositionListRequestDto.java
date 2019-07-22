package com.vosung.auapi.client.dto.requestdto.position;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 查询岗位列表
 * @Author 彬
 * @Date 2019/5/27
 */
@Data
public class PositionListRequestDto implements Serializable {
    private static final long serialVersionUID = -4568179975979977340L;

    private Integer id;

    private String positionCode;

    private String positionName;

    private String mnemonicCode;

    private Integer belongDeptId;

    private String isHr;

    private String isResponsiblePosition;

    private Date effectDate;

    private Date abateDate;

    private String dataState;

    private String summary;

    private String isFromSHr;

    private Integer createOrgId;

    private Integer useOrgId;

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
}
