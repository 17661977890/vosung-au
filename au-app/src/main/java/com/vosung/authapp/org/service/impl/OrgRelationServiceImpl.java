package com.vosung.authapp.org.service.impl;

import com.vosung.auapi.client.dto.requestdto.orgrelation.DeleteOrgRelationRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelation.OrgRelationRequestDto;
import com.vosung.auapi.client.entity.TAuOrgRelation;
import com.vosung.auapi.client.entity.TAuOrgRelationExample;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.DataStateCode;
import com.vosung.authapp.common.constant.UserHolder;
import com.vosung.authapp.org.mapper.TAuOrgRelationMapper;
import com.vosung.authapp.org.service.OrgRelationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 组织关系业务处理实现类
 * @Author 彬
 * @Date 2019/4/28
 */
@Slf4j
@Service
public class OrgRelationServiceImpl extends BaseServiceImpl implements OrgRelationService {
    @Autowired
    private TAuOrgRelationMapper tAuOrgRelationMapper;
    @Autowired
    private UserHolder userHolder;


    /**
     * 新增组织关系
     * @param orgRelationRequestDto
     * @return
     */
    @Override
    public RestResponse addOrgRelation(OrgRelationRequestDto orgRelationRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuOrgRelation tAuOrgRelation = new TAuOrgRelation();
        try {
            BeanUtils.copyProperties(tAuOrgRelation,orgRelationRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tAuOrgRelation.setCreateTime(new Date());
        tAuOrgRelation.setCreateUser(loginUserId);
        tAuOrgRelation.setUpdateTime(new Date());
        tAuOrgRelation.setUpdateUser(loginUserId);
        tAuOrgRelation.setDataState(DataStateCode.CREATED);
        Integer record = tAuOrgRelationMapper.insertSelective(tAuOrgRelation);
        if(record ==1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("新增组织关系成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据主键id 删除组织关系（如果删除的组织关系id的组织id 是父节点，那子节点也都删除）
     *这里只是删除组织关系是真删；
     * 如果是方案删除，那么就是跟着方案走，所有关联对应方案的关系数据逻辑删。
     * @param deleteOrgRelationRequestDto
     * @return
     */
    @Override
    public RestResponse deleteOrgRelation(DeleteOrgRelationRequestDto deleteOrgRelationRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        //如果前端也把orgId 传过来，不用再查
        TAuOrgRelation tAuOrgRelation = tAuOrgRelationMapper.selectByPrimaryKey(deleteOrgRelationRequestDto.getId());
        TAuOrgRelationExample example = new TAuOrgRelationExample();
        TAuOrgRelationExample.Criteria criteria = example.createCriteria();
        criteria.andSuperiorOrgIdEqualTo(tAuOrgRelation.getOrgId());
        List<TAuOrgRelation> tAuOrgRelationList = tAuOrgRelationMapper.selectByExample(example);
        Integer record = 0;
        for (TAuOrgRelation t: tAuOrgRelationList) {
            tAuOrgRelationMapper.deleteByPrimaryKey(t.getId());
            record++;
        }
        tAuOrgRelationMapper.deleteByPrimaryKey(deleteOrgRelationRequestDto.getId());
        record++;
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("删除组织关系成功");
        return RestResponse.resultSuccess(record,restResponseHeader);
    }
}
