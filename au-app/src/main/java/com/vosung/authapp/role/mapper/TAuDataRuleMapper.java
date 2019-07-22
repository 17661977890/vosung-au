package com.vosung.authapp.role.mapper;

import com.vosung.auapi.client.entity.TAuDataRule;
import com.vosung.auapi.client.entity.TAuDataRuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuDataRuleMapper {
    int countByExample(TAuDataRuleExample example);

    int deleteByExample(TAuDataRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuDataRule record);

    int insertSelective(TAuDataRule record);

    List<TAuDataRule> selectByExample(TAuDataRuleExample example);

    TAuDataRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuDataRule record, @Param("example") TAuDataRuleExample example);

    int updateByExample(@Param("record") TAuDataRule record, @Param("example") TAuDataRuleExample example);

    int updateByPrimaryKeySelective(TAuDataRule record);

    int updateByPrimaryKey(TAuDataRule record);
}