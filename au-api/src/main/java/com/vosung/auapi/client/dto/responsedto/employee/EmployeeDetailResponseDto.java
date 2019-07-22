package com.vosung.auapi.client.dto.responsedto.employee;

import com.vosung.auapi.client.dto.requestdto.employee.PapersDto;
import com.vosung.auapi.client.dto.requestdto.employee.PostDto;
import com.vosung.auapi.client.entity.TAuEmployee;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 人员基本信息出参
 * @Author 彬
 * @Date 2019/5/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDetailResponseDto extends TAuEmployee implements Serializable{
    private static final long serialVersionUID = -8693502798742331637L;
    /**
     * 证件列表
     */
    private List<PapersDto> papersList;
    /**
     * 任岗信息列表
     */
    private List<PostDto> postDtoList;
}
