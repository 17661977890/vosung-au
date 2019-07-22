package com.vosung.authapp.common.enums;


/**
 * 系统字典枚举类
 * @Author 彬
 */
public enum SysDictEnum {

    DATA_STATE("DATA_STATE","数据状态"),
    COMPANY_FORM("COMPANY_FORM","公司形态"),
    ACCOUNT_ORG("ACCOUNT_ORG","核算组织"),
    OPERATION_ORG("OPERATION_ORG","业务组织"),
    ROLE_ATTRIBUTE("ROLE_ATTRIBUTE","角色属性"),
    ROLE_TYPE("ROLE_TYPE","角色类型"),
    DEPT_PROPERTY("DEPT_PROPERTY","部门属性"),
    CONTACT_OBJECT_TYPE("CONTACT_OBJECT_TYPE","联系对象类型"),
    USER_TYPE("USER_TYPE","用户类型"),
    PERMISSION_GROUP("PERMISSION_GROUP","权限项组"),
    COUNTRY("COUNTRY","国家"),
    CURRENCY("CURRENCY","币别"),
    REPORT_TYPE("REPORT_TYPE","汇报类型"),
    ;
    private String code;

    private String desc;

    SysDictEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
