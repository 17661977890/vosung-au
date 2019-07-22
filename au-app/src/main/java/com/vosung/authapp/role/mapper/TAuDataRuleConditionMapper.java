package com.vosung.authapp.role.mapper;

import com.vosung.auapi.client.entity.TAuDataRuleCondition;
import com.vosung.auapi.client.entity.TAuDataRuleConditionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuDataRuleConditionMapper {
    int countByExample(TAuDataRuleConditionExample example);

    int deleteByExample(TAuDataRuleConditionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuDataRuleCondition record);

    int insertSelective(TAuDataRuleCondition record);

    List<TAuDataRuleCondition> selectByExample(TAuDataRuleConditionExample example);

    TAuDataRuleCondition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuDataRuleCondition record, @Param("example") TAuDataRuleConditionExample example);

    int updateByExample(@Param("record") TAuDataRuleCondition record, @Param("example") TAuDataRuleConditionExample example);

    int updateByPrimaryKeySelective(TAuDataRuleCondition record);

    int updateByPrimaryKey(TAuDataRuleCondition record);
}