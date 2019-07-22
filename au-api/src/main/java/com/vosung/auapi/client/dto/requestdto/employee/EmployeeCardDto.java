package com.vosung.auapi.client.dto.requestdto.employee;

import lombok.Data;

import java.io.Serializable;

/**
 * 员工新增时，财务信息列表
 * @Author 彬
 * @Date 2019/5/8
 */
@Data
public class EmployeeCardDto implements Serializable{
    private static final long serialVersionUID = 1852844555221918417L;

    private Integer id;
    /**
     * 国家
     */
    private String country;
    /**
     * 国家名称
     */
    private String countryName;
    /**
     * 开户银行
     */
    private String bankOfDeposit;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 币别
     */
    private String currency;
    /**
     * 币别名称
     */
    private String currencyName;
    /**
     * 是否默认
     */
    private String isDefault;
    /**
     * 描述
     */
    private String summary;
}
