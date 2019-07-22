package com.vosung.authapp.position.mapper;

import com.vosung.auapi.client.dto.requestdto.position.PositionListRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionRequestDto;
import com.vosung.auapi.client.dto.responsedto.position.PositionResponseDto;
import com.vosung.auapi.client.entity.TAuPosition;
import com.vosung.auapi.client.entity.TAuPositionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface TAuPositionMapper {
    int countByExample(TAuPositionExample example);

    int deleteByExample(TAuPositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPosition record);

    int insertSelective(TAuPosition record);

    List<TAuPosition> selectByExample(TAuPositionExample example);

    TAuPosition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPosition record, @Param("example") TAuPositionExample example);

    int updateByExample(@Param("record") TAuPosition record, @Param("example") TAuPositionExample example);

    int updateByPrimaryKeySelective(TAuPosition record);

    int updateByPrimaryKey(TAuPosition record);

    List<TAuPosition> selectDownPositionByPositionId(@Param("positionId") Integer id);

    List<TAuPosition> selectPositionUPByReportType(@Param("reportType") String reportType);
    List<TAuPosition> selectPositionDOWNByReportType(@Param("reportType") String reportType);

    /**
     * 查询岗位列表
     * @param positionRequestDto
     * @return
     */
    List<PositionResponseDto> getPositionList(PositionListRequestDto positionRequestDto);

    /**
     * 查询列表数
     * @param positionRequestDto
     * @return
     */
    Integer getCount(PositionListRequestDto positionRequestDto);
}