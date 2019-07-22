package com.vosung.auapi.client.dto.responsedto.orguserrole;

import lombok.Data;

import java.io.Serializable;

/**
 * 保存组织用户关系，数量变化情况
 * @Author 彬
 * @Date 2019/5/13
 */
@Data
public class CountDto implements Serializable {
    private static final long serialVersionUID = -998698711806979211L;
    /**
     * 新增数
     */
    private Integer addCount;
    /**
     * 删除数
     */
    private Integer deleteCount;
}
