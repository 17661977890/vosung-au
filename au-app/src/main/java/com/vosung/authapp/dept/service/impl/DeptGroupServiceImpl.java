package com.vosung.authapp.dept.service.impl;

import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.dto.responsedto.deptgroup.DeptGroupResponseDto;
import com.vosung.auapi.client.dto.responsedto.deptgroup.DeptGroupTreeDto;
import com.vosung.auapi.client.entity.TAuDeptGroup;
import com.vosung.auapi.client.entity.TAuDeptGroupExample;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.DeptGroupTreeUtils;
import com.vosung.authapp.common.constant.UserHolder;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.dept.mapper.TAuDeptGroupMapper;
import com.vosung.authapp.dept.service.DeptGroupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 部门分组业务层实现类
 * @Author 彬
 * @Date 2019/4/26
 */
@Slf4j
@Service
public class DeptGroupServiceImpl extends BaseServiceImpl implements DeptGroupService{
    @Autowired
    private TAuDeptGroupMapper tAuDeptGroupMapper;
    @Autowired
    private UserHolder userHolder;


    /**
     * 保存部门分组信息
     * @param deptGroupRequestDto
     * @return
     */
    @Override
    public RestResponse saveNewDeptGroup(DeptGroupRequestDto deptGroupRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuDeptGroup tAuDeptGroup = new TAuDeptGroup();
        try {
            BeanUtils.copyProperties(tAuDeptGroup,deptGroupRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer record = 0;
        if(deptGroupRequestDto.getId() == null){
            //新增 判重--有抛出异常，无继续保存
            checkDeptGroupIsExist(deptGroupRequestDto.getDeptGroupCode());
            tAuDeptGroup.setCreateUser(loginUserId);
            tAuDeptGroup.setCreateTime(new Date());
            tAuDeptGroup.setUpdateTime(new Date());
            tAuDeptGroup.setUpdateUser(loginUserId);
            record = tAuDeptGroupMapper.insertSelective(tAuDeptGroup);
        }else {
            //修改 判重:如果code变化,
            TAuDeptGroup tAuDeptGroup1 = tAuDeptGroupMapper.selectByPrimaryKey(deptGroupRequestDto.getId());
            if(tAuDeptGroup1 == null){
                throw new BusinessException("3011111132",getMessage("3011111132"));
            }
            if(!tAuDeptGroup1.getDeptGroupCode().equalsIgnoreCase(deptGroupRequestDto.getDeptGroupCode())){
                checkDeptGroupIsExist(deptGroupRequestDto.getDeptGroupCode());
            }
            tAuDeptGroup.setUpdateTime(new Date());
            tAuDeptGroup.setUpdateUser(loginUserId);
            record = tAuDeptGroupMapper.updateByPrimaryKeySelective(tAuDeptGroup);
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存部门分组信息成功 ");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据部门分组id查询详情
     * @param deptGroupInfoRequestDto
     * @return
     */
    @Override
    public RestResponse getDeptGroupById(DeptGroupInfoRequestDto deptGroupInfoRequestDto) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        DeptGroupResponseDto deptGroupResponseDto = new DeptGroupResponseDto();
        TAuDeptGroup tAuDeptGroup = tAuDeptGroupMapper.selectByPrimaryKey(deptGroupInfoRequestDto.getId());
        if(tAuDeptGroup == null){
            throw new BusinessException("3011111132",getMessage("3011111132"));
        }
        try {
            BeanUtils.copyProperties(deptGroupResponseDto,tAuDeptGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(tAuDeptGroup.getParentGroupId() !=null){
            TAuDeptGroup tAuDeptGroup1 = tAuDeptGroupMapper.selectByPrimaryKey(tAuDeptGroup.getParentGroupId());
            deptGroupResponseDto.setParentGroupName(tAuDeptGroup1.getDeptGroupName());
        }else {
            //顶级分组 ALL
            deptGroupResponseDto.setParentGroupName("ALL");
        }
        //todo 将当前部门分组树结构也返回
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查看部门分组详情成功");
        return RestResponse.resultSuccess(deptGroupResponseDto,restResponseHeader);
    }

    /**
     * 部门分组树形结构展示
     * @return
     */
    @Override
    public RestResponse getDeptGroupTree() {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        //查询所有未删除的部门分组
        TAuDeptGroupExample example = new TAuDeptGroupExample();
        TAuDeptGroupExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuDeptGroup> tAuDeptGroupList = tAuDeptGroupMapper.selectByExample(example);
        List<DeptGroupTreeDto> treeDtos = new ArrayList<>();
        //集合元素值复制
        for (TAuDeptGroup t : tAuDeptGroupList) {
            DeptGroupTreeDto treeDto = new DeptGroupTreeDto();
            try {
                BeanUtils.copyProperties(treeDto,t);
            } catch (Exception e) {
                e.printStackTrace();
            }
           treeDtos.add(treeDto);
        }
        DeptGroupTreeUtils treeUtils = new DeptGroupTreeUtils();
        List<Object> list = treeUtils.treeMenu(treeDtos);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("部门分组树形结构展示成功");
        return RestResponse.resultSuccess(list,restResponseHeader);
    }

    /**
     * 查看部门分组编码是否存在
     * @param deptGroupCode
     */
    private void checkDeptGroupIsExist(String deptGroupCode){
        TAuDeptGroupExample tAuDeptGroupExample = new TAuDeptGroupExample();
        TAuDeptGroupExample.Criteria criteria = tAuDeptGroupExample.createCriteria();
        criteria.andDeptGroupCodeEqualTo(deptGroupCode);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuDeptGroup> tAuDeptGroupList = tAuDeptGroupMapper.selectByExample(tAuDeptGroupExample);
        if(!CollectionUtils.isEmpty(tAuDeptGroupList)){
            throw new BusinessException("3011111131",getMessage("3011111131"));
        }
    }
}
