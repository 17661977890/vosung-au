package com.vosung.authapp.employee.mapper;

import com.vosung.auapi.client.entity.TAuPapers;
import com.vosung.auapi.client.entity.TAuPapersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPapersMapper {
    int countByExample(TAuPapersExample example);

    int deleteByExample(TAuPapersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPapers record);

    int insertSelective(TAuPapers record);

    List<TAuPapers> selectByExample(TAuPapersExample example);

    TAuPapers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPapers record, @Param("example") TAuPapersExample example);

    int updateByExample(@Param("record") TAuPapers record, @Param("example") TAuPapersExample example);

    int updateByPrimaryKeySelective(TAuPapers record);

    int updateByPrimaryKey(TAuPapers record);
}