package com.vosung.auapi.client.dto.requestdto.post;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 员工任岗信息保存入参类
 * @Author 彬
 * @Date 2019/5/9
 */
@Data
public class PostRequestDto implements Serializable {
    private static final long serialVersionUID = -6405273958762967102L;
    /**
     * 无新增，有修改
     */
    private Integer id;
    /**
     * 员工id
     */
    @NotNull(message = "员工id不能为空")
    private Integer employeeId;
    /**
     * 工作组织（使用组织）----前端通过这个组织id查对应任职部门和岗位
     */
    @NotNull(message = "工作组织id不能为空")
    private Integer workOrgId;
    /**
     * 所属部门---组织下部门（已审核）
     */
    private Integer belongDeptId;
    /**
     * 任岗id----组织部门下的岗位（已审核）
     */
    @NotNull(message = "就任岗位id不能为空")
    private Integer directorPositionId;
    /**
     * 任岗开始时间
     */
    @NotNull(message = "任岗开始时间不能为空")
    private Date directorPositionStartDate;
    /**
     * 是否主任岗
     */
    private String isMainPosition;

}
