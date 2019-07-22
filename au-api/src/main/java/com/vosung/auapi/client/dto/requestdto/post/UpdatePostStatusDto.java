package com.vosung.auapi.client.dto.requestdto.post;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改员工任岗数据状态等入参
 * @Author 彬
 * @Date 2019/5/9
 */
@Data
public class UpdatePostStatusDto implements Serializable {
    private static final long serialVersionUID = 1878062094289196581L;

    /**
     * 约定修改类操作类型  1：提交commit 2：撤销abolish 3：审核audit 4：反审核reverse_audit 5:删除delete 6：禁用forbidden 7：反禁用un_forbidden
     *
     * 提交：数据状态为重新审核和创建、暂存可以提交----提交以后为审核中
     * 撤销：数据状态为审核中的可以进行撤销-----撤销后为重新审核
     * 审核：数据状态为审核中的可以进行审核-----审核后为已审核
     * 反审核：数据状态为已审核和审核中的可以进行反审核-----反审核后为重新审核
     * 删除：数据状态为创建、暂存、重新审核 可以删除，删除之前要检查其他表有没有引用数据！
     */
    /**
     * 任岗id 串（逗号分隔）
     */
    @NotNull(message = "操作的员工任岗id字符串集合不能为空")
    private String postIds;
}
