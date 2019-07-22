package com.vosung.auapi.client.dto.responsedto.position;

import com.vosung.auapi.client.entity.TAuPositionRelationDown;
import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查岗位详情出参
 * @Author 彬
 * @Date 2019/5/14
 */
@Data
public class PositionResponseDto implements Serializable {
    private static final long serialVersionUID = 3211981112579964323L;

    private Integer id;
    /**
     * ==========岗位基本信息==========
     */
    //编码
    @FiledMessage(name = "岗位编码")
    private String positionCode;
    //岗位名称
    @FiledMessage(name = "岗位名称")
    private String positionName;
    //助记码
    @FiledMessage(name = "助记码")
    private String mnemonicCode;
    //所属部门
    private Integer belongDeptId;
    //是否负责岗位
    private String isResponsiblePosition;
    //生效日期
    private Date effectDate;
    //失效日期
    private Date abateDate;
    //禁用状态
    @FiledMessage(name = "禁用状态")
    private String prohibitState;
    //数据状态

    private String dataState;
    @FiledMessage(name = "数据状态")
    private String dataSateName;
    //描述
    private String summary;
    //创建组织id
    private Integer createOrgId;
    //使用组织id
    private Integer useOrgId;
    //创建组织名
    @FiledMessage(name = "创建组织")
    private String createOrgName;
    //使用组织名
    @FiledMessage(name = "使用组织")
    private String useOrgName;
    //所属部门名
    @FiledMessage(name = "所属部门")
    private String belongDeptName;
    //部门全称
    @FiledMessage(name = "部门全称")
    private String deptFullName;

    /**
     * ===========上级汇报关系list=============
     */
    private List<PositionUpResponseDto> relationUpList;
    /**
     * ===========下级汇报关系list=============
     */
    private List<PositionDownResponseDto> relationDownList;
}
