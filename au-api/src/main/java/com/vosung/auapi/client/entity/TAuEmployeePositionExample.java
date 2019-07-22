package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TAuEmployeePositionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuEmployeePositionExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(Integer value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(Integer value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(Integer value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(Integer value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(Integer value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<Integer> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<Integer> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(Integer value1, Integer value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIsNull() {
            addCriterion("work_org_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIsNotNull() {
            addCriterion("work_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdEqualTo(Integer value) {
            addCriterion("work_org_id =", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotEqualTo(Integer value) {
            addCriterion("work_org_id <>", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdGreaterThan(Integer value) {
            addCriterion("work_org_id >", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_org_id >=", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLessThan(Integer value) {
            addCriterion("work_org_id <", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("work_org_id <=", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIn(List<Integer> values) {
            addCriterion("work_org_id in", values, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotIn(List<Integer> values) {
            addCriterion("work_org_id not in", values, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("work_org_id between", value1, value2, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("work_org_id not between", value1, value2, "workOrgId");
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

        public Criteria andDirectorPositionIdIsNull() {
            addCriterion("director_position_id is null");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdIsNotNull() {
            addCriterion("director_position_id is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdEqualTo(Integer value) {
            addCriterion("director_position_id =", value, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdNotEqualTo(Integer value) {
            addCriterion("director_position_id <>", value, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdGreaterThan(Integer value) {
            addCriterion("director_position_id >", value, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("director_position_id >=", value, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdLessThan(Integer value) {
            addCriterion("director_position_id <", value, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdLessThanOrEqualTo(Integer value) {
            addCriterion("director_position_id <=", value, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdIn(List<Integer> values) {
            addCriterion("director_position_id in", values, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdNotIn(List<Integer> values) {
            addCriterion("director_position_id not in", values, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdBetween(Integer value1, Integer value2) {
            addCriterion("director_position_id between", value1, value2, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("director_position_id not between", value1, value2, "directorPositionId");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateIsNull() {
            addCriterion("director_position_start_date is null");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateIsNotNull() {
            addCriterion("director_position_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("director_position_start_date =", value, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("director_position_start_date <>", value, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("director_position_start_date >", value, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("director_position_start_date >=", value, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateLessThan(Date value) {
            addCriterionForJDBCDate("director_position_start_date <", value, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("director_position_start_date <=", value, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("director_position_start_date in", values, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("director_position_start_date not in", values, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("director_position_start_date between", value1, value2, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andDirectorPositionStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("director_position_start_date not between", value1, value2, "directorPositionStartDate");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionIsNull() {
            addCriterion("is_main_position is null");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionIsNotNull() {
            addCriterion("is_main_position is not null");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionEqualTo(String value) {
            addCriterion("is_main_position =", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionNotEqualTo(String value) {
            addCriterion("is_main_position <>", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionGreaterThan(String value) {
            addCriterion("is_main_position >", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionGreaterThanOrEqualTo(String value) {
            addCriterion("is_main_position >=", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionLessThan(String value) {
            addCriterion("is_main_position <", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionLessThanOrEqualTo(String value) {
            addCriterion("is_main_position <=", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionLike(String value) {
            addCriterion("is_main_position like", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionNotLike(String value) {
            addCriterion("is_main_position not like", value, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionIn(List<String> values) {
            addCriterion("is_main_position in", values, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionNotIn(List<String> values) {
            addCriterion("is_main_position not in", values, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionBetween(String value1, String value2) {
            addCriterion("is_main_position between", value1, value2, "isMainPosition");
            return (Criteria) this;
        }

        public Criteria andIsMainPositionNotBetween(String value1, String value2) {
            addCriterion("is_main_position not between", value1, value2, "isMainPosition");
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