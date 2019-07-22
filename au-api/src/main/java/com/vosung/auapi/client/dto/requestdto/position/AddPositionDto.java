package com.vosung.auapi.client.dto.requestdto.position;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 添加岗位入参（岗位汇报体系中）
 * @Author 彬
 * @Date 2019/5/13
 */
@Data
public class AddPositionDto implements Serializable {
    private static final long serialVersionUID = -9014108773059948089L;

    /**
     * 创建组织----导航栏选择
     */
    @NotNull(message = "创建组织不能为空")
    private Integer createOrgId;
    /**
     * 使用组织
     */
    @NotNull(message = "使用组织不能为空")
    private Integer useOrgId;
    /**
     * 岗位名称
     */
    @NotNull(message = "岗位名称不能为空")
    private String positionName;
    /**
     * 所属部门（组织下的，前端回调接口查组织下的部门）
     */
    @NotNull(message = "所属部门不能为空")
    private Integer belongDeptId;
    /**
     * 是否负责人岗位
     */
    private String isResponsiblePosition;
    /**
     * 生效日期
     */
    @NotNull(message = "生效日期不能为空")
    private Date effectDate;
    /**
     * 失效日期
     */
    @NotNull(message = "失效日期不能为空")
    private Date abateDate;
    /**
     * 描述
     */
    private String summary;
    /**
     * 上级岗位id
     */
    private Integer superiorPositionId;
    /**
     * 汇报类型（字典项）----如果没有此值，只是新增岗位，不会有上下级汇报关系，上级id前端也不传
     */
    private String reportType;

}
