package com.vosung.authapp.employee.mapper;

import com.vosung.auapi.client.dto.requestdto.employee.EmployeeListRequest;
import com.vosung.auapi.client.dto.responsedto.employee.EmployeeResponseDto;
import com.vosung.auapi.client.entity.TAuEmployee;
import com.vosung.auapi.client.entity.TAuEmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuEmployeeMapper {
    int countByExample(TAuEmployeeExample example);

    int deleteByExample(TAuEmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuEmployee record);

    int insertSelective(TAuEmployee record);

    List<TAuEmployee> selectByExample(TAuEmployeeExample example);

    TAuEmployee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuEmployee record, @Param("example") TAuEmployeeExample example);

    int updateByExample(@Param("record") TAuEmployee record, @Param("example") TAuEmployeeExample example);

    int updateByPrimaryKeySelective(TAuEmployee record);

    int updateByPrimaryKey(TAuEmployee record);

    /**
     * 查员工列表
     * @param employeeListRequest
     * @return
     */
    List<EmployeeResponseDto> getEmployeeList(EmployeeListRequest employeeListRequest);

    /**
     * 获取员工主任岗信息
     * @param employeeId
     * @return
     */
    EmployeeResponseDto getEmployeeMainPost(Integer employeeId);

    /**
     * 查询某岗位未任岗的员工list
     * @param employeeIdList
     * @return
     */
    List<EmployeeResponseDto> getUNPostEmployeeList(List<Integer> employeeIdList);
}