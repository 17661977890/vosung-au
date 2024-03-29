package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAuPermissionObjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuPermissionObjectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdIsNull() {
            addCriterion("subsystem_id is null");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdIsNotNull() {
            addCriterion("subsystem_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdEqualTo(Integer value) {
            addCriterion("subsystem_id =", value, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdNotEqualTo(Integer value) {
            addCriterion("subsystem_id <>", value, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdGreaterThan(Integer value) {
            addCriterion("subsystem_id >", value, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("subsystem_id >=", value, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdLessThan(Integer value) {
            addCriterion("subsystem_id <", value, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdLessThanOrEqualTo(Integer value) {
            addCriterion("subsystem_id <=", value, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdIn(List<Integer> values) {
            addCriterion("subsystem_id in", values, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdNotIn(List<Integer> values) {
            addCriterion("subsystem_id not in", values, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdBetween(Integer value1, Integer value2) {
            addCriterion("subsystem_id between", value1, value2, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andSubsystemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("subsystem_id not between", value1, value2, "subsystemId");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeIsNull() {
            addCriterion("permission_object_code is null");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeIsNotNull() {
            addCriterion("permission_object_code is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeEqualTo(String value) {
            addCriterion("permission_object_code =", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeNotEqualTo(String value) {
            addCriterion("permission_object_code <>", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeGreaterThan(String value) {
            addCriterion("permission_object_code >", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("permission_object_code >=", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeLessThan(String value) {
            addCriterion("permission_object_code <", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeLessThanOrEqualTo(String value) {
            addCriterion("permission_object_code <=", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeLike(String value) {
            addCriterion("permission_object_code like", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeNotLike(String value) {
            addCriterion("permission_object_code not like", value, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeIn(List<String> values) {
            addCriterion("permission_object_code in", values, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeNotIn(List<String> values) {
            addCriterion("permission_object_code not in", values, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeBetween(String value1, String value2) {
            addCriterion("permission_object_code between", value1, value2, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectCodeNotBetween(String value1, String value2) {
            addCriterion("permission_object_code not between", value1, value2, "permissionObjectCode");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameIsNull() {
            addCriterion("permission_object_name is null");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameIsNotNull() {
            addCriterion("permission_object_name is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameEqualTo(String value) {
            addCriterion("permission_object_name =", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameNotEqualTo(String value) {
            addCriterion("permission_object_name <>", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameGreaterThan(String value) {
            addCriterion("permission_object_name >", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("permission_object_name >=", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameLessThan(String value) {
            addCriterion("permission_object_name <", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameLessThanOrEqualTo(String value) {
            addCriterion("permission_object_name <=", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameLike(String value) {
            addCriterion("permission_object_name like", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameNotLike(String value) {
            addCriterion("permission_object_name not like", value, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameIn(List<String> values) {
            addCriterion("permission_object_name in", values, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameNotIn(List<String> values) {
            addCriterion("permission_object_name not in", values, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameBetween(String value1, String value2) {
            addCriterion("permission_object_name between", value1, value2, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andPermissionObjectNameNotBetween(String value1, String value2) {
            addCriterion("permission_object_name not between", value1, value2, "permissionObjectName");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitIsNull() {
            addCriterion("is_system_init is null");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitIsNotNull() {
            addCriterion("is_system_init is not null");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitEqualTo(String value) {
            addCriterion("is_system_init =", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitNotEqualTo(String value) {
            addCriterion("is_system_init <>", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitGreaterThan(String value) {
            addCriterion("is_system_init >", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitGreaterThanOrEqualTo(String value) {
            addCriterion("is_system_init >=", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitLessThan(String value) {
            addCriterion("is_system_init <", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitLessThanOrEqualTo(String value) {
            addCriterion("is_system_init <=", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitLike(String value) {
            addCriterion("is_system_init like", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitNotLike(String value) {
            addCriterion("is_system_init not like", value, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitIn(List<String> values) {
            addCriterion("is_system_init in", values, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitNotIn(List<String> values) {
            addCriterion("is_system_init not in", values, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitBetween(String value1, String value2) {
            addCriterion("is_system_init between", value1, value2, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andIsSystemInitNotBetween(String value1, String value2) {
            addCriterion("is_system_init not between", value1, value2, "isSystemInit");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(String value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(String value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(String value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(String value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(String value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(String value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLike(String value) {
            addCriterion("is_delete like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotLike(String value) {
            addCriterion("is_delete not like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<String> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<String> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(String value1, String value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(String value1, String value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(Integer value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(Integer value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(Integer value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(Integer value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(Integer value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<Integer> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<Integer> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(Integer value1, Integer value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(Integer value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(Integer value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(Integer value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(Integer value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(Integer value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<Integer> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<Integer> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(Integer value1, Integer value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}