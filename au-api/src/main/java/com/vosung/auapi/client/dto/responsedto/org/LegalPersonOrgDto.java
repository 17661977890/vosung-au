package com.vosung.auapi.client.dto.responsedto.org;

import lombok.Data;

import java.io.Serializable;

/**
 * 法人组织（所属法人）
 * @Author 彬
 * @Date 2019/5/11
 */
@Data
public class LegalPersonOrgDto implements Serializable{
    private static final long serialVersionUID = -7945000095219611435L;

    private Integer id;

    private String  orgName;
}
