package com.vosung.auapi.client.dto.requestdto.employee;

import com.vosung.auapi.client.entity.TAuPapers;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 证件dto
 * @Author 彬
 * @Date 2019/5/9
 */
@Data
public class PapersDto implements Serializable {
    private static final long serialVersionUID = -2860005273431642219L;

    private Integer id;
    /**
     * 国家代码
     */
    private String country;
    /**
     * 证件类型
     */
    private String papersType;
    /**
     * 证件号
     */
    private String papersNo;
    /**
     * 是否主证件
     */
    private String isMainPapers;
    /**
     * 是否有效
     */
    private String isEffect;
    /**
     * 生效日期
     */
    private Date effectiveDate;
    /**
     * 失效日期
     */
    private Date expiryDate;
    /**
     * 描述
     */
    private String summary;
    /**
     * 国家名：-----字典项
     */
    private String countryName;
    /**
     * 证件类型名：---字典项
     */
    private String papersTypeName;
}
