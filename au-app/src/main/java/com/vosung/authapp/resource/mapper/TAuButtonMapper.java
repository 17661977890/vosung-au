package com.vosung.authapp.resource.mapper;

import com.vosung.auapi.client.dto.requestdto.button.ButtonListRequest;
import com.vosung.auapi.client.dto.responsedto.button.ButtonResponseDto;
import com.vosung.auapi.client.entity.TAuButton;
import com.vosung.auapi.client.entity.TAuButtonExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuButtonMapper {
    int countByExample(TAuButtonExample example);

    int deleteByExample(TAuButtonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuButton record);

    int insertSelective(TAuButton record);

    List<TAuButton> selectByExample(TAuButtonExample example);

    TAuButton selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuButton record, @Param("example") TAuButtonExample example);

    int updateByExample(@Param("record") TAuButton record, @Param("example") TAuButtonExample example);

    int updateByPrimaryKeySelective(TAuButton record);

    int updateByPrimaryKey(TAuButton record);

    List<ButtonResponseDto> getButtonList(ButtonListRequest buttonListRequest);
}