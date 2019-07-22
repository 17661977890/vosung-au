package com.vosung.auapi.client.dto.requestdto.org;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 修该组织机构数据状态入参类
 * @Author 彬
 * @Date 2019/4/23
 */
@Data
public class UpdateOrgDataStateDto implements Serializable{
    private static final long serialVersionUID = -5385298719425103108L;

    /**
     * 操作类型  1：提交commit 2：撤销abolish 3：审核audit 4：反审核reverse_audit 5:删除delete
     *
     * 提交：数据状态为重新审核和创建、暂存可以提交----提交以后为审核中
     * 撤销：数据状态为审核中的可以进行撤销-----撤销后为重新审核
     * 审核：数据状态为审核中的可以进行审核-----审核后为已审核
     * 反审核：数据状态为已审核和审核中的可以进行反审核-----反审核后为重新审核
     * 删除：数据状态为创建、暂存、重新审核 可以删除，删除之前要检查其他表有没有引用数据！
     */
    @NotNull(message = "组织机构id字符串集合不能为空")
    private String orgIds;


}
