package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAuSubsystemPermissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuSubsystemPermissionExample() {
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

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdIsNull() {
            addCriterion("business_domain_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdIsNotNull() {
            addCriterion("business_domain_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdEqualTo(Integer value) {
            addCriterion("business_domain_id =", value, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdNotEqualTo(Integer value) {
            addCriterion("business_domain_id <>", value, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdGreaterThan(Integer value) {
            addCriterion("business_domain_id >", value, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_domain_id >=", value, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdLessThan(Integer value) {
            addCriterion("business_domain_id <", value, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_domain_id <=", value, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdIn(List<Integer> values) {
            addCriterion("business_domain_id in", values, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdNotIn(List<Integer> values) {
            addCriterion("business_domain_id not in", values, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdBetween(Integer value1, Integer value2) {
            addCriterion("business_domain_id between", value1, value2, "businessDomainId");
            return (Criteria) this;
        }

        public Criteria andBusinessDomainIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_domain_id not between", value1, value2, "businessDomainId");
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

        public Criteria andPermissionGroupCodeIsNull() {
            addCriterion("permission_group_code is null");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeIsNotNull() {
            addCriterion("permission_group_code is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeEqualTo(String value) {
            addCriterion("permission_group_code =", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeNotEqualTo(String value) {
            addCriterion("permission_group_code <>", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeGreaterThan(String value) {
            addCriterion("permission_group_code >", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("permission_group_code >=", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeLessThan(String value) {
            addCriterion("permission_group_code <", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("permission_group_code <=", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeLike(String value) {
            addCriterion("permission_group_code like", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeNotLike(String value) {
            addCriterion("permission_group_code not like", value, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeIn(List<String> values) {
            addCriterion("permission_group_code in", values, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeNotIn(List<String> values) {
            addCriterion("permission_group_code not in", values, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeBetween(String value1, String value2) {
            addCriterion("permission_group_code between", value1, value2, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionGroupCodeNotBetween(String value1, String value2) {
            addCriterion("permission_group_code not between", value1, value2, "permissionGroupCode");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusIsNull() {
            addCriterion("permission_status is null");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusIsNotNull() {
            addCriterion("permission_status is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusEqualTo(String value) {
            addCriterion("permission_status =", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusNotEqualTo(String value) {
            addCriterion("permission_status <>", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusGreaterThan(String value) {
            addCriterion("permission_status >", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusGreaterThanOrEqualTo(String value) {
            addCriterion("permission_status >=", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusLessThan(String value) {
            addCriterion("permission_status <", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusLessThanOrEqualTo(String value) {
            addCriterion("permission_status <=", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusLike(String value) {
            addCriterion("permission_status like", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusNotLike(String value) {
            addCriterion("permission_status not like", value, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusIn(List<String> values) {
            addCriterion("permission_status in", values, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusNotIn(List<String> values) {
            addCriterion("permission_status not in", values, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusBetween(String value1, String value2) {
            addCriterion("permission_status between", value1, value2, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andPermissionStatusNotBetween(String value1, String value2) {
            addCriterion("permission_status not between", value1, value2, "permissionStatus");
            return (Criteria) this;
        }

        public Criteria andDataStateIsNull() {
            addCriterion("data_state is null");
            return (Criteria) this;
        }

        public Criteria andDataStateIsNotNull() {
            addCriterion("data_state is not null");
            return (Criteria) this;
        }

        public Criteria andDataStateEqualTo(String value) {
            addCriterion("data_state =", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateNotEqualTo(String value) {
            addCriterion("data_state <>", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateGreaterThan(String value) {
            addCriterion("data_state >", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateGreaterThanOrEqualTo(String value) {
            addCriterion("data_state >=", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateLessThan(String value) {
            addCriterion("data_state <", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateLessThanOrEqualTo(String value) {
            addCriterion("data_state <=", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateLike(String value) {
            addCriterion("data_state like", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateNotLike(String value) {
            addCriterion("data_state not like", value, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateIn(List<String> values) {
            addCriterion("data_state in", values, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateNotIn(List<String> values) {
            addCriterion("data_state not in", values, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateBetween(String value1, String value2) {
            addCriterion("data_state between", value1, value2, "dataState");
            return (Criteria) this;
        }

        public Criteria andDataStateNotBetween(String value1, String value2) {
            addCriterion("data_state not between", value1, value2, "dataState");
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