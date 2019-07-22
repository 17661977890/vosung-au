package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAuDataRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuDataRuleExample() {
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

        public Criteria andDataRuleCodeIsNull() {
            addCriterion("data_rule_code is null");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeIsNotNull() {
            addCriterion("data_rule_code is not null");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeEqualTo(String value) {
            addCriterion("data_rule_code =", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeNotEqualTo(String value) {
            addCriterion("data_rule_code <>", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeGreaterThan(String value) {
            addCriterion("data_rule_code >", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("data_rule_code >=", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeLessThan(String value) {
            addCriterion("data_rule_code <", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeLessThanOrEqualTo(String value) {
            addCriterion("data_rule_code <=", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeLike(String value) {
            addCriterion("data_rule_code like", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeNotLike(String value) {
            addCriterion("data_rule_code not like", value, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeIn(List<String> values) {
            addCriterion("data_rule_code in", values, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeNotIn(List<String> values) {
            addCriterion("data_rule_code not in", values, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeBetween(String value1, String value2) {
            addCriterion("data_rule_code between", value1, value2, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleCodeNotBetween(String value1, String value2) {
            addCriterion("data_rule_code not between", value1, value2, "dataRuleCode");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameIsNull() {
            addCriterion("data_rule_name is null");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameIsNotNull() {
            addCriterion("data_rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameEqualTo(String value) {
            addCriterion("data_rule_name =", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameNotEqualTo(String value) {
            addCriterion("data_rule_name <>", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameGreaterThan(String value) {
            addCriterion("data_rule_name >", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("data_rule_name >=", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameLessThan(String value) {
            addCriterion("data_rule_name <", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameLessThanOrEqualTo(String value) {
            addCriterion("data_rule_name <=", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameLike(String value) {
            addCriterion("data_rule_name like", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameNotLike(String value) {
            addCriterion("data_rule_name not like", value, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameIn(List<String> values) {
            addCriterion("data_rule_name in", values, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameNotIn(List<String> values) {
            addCriterion("data_rule_name not in", values, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameBetween(String value1, String value2) {
            addCriterion("data_rule_name between", value1, value2, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andDataRuleNameNotBetween(String value1, String value2) {
            addCriterion("data_rule_name not between", value1, value2, "dataRuleName");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdIsNull() {
            addCriterion("business_object_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdIsNotNull() {
            addCriterion("business_object_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdEqualTo(Integer value) {
            addCriterion("business_object_id =", value, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdNotEqualTo(Integer value) {
            addCriterion("business_object_id <>", value, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdGreaterThan(Integer value) {
            addCriterion("business_object_id >", value, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_object_id >=", value, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdLessThan(Integer value) {
            addCriterion("business_object_id <", value, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_object_id <=", value, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdIn(List<Integer> values) {
            addCriterion("business_object_id in", values, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdNotIn(List<Integer> values) {
            addCriterion("business_object_id not in", values, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdBetween(Integer value1, Integer value2) {
            addCriterion("business_object_id between", value1, value2, "businessObjectId");
            return (Criteria) this;
        }

        public Criteria andBusinessObjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_object_id not between", value1, value2, "businessObjectId");
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

        public Criteria andProhibitStateIsNull() {
            addCriterion("prohibit_state is null");
            return (Criteria) this;
        }

        public Criteria andProhibitStateIsNotNull() {
            addCriterion("prohibit_state is not null");
            return (Criteria) this;
        }

        public Criteria andProhibitStateEqualTo(String value) {
            addCriterion("prohibit_state =", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateNotEqualTo(String value) {
            addCriterion("prohibit_state <>", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateGreaterThan(String value) {
            addCriterion("prohibit_state >", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateGreaterThanOrEqualTo(String value) {
            addCriterion("prohibit_state >=", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateLessThan(String value) {
            addCriterion("prohibit_state <", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateLessThanOrEqualTo(String value) {
            addCriterion("prohibit_state <=", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateLike(String value) {
            addCriterion("prohibit_state like", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateNotLike(String value) {
            addCriterion("prohibit_state not like", value, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateIn(List<String> values) {
            addCriterion("prohibit_state in", values, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateNotIn(List<String> values) {
            addCriterion("prohibit_state not in", values, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateBetween(String value1, String value2) {
            addCriterion("prohibit_state between", value1, value2, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitStateNotBetween(String value1, String value2) {
            addCriterion("prohibit_state not between", value1, value2, "prohibitState");
            return (Criteria) this;
        }

        public Criteria andProhibitUserIsNull() {
            addCriterion("prohibit_user is null");
            return (Criteria) this;
        }

        public Criteria andProhibitUserIsNotNull() {
            addCriterion("prohibit_user is not null");
            return (Criteria) this;
        }

        public Criteria andProhibitUserEqualTo(Integer value) {
            addCriterion("prohibit_user =", value, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserNotEqualTo(Integer value) {
            addCriterion("prohibit_user <>", value, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserGreaterThan(Integer value) {
            addCriterion("prohibit_user >", value, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("prohibit_user >=", value, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserLessThan(Integer value) {
            addCriterion("prohibit_user <", value, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserLessThanOrEqualTo(Integer value) {
            addCriterion("prohibit_user <=", value, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserIn(List<Integer> values) {
            addCriterion("prohibit_user in", values, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserNotIn(List<Integer> values) {
            addCriterion("prohibit_user not in", values, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserBetween(Integer value1, Integer value2) {
            addCriterion("prohibit_user between", value1, value2, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitUserNotBetween(Integer value1, Integer value2) {
            addCriterion("prohibit_user not between", value1, value2, "prohibitUser");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeIsNull() {
            addCriterion("prohibit_time is null");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeIsNotNull() {
            addCriterion("prohibit_time is not null");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeEqualTo(Date value) {
            addCriterion("prohibit_time =", value, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeNotEqualTo(Date value) {
            addCriterion("prohibit_time <>", value, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeGreaterThan(Date value) {
            addCriterion("prohibit_time >", value, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("prohibit_time >=", value, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeLessThan(Date value) {
            addCriterion("prohibit_time <", value, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeLessThanOrEqualTo(Date value) {
            addCriterion("prohibit_time <=", value, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeIn(List<Date> values) {
            addCriterion("prohibit_time in", values, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeNotIn(List<Date> values) {
            addCriterion("prohibit_time not in", values, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeBetween(Date value1, Date value2) {
            addCriterion("prohibit_time between", value1, value2, "prohibitTime");
            return (Criteria) this;
        }

        public Criteria andProhibitTimeNotBetween(Date value1, Date value2) {
            addCriterion("prohibit_time not between", value1, value2, "prohibitTime");
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