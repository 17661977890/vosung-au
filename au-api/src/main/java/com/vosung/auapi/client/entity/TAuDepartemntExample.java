package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TAuDepartemntExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuDepartemntExample() {
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

        public Criteria andUseOrgIdIsNull() {
            addCriterion("use_org_id is null");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdIsNotNull() {
            addCriterion("use_org_id is not null");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdEqualTo(Integer value) {
            addCriterion("use_org_id =", value, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdNotEqualTo(Integer value) {
            addCriterion("use_org_id <>", value, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdGreaterThan(Integer value) {
            addCriterion("use_org_id >", value, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_org_id >=", value, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdLessThan(Integer value) {
            addCriterion("use_org_id <", value, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("use_org_id <=", value, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdIn(List<Integer> values) {
            addCriterion("use_org_id in", values, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdNotIn(List<Integer> values) {
            addCriterion("use_org_id not in", values, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("use_org_id between", value1, value2, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andUseOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("use_org_id not between", value1, value2, "useOrgId");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNull() {
            addCriterion("dept_code is null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNotNull() {
            addCriterion("dept_code is not null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeEqualTo(String value) {
            addCriterion("dept_code =", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotEqualTo(String value) {
            addCriterion("dept_code <>", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThan(String value) {
            addCriterion("dept_code >", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dept_code >=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThan(String value) {
            addCriterion("dept_code <", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThanOrEqualTo(String value) {
            addCriterion("dept_code <=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLike(String value) {
            addCriterion("dept_code like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotLike(String value) {
            addCriterion("dept_code not like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIn(List<String> values) {
            addCriterion("dept_code in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotIn(List<String> values) {
            addCriterion("dept_code not in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeBetween(String value1, String value2) {
            addCriterion("dept_code between", value1, value2, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotBetween(String value1, String value2) {
            addCriterion("dept_code not between", value1, value2, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIsNull() {
            addCriterion("mnemonic_code is null");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIsNotNull() {
            addCriterion("mnemonic_code is not null");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeEqualTo(String value) {
            addCriterion("mnemonic_code =", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotEqualTo(String value) {
            addCriterion("mnemonic_code <>", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeGreaterThan(String value) {
            addCriterion("mnemonic_code >", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeGreaterThanOrEqualTo(String value) {
            addCriterion("mnemonic_code >=", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLessThan(String value) {
            addCriterion("mnemonic_code <", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLessThanOrEqualTo(String value) {
            addCriterion("mnemonic_code <=", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeLike(String value) {
            addCriterion("mnemonic_code like", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotLike(String value) {
            addCriterion("mnemonic_code not like", value, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeIn(List<String> values) {
            addCriterion("mnemonic_code in", values, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotIn(List<String> values) {
            addCriterion("mnemonic_code not in", values, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeBetween(String value1, String value2) {
            addCriterion("mnemonic_code between", value1, value2, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andMnemonicCodeNotBetween(String value1, String value2) {
            addCriterion("mnemonic_code not between", value1, value2, "mnemonicCode");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameIsNull() {
            addCriterion("dept_full_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameIsNotNull() {
            addCriterion("dept_full_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameEqualTo(String value) {
            addCriterion("dept_full_name =", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameNotEqualTo(String value) {
            addCriterion("dept_full_name <>", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameGreaterThan(String value) {
            addCriterion("dept_full_name >", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_full_name >=", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameLessThan(String value) {
            addCriterion("dept_full_name <", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameLessThanOrEqualTo(String value) {
            addCriterion("dept_full_name <=", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameLike(String value) {
            addCriterion("dept_full_name like", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameNotLike(String value) {
            addCriterion("dept_full_name not like", value, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameIn(List<String> values) {
            addCriterion("dept_full_name in", values, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameNotIn(List<String> values) {
            addCriterion("dept_full_name not in", values, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameBetween(String value1, String value2) {
            addCriterion("dept_full_name between", value1, value2, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptFullNameNotBetween(String value1, String value2) {
            addCriterion("dept_full_name not between", value1, value2, "deptFullName");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdIsNull() {
            addCriterion("dept_group_id is null");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdIsNotNull() {
            addCriterion("dept_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdEqualTo(Integer value) {
            addCriterion("dept_group_id =", value, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdNotEqualTo(Integer value) {
            addCriterion("dept_group_id <>", value, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdGreaterThan(Integer value) {
            addCriterion("dept_group_id >", value, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dept_group_id >=", value, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdLessThan(Integer value) {
            addCriterion("dept_group_id <", value, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("dept_group_id <=", value, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdIn(List<Integer> values) {
            addCriterion("dept_group_id in", values, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdNotIn(List<Integer> values) {
            addCriterion("dept_group_id not in", values, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("dept_group_id between", value1, value2, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andDeptGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dept_group_id not between", value1, value2, "deptGroupId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdIsNull() {
            addCriterion("parent_dept_id is null");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdIsNotNull() {
            addCriterion("parent_dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdEqualTo(Integer value) {
            addCriterion("parent_dept_id =", value, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdNotEqualTo(Integer value) {
            addCriterion("parent_dept_id <>", value, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdGreaterThan(Integer value) {
            addCriterion("parent_dept_id >", value, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_dept_id >=", value, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdLessThan(Integer value) {
            addCriterion("parent_dept_id <", value, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_dept_id <=", value, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdIn(List<Integer> values) {
            addCriterion("parent_dept_id in", values, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdNotIn(List<Integer> values) {
            addCriterion("parent_dept_id not in", values, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_dept_id between", value1, value2, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andParentDeptIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_dept_id not between", value1, value2, "parentDeptId");
            return (Criteria) this;
        }

        public Criteria andEffectDateIsNull() {
            addCriterion("effect_date is null");
            return (Criteria) this;
        }

        public Criteria andEffectDateIsNotNull() {
            addCriterion("effect_date is not null");
            return (Criteria) this;
        }

        public Criteria andEffectDateEqualTo(Date value) {
            addCriterionForJDBCDate("effect_date =", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("effect_date <>", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThan(Date value) {
            addCriterionForJDBCDate("effect_date >", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("effect_date >=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThan(Date value) {
            addCriterionForJDBCDate("effect_date <", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("effect_date <=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateIn(List<Date> values) {
            addCriterionForJDBCDate("effect_date in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("effect_date not in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("effect_date between", value1, value2, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("effect_date not between", value1, value2, "effectDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateIsNull() {
            addCriterion("abate_date is null");
            return (Criteria) this;
        }

        public Criteria andAbateDateIsNotNull() {
            addCriterion("abate_date is not null");
            return (Criteria) this;
        }

        public Criteria andAbateDateEqualTo(Date value) {
            addCriterionForJDBCDate("abate_date =", value, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("abate_date <>", value, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("abate_date >", value, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("abate_date >=", value, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateLessThan(Date value) {
            addCriterionForJDBCDate("abate_date <", value, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("abate_date <=", value, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateIn(List<Date> values) {
            addCriterionForJDBCDate("abate_date in", values, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("abate_date not in", values, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("abate_date between", value1, value2, "abateDate");
            return (Criteria) this;
        }

        public Criteria andAbateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("abate_date not between", value1, value2, "abateDate");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyIsNull() {
            addCriterion("dept_property is null");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyIsNotNull() {
            addCriterion("dept_property is not null");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyEqualTo(String value) {
            addCriterion("dept_property =", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotEqualTo(String value) {
            addCriterion("dept_property <>", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyGreaterThan(String value) {
            addCriterion("dept_property >", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("dept_property >=", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyLessThan(String value) {
            addCriterion("dept_property <", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyLessThanOrEqualTo(String value) {
            addCriterion("dept_property <=", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyLike(String value) {
            addCriterion("dept_property like", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotLike(String value) {
            addCriterion("dept_property not like", value, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyIn(List<String> values) {
            addCriterion("dept_property in", values, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotIn(List<String> values) {
            addCriterion("dept_property not in", values, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyBetween(String value1, String value2) {
            addCriterion("dept_property between", value1, value2, "deptProperty");
            return (Criteria) this;
        }

        public Criteria andDeptPropertyNotBetween(String value1, String value2) {
            addCriterion("dept_property not between", value1, value2, "deptProperty");
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