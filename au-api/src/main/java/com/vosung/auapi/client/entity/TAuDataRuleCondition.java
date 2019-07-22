package com.vosung.auapi.client.entity;

import java.util.Date;

public class TAuDataRuleCondition {
    private Integer id;

    private Integer dataRuleId;

    private String tableName;

    private Integer contactTableId;

    private String contactColumn;

    private String leftBrackets;

    private String rightBrackets;

    private String columnName;

    private String compare;

    private String conditionValue;

    private String logic;

    private String sort;

    private String remark;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private String columnCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataRuleId() {
        return dataRuleId;
    }

    public void setDataRuleId(Integer dataRuleId) {
        this.dataRuleId = dataRuleId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getContactTableId() {
        return contactTableId;
    }

    public void setContactTableId(Integer contactTableId) {
        this.contactTableId = contactTableId;
    }

    public String getContactColumn() {
        return contactColumn;
    }

    public void setContactColumn(String contactColumn) {
        this.contactColumn = contactColumn == null ? null : contactColumn.trim();
    }

    public String getLeftBrackets() {
        return leftBrackets;
    }

    public void setLeftBrackets(String leftBrackets) {
        this.leftBrackets = leftBrackets == null ? null : leftBrackets.trim();
    }

    public String getRightBrackets() {
        return rightBrackets;
    }

    public void setRightBrackets(String rightBrackets) {
        this.rightBrackets = rightBrackets == null ? null : rightBrackets.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare == null ? null : compare.trim();
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue == null ? null : conditionValue.trim();
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic == null ? null : logic.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getProhibitState() {
        return prohibitState;
    }

    public void setProhibitState(String prohibitState) {
        this.prohibitState = prohibitState == null ? null : prohibitState.trim();
    }

    public Integer getProhibitUser() {
        return prohibitUser;
    }

    public void setProhibitUser(Integer prohibitUser) {
        this.prohibitUser = prohibitUser;
    }

    public Date getProhibitTime() {
        return prohibitTime;
    }

    public void setProhibitTime(Date prohibitTime) {
        this.prohibitTime = prohibitTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode == null ? null : columnCode.trim();
    }
}