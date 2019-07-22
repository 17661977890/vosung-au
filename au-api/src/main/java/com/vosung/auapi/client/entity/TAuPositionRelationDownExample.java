package com.vosung.auapi.client.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TAuPositionRelationDownExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TAuPositionRelationDownExample() {
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

        public Criteria andPositionIdIsNull() {
            addCriterion("position_id is null");
            return (Criteria) this;
        }

        public Criteria andPositionIdIsNotNull() {
            addCriterion("position_id is not null");
            return (Criteria) this;
        }

        public Criteria andPositionIdEqualTo(Integer value) {
            addCriterion("position_id =", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotEqualTo(Integer value) {
            addCriterion("position_id <>", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThan(Integer value) {
            addCriterion("position_id >", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("position_id >=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThan(Integer value) {
            addCriterion("position_id <", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThanOrEqualTo(Integer value) {
            addCriterion("position_id <=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdIn(List<Integer> values) {
            addCriterion("position_id in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotIn(List<Integer> values) {
            addCriterion("position_id not in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdBetween(Integer value1, Integer value2) {
            addCriterion("position_id between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("position_id not between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdIsNull() {
            addCriterion("down_position_id is null");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdIsNotNull() {
            addCriterion("down_position_id is not null");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdEqualTo(Integer value) {
            addCriterion("down_position_id =", value, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdNotEqualTo(Integer value) {
            addCriterion("down_position_id <>", value, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdGreaterThan(Integer value) {
            addCriterion("down_position_id >", value, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("down_position_id >=", value, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdLessThan(Integer value) {
            addCriterion("down_position_id <", value, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdLessThanOrEqualTo(Integer value) {
            addCriterion("down_position_id <=", value, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdIn(List<Integer> values) {
            addCriterion("down_position_id in", values, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdNotIn(List<Integer> values) {
            addCriterion("down_position_id not in", values, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdBetween(Integer value1, Integer value2) {
            addCriterion("down_position_id between", value1, value2, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andDownPositionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("down_position_id not between", value1, value2, "downPositionId");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNull() {
            addCriterion("report_type is null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNotNull() {
            addCriterion("report_type is not null");
            return (Criteria) this;
        }

        public Criteria andReportTypeEqualTo(String value) {
            addCriterion("report_type =", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotEqualTo(String value) {
            addCriterion("report_type <>", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThan(String value) {
            addCriterion("report_type >", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThanOrEqualTo(String value) {
            addCriterion("report_type >=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThan(String value) {
            addCriterion("report_type <", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThanOrEqualTo(String value) {
            addCriterion("report_type <=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLike(String value) {
            addCriterion("report_type like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotLike(String value) {
            addCriterion("report_type not like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeIn(List<String> values) {
            addCriterion("report_type in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotIn(List<String> values) {
            addCriterion("report_type not in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeBetween(String value1, String value2) {
            addCriterion("report_type between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotBetween(String value1, String value2) {
            addCriterion("report_type not between", value1, value2, "reportType");
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

        public Criteria andIsEffectIsNull() {
            addCriterion("is_effect is null");
            return (Criteria) this;
        }

        public Criteria andIsEffectIsNotNull() {
            addCriterion("is_effect is not null");
            return (Criteria) this;
        }

        public Criteria andIsEffectEqualTo(String value) {
            addCriterion("is_effect =", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotEqualTo(String value) {
            addCriterion("is_effect <>", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThan(String value) {
            addCriterion("is_effect >", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectGreaterThanOrEqualTo(String value) {
            addCriterion("is_effect >=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThan(String value) {
            addCriterion("is_effect <", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLessThanOrEqualTo(String value) {
            addCriterion("is_effect <=", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectLike(String value) {
            addCriterion("is_effect like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotLike(String value) {
            addCriterion("is_effect not like", value, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectIn(List<String> values) {
            addCriterion("is_effect in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotIn(List<String> values) {
            addCriterion("is_effect not in", values, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectBetween(String value1, String value2) {
            addCriterion("is_effect between", value1, value2, "isEffect");
            return (Criteria) this;
        }

        public Criteria andIsEffectNotBetween(String value1, String value2) {
            addCriterion("is_effect not between", value1, value2, "isEffect");
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