package com.vosung.auapi.client.dto.responsedto.orgrelation;

import com.vosung.auapi.client.dto.responsedto.deptgroup.DeptGroupTreeDto;
import lombok.Data;

import java.util.List;

/**
 * 树公共类
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class TreeDto<T> {

    /**
     * 树子节点
     */
    private List<T> children;
    /**
     * 是否有子节点
     */
    private boolean hasChildren;

}
