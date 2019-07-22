package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAuUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuUserExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdIsNull() {
            addCriterion("create_org_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdIsNotNull() {
            addCriterion("create_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdEqualTo(Integer value) {
            addCriterion("create_org_id =", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdNotEqualTo(Integer value) {
            addCriterion("create_org_id <>", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdGreaterThan(Integer value) {
            addCriterion("create_org_id >", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_org_id >=", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdLessThan(Integer value) {
            addCriterion("create_org_id <", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_org_id <=", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdIn(List<Integer> values) {
            addCriterion("create_org_id in", values, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdNotIn(List<Integer> values) {
            addCriterion("create_org_id not in", values, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("create_org_id between", value1, value2, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_org_id not between", value1, value2, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdIsNull() {
            addCriterion("permit_org_id is null");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdIsNotNull() {
            addCriterion("permit_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdEqualTo(Integer value) {
            addCriterion("permit_org_id =", value, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdNotEqualTo(Integer value) {
            addCriterion("permit_org_id <>", value, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdGreaterThan(Integer value) {
            addCriterion("permit_org_id >", value, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("permit_org_id >=", value, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdLessThan(Integer value) {
            addCriterion("permit_org_id <", value, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("permit_org_id <=", value, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdIn(List<Integer> values) {
            addCriterion("permit_org_id in", values, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdNotIn(List<Integer> values) {
            addCriterion("permit_org_id not in", values, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("permit_org_id between", value1, value2, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andPermitOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("permit_org_id not between", value1, value2, "permitOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdIsNull() {
            addCriterion("belong_dept_id is null");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdIsNotNull() {
            addCriterion("belong_dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdEqualTo(Integer value) {
            addCriterion("belong_dept_id =", value, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdNotEqualTo(Integer value) {
            addCriterion("belong_dept_id <>", value, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdGreaterThan(Integer value) {
            addCriterion("belong_dept_id >", value, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("belong_dept_id >=", value, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdLessThan(Integer value) {
            addCriterion("belong_dept_id <", value, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdLessThanOrEqualTo(Integer value) {
            addCriterion("belong_dept_id <=", value, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdIn(List<Integer> values) {
            addCriterion("belong_dept_id in", values, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdNotIn(List<Integer> values) {
            addCriterion("belong_dept_id not in", values, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdBetween(Integer value1, Integer value2) {
            addCriterion("belong_dept_id between", value1, value2, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongDeptIdNotBetween(Integer value1, Integer value2) {
            addCriterion("belong_dept_id not between", value1, value2, "belongDeptId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdIsNull() {
            addCriterion("belong_station_id is null");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdIsNotNull() {
            addCriterion("belong_station_id is not null");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdEqualTo(Integer value) {
            addCriterion("belong_station_id =", value, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdNotEqualTo(Integer value) {
            addCriterion("belong_station_id <>", value, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdGreaterThan(Integer value) {
            addCriterion("belong_station_id >", value, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("belong_station_id >=", value, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdLessThan(Integer value) {
            addCriterion("belong_station_id <", value, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdLessThanOrEqualTo(Integer value) {
            addCriterion("belong_station_id <=", value, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdIn(List<Integer> values) {
            addCriterion("belong_station_id in", values, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdNotIn(List<Integer> values) {
            addCriterion("belong_station_id not in", values, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdBetween(Integer value1, Integer value2) {
            addCriterion("belong_station_id between", value1, value2, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andBelongStationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("belong_station_id not between", value1, value2, "belongStationId");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andContactStaffIsNull() {
            addCriterion("contact_staff is null");
            return (Criteria) this;
        }

        public Criteria andContactStaffIsNotNull() {
            addCriterion("contact_staff is not null");
            return (Criteria) this;
        }

        public Criteria andContactStaffEqualTo(Integer value) {
            addCriterion("contact_staff =", value, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffNotEqualTo(Integer value) {
            addCriterion("contact_staff <>", value, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffGreaterThan(Integer value) {
            addCriterion("contact_staff >", value, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_staff >=", value, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffLessThan(Integer value) {
            addCriterion("contact_staff <", value, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffLessThanOrEqualTo(Integer value) {
            addCriterion("contact_staff <=", value, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffIn(List<Integer> values) {
            addCriterion("contact_staff in", values, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffNotIn(List<Integer> values) {
            addCriterion("contact_staff not in", values, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffBetween(Integer value1, Integer value2) {
            addCriterion("contact_staff between", value1, value2, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_staff not between", value1, value2, "contactStaff");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeIsNull() {
            addCriterion("contact_staff_type is null");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeIsNotNull() {
            addCriterion("contact_staff_type is not null");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeEqualTo(String value) {
            addCriterion("contact_staff_type =", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeNotEqualTo(String value) {
            addCriterion("contact_staff_type <>", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeGreaterThan(String value) {
            addCriterion("contact_staff_type >", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeGreaterThanOrEqualTo(String value) {
            addCriterion("contact_staff_type >=", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeLessThan(String value) {
            addCriterion("contact_staff_type <", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeLessThanOrEqualTo(String value) {
            addCriterion("contact_staff_type <=", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeLike(String value) {
            addCriterion("contact_staff_type like", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeNotLike(String value) {
            addCriterion("contact_staff_type not like", value, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeIn(List<String> values) {
            addCriterion("contact_staff_type in", values, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeNotIn(List<String> values) {
            addCriterion("contact_staff_type not in", values, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeBetween(String value1, String value2) {
            addCriterion("contact_staff_type between", value1, value2, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andContactStaffTypeNotBetween(String value1, String value2) {
            addCriterion("contact_staff_type not between", value1, value2, "contactStaffType");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("summary is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("summary is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("summary =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("summary <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("summary >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("summary >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("summary <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("summary <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("summary like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("summary not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("summary in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("summary not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("summary between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("summary not between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andLiftBanByIsNull() {
            addCriterion("lift_ban_by is null");
            return (Criteria) this;
        }

        public Criteria andLiftBanByIsNotNull() {
            addCriterion("lift_ban_by is not null");
            return (Criteria) this;
        }

        public Criteria andLiftBanByEqualTo(Integer value) {
            addCriterion("lift_ban_by =", value, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByNotEqualTo(Integer value) {
            addCriterion("lift_ban_by <>", value, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByGreaterThan(Integer value) {
            addCriterion("lift_ban_by >", value, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByGreaterThanOrEqualTo(Integer value) {
            addCriterion("lift_ban_by >=", value, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByLessThan(Integer value) {
            addCriterion("lift_ban_by <", value, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByLessThanOrEqualTo(Integer value) {
            addCriterion("lift_ban_by <=", value, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByIn(List<Integer> values) {
            addCriterion("lift_ban_by in", values, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByNotIn(List<Integer> values) {
            addCriterion("lift_ban_by not in", values, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByBetween(Integer value1, Integer value2) {
            addCriterion("lift_ban_by between", value1, value2, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanByNotBetween(Integer value1, Integer value2) {
            addCriterion("lift_ban_by not between", value1, value2, "liftBanBy");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeIsNull() {
            addCriterion("lift_ban_time is null");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeIsNotNull() {
            addCriterion("lift_ban_time is not null");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeEqualTo(Date value) {
            addCriterion("lift_ban_time =", value, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeNotEqualTo(Date value) {
            addCriterion("lift_ban_time <>", value, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeGreaterThan(Date value) {
            addCriterion("lift_ban_time >", value, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lift_ban_time >=", value, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeLessThan(Date value) {
            addCriterion("lift_ban_time <", value, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeLessThanOrEqualTo(Date value) {
            addCriterion("lift_ban_time <=", value, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeIn(List<Date> values) {
            addCriterion("lift_ban_time in", values, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeNotIn(List<Date> values) {
            addCriterion("lift_ban_time not in", values, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeBetween(Date value1, Date value2) {
            addCriterion("lift_ban_time between", value1, value2, "liftBanTime");
            return (Criteria) this;
        }

        public Criteria andLiftBanTimeNotBetween(Date value1, Date value2) {
            addCriterion("lift_ban_time not between", value1, value2, "liftBanTime");
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

        public Criteria andReviewUserIsNull() {
            addCriterion("review_user is null");
            return (Criteria) this;
        }

        public Criteria andReviewUserIsNotNull() {
            addCriterion("review_user is not null");
            return (Criteria) this;
        }

        public Criteria andReviewUserEqualTo(Integer value) {
            addCriterion("review_user =", value, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserNotEqualTo(Integer value) {
            addCriterion("review_user <>", value, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserGreaterThan(Integer value) {
            addCriterion("review_user >", value, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("review_user >=", value, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserLessThan(Integer value) {
            addCriterion("review_user <", value, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserLessThanOrEqualTo(Integer value) {
            addCriterion("review_user <=", value, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserIn(List<Integer> values) {
            addCriterion("review_user in", values, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserNotIn(List<Integer> values) {
            addCriterion("review_user not in", values, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserBetween(Integer value1, Integer value2) {
            addCriterion("review_user between", value1, value2, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewUserNotBetween(Integer value1, Integer value2) {
            addCriterion("review_user not between", value1, value2, "reviewUser");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIsNull() {
            addCriterion("review_time is null");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIsNotNull() {
            addCriterion("review_time is not null");
            return (Criteria) this;
        }

        public Criteria andReviewTimeEqualTo(Date value) {
            addCriterion("review_time =", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotEqualTo(Date value) {
            addCriterion("review_time <>", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeGreaterThan(Date value) {
            addCriterion("review_time >", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("review_time >=", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeLessThan(Date value) {
            addCriterion("review_time <", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeLessThanOrEqualTo(Date value) {
            addCriterion("review_time <=", value, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeIn(List<Date> values) {
            addCriterion("review_time in", values, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotIn(List<Date> values) {
            addCriterion("review_time not in", values, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeBetween(Date value1, Date value2) {
            addCriterion("review_time between", value1, value2, "reviewTime");
            return (Criteria) this;
        }

        public Criteria andReviewTimeNotBetween(Date value1, Date value2) {
            addCriterion("review_time not between", value1, value2, "reviewTime");
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