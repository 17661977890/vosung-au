package com.vosung.auapi.client.dto.requestdto.position;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 保存岗位入参
 * @Author 彬
 * @Date 2019/5/13
 */
@Data
public class PositionRequestDto implements Serializable {
    private static final long serialVersionUID = -9014108773059948089L;

    private Integer id;
    /**
     * 创建组织
     */
    @NotNull(message = "创建组织不能为空")
    private Integer createOrgId;
    /**
     * 使用组织
     */
    @NotNull(message = "创建组织不能为空")
    private Integer useOrgId;
    /**
     * 岗位编码（自动生成）
     */
    private String positionCode;
    /**
     * 岗位名称
     */
    @NotNull(message = "岗位名称不能为空")
    private String positionName;
    /**
     * 助记码
     */
    private String mnemonicCode;
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
     * 上级岗位关系
     */
    private List<PositionUpDto> positionUpDtoList;

    /**
     * 岗位角色目前不考虑
     */

}
