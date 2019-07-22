package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAuDataRuleConditionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuDataRuleConditionExample() {
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

        public Criteria andDataRuleIdIsNull() {
            addCriterion("data_rule_id is null");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdIsNotNull() {
            addCriterion("data_rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdEqualTo(Integer value) {
            addCriterion("data_rule_id =", value, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdNotEqualTo(Integer value) {
            addCriterion("data_rule_id <>", value, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdGreaterThan(Integer value) {
            addCriterion("data_rule_id >", value, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_rule_id >=", value, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdLessThan(Integer value) {
            addCriterion("data_rule_id <", value, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("data_rule_id <=", value, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdIn(List<Integer> values) {
            addCriterion("data_rule_id in", values, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdNotIn(List<Integer> values) {
            addCriterion("data_rule_id not in", values, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("data_rule_id between", value1, value2, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andDataRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("data_rule_id not between", value1, value2, "dataRuleId");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andContactTableIdIsNull() {
            addCriterion("contact_table_id is null");
            return (Criteria) this;
        }

        public Criteria andContactTableIdIsNotNull() {
            addCriterion("contact_table_id is not null");
            return (Criteria) this;
        }

        public Criteria andContactTableIdEqualTo(Integer value) {
            addCriterion("contact_table_id =", value, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdNotEqualTo(Integer value) {
            addCriterion("contact_table_id <>", value, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdGreaterThan(Integer value) {
            addCriterion("contact_table_id >", value, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_table_id >=", value, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdLessThan(Integer value) {
            addCriterion("contact_table_id <", value, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("contact_table_id <=", value, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdIn(List<Integer> values) {
            addCriterion("contact_table_id in", values, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdNotIn(List<Integer> values) {
            addCriterion("contact_table_id not in", values, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdBetween(Integer value1, Integer value2) {
            addCriterion("contact_table_id between", value1, value2, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_table_id not between", value1, value2, "contactTableId");
            return (Criteria) this;
        }

        public Criteria andContactColumnIsNull() {
            addCriterion("contact_column is null");
            return (Criteria) this;
        }

        public Criteria andContactColumnIsNotNull() {
            addCriterion("contact_column is not null");
            return (Criteria) this;
        }

        public Criteria andContactColumnEqualTo(String value) {
            addCriterion("contact_column =", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnNotEqualTo(String value) {
            addCriterion("contact_column <>", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnGreaterThan(String value) {
            addCriterion("contact_column >", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnGreaterThanOrEqualTo(String value) {
            addCriterion("contact_column >=", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnLessThan(String value) {
            addCriterion("contact_column <", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnLessThanOrEqualTo(String value) {
            addCriterion("contact_column <=", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnLike(String value) {
            addCriterion("contact_column like", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnNotLike(String value) {
            addCriterion("contact_column not like", value, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnIn(List<String> values) {
            addCriterion("contact_column in", values, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnNotIn(List<String> values) {
            addCriterion("contact_column not in", values, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnBetween(String value1, String value2) {
            addCriterion("contact_column between", value1, value2, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andContactColumnNotBetween(String value1, String value2) {
            addCriterion("contact_column not between", value1, value2, "contactColumn");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsIsNull() {
            addCriterion("left_brackets is null");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsIsNotNull() {
            addCriterion("left_brackets is not null");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsEqualTo(String value) {
            addCriterion("left_brackets =", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsNotEqualTo(String value) {
            addCriterion("left_brackets <>", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsGreaterThan(String value) {
            addCriterion("left_brackets >", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsGreaterThanOrEqualTo(String value) {
            addCriterion("left_brackets >=", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsLessThan(String value) {
            addCriterion("left_brackets <", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsLessThanOrEqualTo(String value) {
            addCriterion("left_brackets <=", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsLike(String value) {
            addCriterion("left_brackets like", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsNotLike(String value) {
            addCriterion("left_brackets not like", value, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsIn(List<String> values) {
            addCriterion("left_brackets in", values, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsNotIn(List<String> values) {
            addCriterion("left_brackets not in", values, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsBetween(String value1, String value2) {
            addCriterion("left_brackets between", value1, value2, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andLeftBracketsNotBetween(String value1, String value2) {
            addCriterion("left_brackets not between", value1, value2, "leftBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsIsNull() {
            addCriterion("right_brackets is null");
            return (Criteria) this;
        }

        public Criteria andRightBracketsIsNotNull() {
            addCriterion("right_brackets is not null");
            return (Criteria) this;
        }

        public Criteria andRightBracketsEqualTo(String value) {
            addCriterion("right_brackets =", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsNotEqualTo(String value) {
            addCriterion("right_brackets <>", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsGreaterThan(String value) {
            addCriterion("right_brackets >", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsGreaterThanOrEqualTo(String value) {
            addCriterion("right_brackets >=", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsLessThan(String value) {
            addCriterion("right_brackets <", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsLessThanOrEqualTo(String value) {
            addCriterion("right_brackets <=", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsLike(String value) {
            addCriterion("right_brackets like", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsNotLike(String value) {
            addCriterion("right_brackets not like", value, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsIn(List<String> values) {
            addCriterion("right_brackets in", values, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsNotIn(List<String> values) {
            addCriterion("right_brackets not in", values, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsBetween(String value1, String value2) {
            addCriterion("right_brackets between", value1, value2, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andRightBracketsNotBetween(String value1, String value2) {
            addCriterion("right_brackets not between", value1, value2, "rightBrackets");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNull() {
            addCriterion("column_name is null");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNotNull() {
            addCriterion("column_name is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNameEqualTo(String value) {
            addCriterion("column_name =", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotEqualTo(String value) {
            addCriterion("column_name <>", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThan(String value) {
            addCriterion("column_name >", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("column_name >=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThan(String value) {
            addCriterion("column_name <", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThanOrEqualTo(String value) {
            addCriterion("column_name <=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLike(String value) {
            addCriterion("column_name like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotLike(String value) {
            addCriterion("column_name not like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIn(List<String> values) {
            addCriterion("column_name in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotIn(List<String> values) {
            addCriterion("column_name not in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameBetween(String value1, String value2) {
            addCriterion("column_name between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotBetween(String value1, String value2) {
            addCriterion("column_name not between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andCompareIsNull() {
            addCriterion("compare is null");
            return (Criteria) this;
        }

        public Criteria andCompareIsNotNull() {
            addCriterion("compare is not null");
            return (Criteria) this;
        }

        public Criteria andCompareEqualTo(String value) {
            addCriterion("compare =", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareNotEqualTo(String value) {
            addCriterion("compare <>", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareGreaterThan(String value) {
            addCriterion("compare >", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareGreaterThanOrEqualTo(String value) {
            addCriterion("compare >=", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareLessThan(String value) {
            addCriterion("compare <", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareLessThanOrEqualTo(String value) {
            addCriterion("compare <=", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareLike(String value) {
            addCriterion("compare like", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareNotLike(String value) {
            addCriterion("compare not like", value, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareIn(List<String> values) {
            addCriterion("compare in", values, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareNotIn(List<String> values) {
            addCriterion("compare not in", values, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareBetween(String value1, String value2) {
            addCriterion("compare between", value1, value2, "compare");
            return (Criteria) this;
        }

        public Criteria andCompareNotBetween(String value1, String value2) {
            addCriterion("compare not between", value1, value2, "compare");
            return (Criteria) this;
        }

        public Criteria andConditionValueIsNull() {
            addCriterion("condition_value is null");
            return (Criteria) this;
        }

        public Criteria andConditionValueIsNotNull() {
            addCriterion("condition_value is not null");
            return (Criteria) this;
        }

        public Criteria andConditionValueEqualTo(String value) {
            addCriterion("condition_value =", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotEqualTo(String value) {
            addCriterion("condition_value <>", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueGreaterThan(String value) {
            addCriterion("condition_value >", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueGreaterThanOrEqualTo(String value) {
            addCriterion("condition_value >=", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueLessThan(String value) {
            addCriterion("condition_value <", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueLessThanOrEqualTo(String value) {
            addCriterion("condition_value <=", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueLike(String value) {
            addCriterion("condition_value like", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotLike(String value) {
            addCriterion("condition_value not like", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueIn(List<String> values) {
            addCriterion("condition_value in", values, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotIn(List<String> values) {
            addCriterion("condition_value not in", values, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueBetween(String value1, String value2) {
            addCriterion("condition_value between", value1, value2, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotBetween(String value1, String value2) {
            addCriterion("condition_value not between", value1, value2, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andLogicIsNull() {
            addCriterion("logic is null");
            return (Criteria) this;
        }

        public Criteria andLogicIsNotNull() {
            addCriterion("logic is not null");
            return (Criteria) this;
        }

        public Criteria andLogicEqualTo(String value) {
            addCriterion("logic =", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicNotEqualTo(String value) {
            addCriterion("logic <>", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicGreaterThan(String value) {
            addCriterion("logic >", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicGreaterThanOrEqualTo(String value) {
            addCriterion("logic >=", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicLessThan(String value) {
            addCriterion("logic <", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicLessThanOrEqualTo(String value) {
            addCriterion("logic <=", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicLike(String value) {
            addCriterion("logic like", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicNotLike(String value) {
            addCriterion("logic not like", value, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicIn(List<String> values) {
            addCriterion("logic in", values, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicNotIn(List<String> values) {
            addCriterion("logic not in", values, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicBetween(String value1, String value2) {
            addCriterion("logic between", value1, value2, "logic");
            return (Criteria) this;
        }

        public Criteria andLogicNotBetween(String value1, String value2) {
            addCriterion("logic not between", value1, value2, "logic");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(String value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(String value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(String value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(String value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(String value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(String value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLike(String value) {
            addCriterion("sort like", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotLike(String value) {
            addCriterion("sort not like", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<String> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<String> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(String value1, String value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(String value1, String value2) {
            addCriterion("sort not between", value1, value2, "sort");
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

        public Criteria andColumnCodeIsNull() {
            addCriterion("column_code is null");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIsNotNull() {
            addCriterion("column_code is not null");
            return (Criteria) this;
        }

        public Criteria andColumnCodeEqualTo(String value) {
            addCriterion("column_code =", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotEqualTo(String value) {
            addCriterion("column_code <>", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeGreaterThan(String value) {
            addCriterion("column_code >", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("column_code >=", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLessThan(String value) {
            addCriterion("column_code <", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLessThanOrEqualTo(String value) {
            addCriterion("column_code <=", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeLike(String value) {
            addCriterion("column_code like", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotLike(String value) {
            addCriterion("column_code not like", value, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeIn(List<String> values) {
            addCriterion("column_code in", values, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotIn(List<String> values) {
            addCriterion("column_code not in", values, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeBetween(String value1, String value2) {
            addCriterion("column_code between", value1, value2, "columnCode");
            return (Criteria) this;
        }

        public Criteria andColumnCodeNotBetween(String value1, String value2) {
            addCriterion("column_code not between", value1, value2, "columnCode");
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