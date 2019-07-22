package com.vosung.authapp.role.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.datarule.DataRuleConditionDto;
import com.vosung.auapi.client.dto.requestdto.datarule.DataRuleRequestDto;
import com.vosung.auapi.client.dto.requestdto.datarule.GetDataRuleListDto;
import com.vosung.auapi.client.dto.requestdto.datarule.UpdateDataRuleStatusDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.datarule.DataRuleInfoResponse;
import com.vosung.auapi.client.dto.responsedto.datarule.DataRuleListResponseDto;
import com.vosung.auapi.client.dto.responsedto.datarule.DataRuleResponse;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.FieldMessageCls;
import com.vosung.authapp.common.constant.UserHolder;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.role.mapper.TAuDataRuleConditionMapper;
import com.vosung.authapp.role.mapper.TAuDataRuleMapper;
import com.vosung.authapp.role.service.DataRuleService;
import com.vosung.boapi.client.BoClient;
import com.vosung.boapi.entity.Result;
import com.vosung.boapi.pojo.DoTempPubModel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 数据规则业务层实现类
 * @Author 彬
 * @Date 2019/7/4
 */
@Slf4j
@Service
public class DataRuleServiceImpl extends BaseServiceImpl implements DataRuleService {


    @Autowired
    private TAuDataRuleMapper tAuDataRuleMapper;
    @Autowired
    private TAuDataRuleConditionMapper tAuDataRuleConditionMapper;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private BoClient boClient;

    /**
     * 保存数据规则：
     * 同一个业务对象，不能有相同的规则名称，但是规则code 可以相同
     * @param dataRuleRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveDataRule(DataRuleRequestDto dataRuleRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<DataRuleConditionDto> dataRuleConditionDtoList = dataRuleRequestDto.getDataRuleConditionDtoList();
        Integer record = 0;
        boolean a =false;
        boolean b =false;
        if(dataRuleRequestDto.getId()==null){
            //新增操作--判重
            checkIsExist(dataRuleRequestDto.getBusinessObjectId(),dataRuleRequestDto.getDataRuleName());
            TAuDataRule tAuDataRule = new TAuDataRule();
            //引入此包org.springframework.beans.BeanUtils，方法的参数位置与之前的相反
            BeanUtils.copyProperties(dataRuleRequestDto,tAuDataRule);
            tAuDataRule.setCreateTime(new Date());
            tAuDataRule.setCreateUser(loginUserId);
            tAuDataRule.setUpdateTime(new Date());
            tAuDataRule.setUpdateUser(loginUserId);
            //前端新增默认系统预设为否
            tAuDataRule.setIsSystemInit(ConstantUtil.YES_OR_NO_N);
            //修改mapper.xml,返回主键id
            record = tAuDataRuleMapper.insertSelective(tAuDataRule);
            for (DataRuleConditionDto dataRuleConditionDto:dataRuleConditionDtoList) {
                //判断条件数据 括号是否符合规范 （每条要么左右都有，要么都没有）
                a = StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                b = !StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && !StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                if (a || b){
                    TAuDataRuleCondition tAuDataRuleCondition = new TAuDataRuleCondition();
                    BeanUtils.copyProperties(dataRuleConditionDto,tAuDataRuleCondition);
                    tAuDataRuleCondition.setDataRuleId(tAuDataRule.getId());
                    tAuDataRuleCondition.setCreateTime(new Date());
                    tAuDataRuleCondition.setCreateUser(loginUserId);
                    tAuDataRuleCondition.setUpdateTime(new Date());
                    tAuDataRuleCondition.setUpdateUser(loginUserId);
                    tAuDataRuleConditionMapper.insertSelective(tAuDataRuleCondition);
                }else {
                    throw new BusinessException("3011111412",getMessage("3011111412"));
                }

            }
        }else {
            //修改操作--判重(不能修改业务对象)
            TAuDataRule tAuDataRule = tAuDataRuleMapper.selectByPrimaryKey(dataRuleRequestDto.getId());
            if(!tAuDataRule.getDataRuleName().equals(dataRuleRequestDto.getDataRuleName())){
                checkIsExist(dataRuleRequestDto.getBusinessObjectId(),dataRuleRequestDto.getDataRuleName());
            }
            tAuDataRule.setDataRuleCode(dataRuleRequestDto.getDataRuleCode());
            tAuDataRule.setDataRuleName(dataRuleRequestDto.getDataRuleName());
            tAuDataRule.setUpdateUser(loginUserId);
            tAuDataRule.setUpdateTime(new Date());
            record = tAuDataRuleMapper.updateByPrimaryKeySelective(tAuDataRule);
            //修改数据规则条件（几种情况： 根据规则id查原有条件与新传的数据对比--->id （如果有对应的） ）
            updateDataRuleCondition(a,b,loginUserId,tAuDataRule.getId(),dataRuleConditionDtoList);
        }
        if(record==1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存数据规则信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 查数据规则列表(暂时查所有)
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getDataRuleList(RestRequest<GetDataRuleListDto> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        DataRuleResponse dataRuleResponse = new DataRuleResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(DataRuleListResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            TAuDataRuleExample example = new TAuDataRuleExample();
            TAuDataRuleExample.Criteria criteria= example.createCriteria();
            //显示未删除的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            if(restRequest.getBody().getProhibitState()!=null){
                criteria.andProhibitStateEqualTo(restRequest.getBody().getProhibitState());
            }
            if(restRequest.getBody().getBusinessObjectId()!=null){
                criteria.andBusinessObjectIdEqualTo(restRequest.getBody().getBusinessObjectId());
            }
            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuDataRule> tAuDataRuleList =  tAuDataRuleMapper.selectByExample(example);
            List<DataRuleListResponseDto> dataRuleListResponseDtos = new ArrayList<>();

            for (TAuDataRule tAuDataRule: tAuDataRuleList) {
                DataRuleListResponseDto dataRuleListResponseDto =new DataRuleListResponseDto();
                BeanUtils.copyProperties(tAuDataRule,dataRuleListResponseDto);
                //todo 调bo接口查业务对象信息（根据业务对象id）
                DoTempPubModel doTempPubModel = new DoTempPubModel();
                try {
                    Result result = boClient.selectAuBo(dataRuleListResponseDto.getBusinessObjectId());
                    doTempPubModel = (DoTempPubModel)result.getData();
                }catch (Exception e){
                    log.info("查数据规则列表接口：=====>调bo接口：{根据业务对象id查相关详情信息}====>失败");
                    e.printStackTrace();
                }
                if(doTempPubModel == null){
                    throw new BusinessException("4011111111",getMessage("4011111111"));
                }else {
                    dataRuleListResponseDto.setBusinessObjectName(doTempPubModel.getName());
                }
                dataRuleListResponseDtos.add(dataRuleListResponseDto);
            }
            Integer sum = tAuDataRuleMapper.countByExample(example);
            dataRuleResponse.setColumnList(viewColumnList);
            dataRuleResponse.setPageInfo(new PageInfo<>(dataRuleListResponseDtos));
            dataRuleResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询数据规则列表成功 ");
        return RestResponse.resultSuccess(dataRuleResponse,restResponseHeader);
    }

    /**
     * 查看数据规则详情（包括其绑定的数据条件）
     * @param id
     * @return
     */
    @Override
    public RestResponse getDataRuleInfo(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        DataRuleInfoResponse dataRuleInfoResponse = new DataRuleInfoResponse();
        //查数据规则信息
        TAuDataRule tAuDataRule = tAuDataRuleMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(tAuDataRule,dataRuleInfoResponse);
        //todo 调bo接口查业务对象名称
        DoTempPubModel doTempPubModel = new DoTempPubModel();
        try {
            Result result = boClient.selectAuBo(tAuDataRule.getBusinessObjectId());
            doTempPubModel = (DoTempPubModel)result.getData();
        }catch (Exception e){
            log.info("查数据规则列表接口：====>调bo接口：{根据业务对象id查相关详情信息}====>失败");
            e.printStackTrace();
        }
        if(doTempPubModel == null){
            throw new BusinessException("4011111111",getMessage("4011111111"));
        }else {
            dataRuleInfoResponse.setBusinessObjectName(doTempPubModel.getName());
        }
        //查相关条件
        TAuDataRuleConditionExample example = new TAuDataRuleConditionExample();
        TAuDataRuleConditionExample.Criteria criteria = example.createCriteria();
        criteria.andDataRuleIdEqualTo(id);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuDataRuleCondition> tAuDataRuleConditions = tAuDataRuleConditionMapper.selectByExample(example);
        List<DataRuleConditionDto> dataRuleConditionDtos = new ArrayList<>();
        for (TAuDataRuleCondition tAuDataRuleCondition:tAuDataRuleConditions) {
            DataRuleConditionDto dataRuleConditionDto = new DataRuleConditionDto();
            BeanUtils.copyProperties(tAuDataRuleCondition,dataRuleConditionDto);
            dataRuleConditionDtos.add(dataRuleConditionDto);
        }
        dataRuleInfoResponse.setDataRuleConditionDtoList(dataRuleConditionDtos);
        restResponseHeader.setMessage("查看数据规则详情成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(dataRuleInfoResponse,restResponseHeader);
    }

    /**
     * 删除数据规则
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse deleteDataRule(UpdateDataRuleStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] dataRuleIds = body.getDataRuleIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<dataRuleIds.length;i++){
            Map map = new HashMap();
            TAuDataRule tAuDataRule = tAuDataRuleMapper.selectByPrimaryKey(Integer.valueOf(dataRuleIds[i]));
            //删除
            if(ConstantUtil.YES_OR_NO_N.equals(tAuDataRule.getIsDelete())){
                tAuDataRule.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuDataRule.setUpdateTime(new Date());
                tAuDataRule.setUpdateUser(loginUserId);
                tAuDataRuleMapper.updateByPrimaryKeySelective(tAuDataRule);
                map.put("message","数据规则编码为："+tAuDataRule.getDataRuleCode()+" 删除成功");
                map.put("code",ConstantUtil.SUCCESS);
                //todo 同时删除数据规则绑定的条件的数据
                TAuDataRuleConditionExample example = new TAuDataRuleConditionExample();
                TAuDataRuleConditionExample.Criteria criteria = example.createCriteria();
                criteria.andDataRuleIdEqualTo(Integer.valueOf(dataRuleIds[i]));
                criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
                List<TAuDataRuleCondition> tAuDataRuleConditions = tAuDataRuleConditionMapper.selectByExample(example);
                Integer count = 0;
                for (TAuDataRuleCondition tAuDataRuleCondition:tAuDataRuleConditions) {
                    tAuDataRuleCondition.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    tAuDataRuleCondition.setUpdateUser(loginUserId);
                    tAuDataRuleCondition.setUpdateTime(new Date());
                    tAuDataRuleConditionMapper.updateByPrimaryKeySelective(tAuDataRuleCondition);
                    count++;
                }
                log.info("逻辑删除数据规则=====>同步逻辑删除数据规则绑定条件数："+count);

            }
            messageList.add(map);

        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 禁用数据规则
     * @param body
     * @return
     */
    @Override
    public RestResponse forbiddenDataRule(UpdateDataRuleStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] dataRuleIds = body.getDataRuleIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<dataRuleIds.length;i++){
            Map map = new HashMap();
            TAuDataRule tAuDataRule = tAuDataRuleMapper.selectByPrimaryKey(Integer.valueOf(dataRuleIds[i]));
            //禁用
            if(ConstantUtil.YES_OR_NO_N.equals(tAuDataRule.getProhibitState())){
                tAuDataRule.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuDataRule.setUpdateTime(new Date());
                tAuDataRule.setUpdateUser(loginUserId);
                tAuDataRuleMapper.updateByPrimaryKeySelective(tAuDataRule);
                map.put("message","数据规则编码为："+tAuDataRule.getDataRuleCode()+" 禁用成功");
                map.put("code",ConstantUtil.SUCCESS);
                //todo 同时禁用数据规则绑定的条件
                TAuDataRuleConditionExample example = new TAuDataRuleConditionExample();
                TAuDataRuleConditionExample.Criteria criteria = example.createCriteria();
                criteria.andDataRuleIdEqualTo(Integer.valueOf(dataRuleIds[i]));
                criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
                List<TAuDataRuleCondition> tAuDataRuleConditions = tAuDataRuleConditionMapper.selectByExample(example);
                Integer count = 0;
                for (TAuDataRuleCondition tAuDataRuleCondition:tAuDataRuleConditions) {
                    tAuDataRuleCondition.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                    tAuDataRuleCondition.setProhibitUser(loginUserId);
                    tAuDataRuleCondition.setProhibitTime(new Date());
                    tAuDataRuleConditionMapper.updateByPrimaryKeySelective(tAuDataRuleCondition);
                    count++;
                }
                log.info("禁用数据规则=====>同步禁用数据规则绑定条件数："+count);
            }else {
                map.put("message","数据规则编码为："+tAuDataRule.getDataRuleCode()+" 已禁用");
                map.put("code",ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 反禁用数据规则
     * @param body
     * @return
     */
    @Override
    public RestResponse unForbiddenDataRule(UpdateDataRuleStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] dataRuleIds = body.getDataRuleIds().split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<dataRuleIds.length;i++){
            Map map = new HashMap();
            TAuDataRule tAuDataRule = tAuDataRuleMapper.selectByPrimaryKey(Integer.valueOf(dataRuleIds[i]));
            //禁用
            if(ConstantUtil.YES_OR_NO_Y.equals(tAuDataRule.getProhibitState())){
                tAuDataRule.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuDataRule.setUpdateTime(new Date());
                tAuDataRule.setUpdateUser(loginUserId);
                tAuDataRuleMapper.updateByPrimaryKeySelective(tAuDataRule);
                map.put("message","数据规则编码为："+tAuDataRule.getDataRuleCode()+" 反禁用成功");
                map.put("code",ConstantUtil.SUCCESS);
                //todo 同时禁用数据规则绑定的条件
                TAuDataRuleConditionExample example = new TAuDataRuleConditionExample();
                TAuDataRuleConditionExample.Criteria criteria = example.createCriteria();
                criteria.andDataRuleIdEqualTo(Integer.valueOf(dataRuleIds[i]));
                criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
                List<TAuDataRuleCondition> tAuDataRuleConditions = tAuDataRuleConditionMapper.selectByExample(example);
                Integer count = 0;
                for (TAuDataRuleCondition tAuDataRuleCondition:tAuDataRuleConditions) {
                    tAuDataRuleCondition.setProhibitState(ConstantUtil.YES_OR_NO_N);
                    tAuDataRuleCondition.setProhibitUser(loginUserId);
                    tAuDataRuleCondition.setProhibitTime(new Date());
                    tAuDataRuleConditionMapper.updateByPrimaryKeySelective(tAuDataRuleCondition);
                    count++;
                }
                log.info("反禁用数据规则=====>同步反禁用数据规则绑定条件数："+count);
            }else {
                map.put("message","数据规则编码为："+tAuDataRule.getDataRuleCode()+" 已处于反禁用状态");
                map.put("code",ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }


    /**
     * 修改数据规则条件
     * @param a
     * @param b
     * @param loginUserId
     * @param dataRuleId
     * @param dataRuleConditionDtos
     */
    private void updateDataRuleCondition(boolean a,boolean b,Integer loginUserId,Integer dataRuleId,List<DataRuleConditionDto> dataRuleConditionDtos) {
        TAuDataRuleConditionExample example = new TAuDataRuleConditionExample();
        TAuDataRuleConditionExample.Criteria criteria = example.createCriteria();
        criteria.andDataRuleIdEqualTo(dataRuleId);
        List<TAuDataRuleCondition> tAuDataRuleConditions = tAuDataRuleConditionMapper.selectByExample(example);
        List<Integer> conditionIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tAuDataRuleConditions)) {
            //原来有数据，看入参情况（入参为空--删除原有  入参不为空--比较原有和传入的情况）
            //入参为空，删除原有(逻辑删除)
            if (CollectionUtils.isEmpty(dataRuleConditionDtos)) {
                Integer count = 0;
                for (TAuDataRuleCondition tAuDataRuleCondition : tAuDataRuleConditions) {
                    tAuDataRuleCondition.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    tAuDataRuleConditionMapper.updateByPrimaryKeySelective(tAuDataRuleCondition);
                    count++;
                }
                log.info("修改数据规则=====>该数据规则绑定的条件之前有数据，入参为空，此时逻辑删除条件按数据数量：" + count);
            }
            //入参不为空，比较数据变化
            for (DataRuleConditionDto dataRuleConditionDto : dataRuleConditionDtos) {
                //入参数据id 为空--新增数据
                if (dataRuleConditionDto.getId() == null) {
                    a = StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                    b = !StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && !StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                    if (a || b) {
                        TAuDataRuleCondition tAuDataRuleCondition = new TAuDataRuleCondition();
                        BeanUtils.copyProperties( dataRuleConditionDto,tAuDataRuleCondition);
                        tAuDataRuleCondition.setDataRuleId(dataRuleId);
                        tAuDataRuleCondition.setCreateTime(new Date());
                        tAuDataRuleCondition.setCreateUser(loginUserId);
                        tAuDataRuleCondition.setUpdateTime(new Date());
                        tAuDataRuleCondition.setUpdateUser(loginUserId);
                        tAuDataRuleConditionMapper.insertSelective(tAuDataRuleCondition);
                        log.info("修改数据规则====>该数据规则绑定的条件之前有数据，入参数据无id，此时新增条件数据");
                    } else {
                        throw new BusinessException("3011111412", getMessage("3011111412"));
                    }

                } else {
                    //入参条件数据有id
                    conditionIds.add(dataRuleConditionDto.getId());
                }
            }
            for (TAuDataRuleCondition tAuDataRuleCondition : tAuDataRuleConditions) {
                if (!conditionIds.contains(tAuDataRuleCondition.getId())) {
                    //入参条件数据有id，但是不含以前有的，逻辑删除之前有的这个数据
                    tAuDataRuleCondition.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    tAuDataRuleConditionMapper.updateByPrimaryKeySelective(tAuDataRuleCondition);
                    log.info("修改数据规则====>该数据规则绑定的条件之前有数据，入参数据有id，但是原有数据不能匹配，逻辑删除原有数据，原数据id：" + tAuDataRuleCondition.getId());
                } else {
                    //是原来数据，修改内容信息
                    for (DataRuleConditionDto dataRuleConditionDto : dataRuleConditionDtos) {
                        a = StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                        b = !StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && !StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                        if(a || b){
                            if (Objects.equals(tAuDataRuleCondition.getId(), dataRuleConditionDto.getId())) {
                                //数据复制
                                BeanUtils.copyProperties(dataRuleConditionDto,tAuDataRuleCondition);
                                tAuDataRuleCondition.setDataRuleId(dataRuleId);
                                tAuDataRuleCondition.setUpdateTime(new Date());
                                tAuDataRuleCondition.setUpdateUser(loginUserId);
                                tAuDataRuleConditionMapper.updateByPrimaryKeySelective(tAuDataRuleCondition);
                                log.info("修改数据规则====>该数据规则绑定的条件之前有数据，入参数据有id，并与原有数据id一样，修改该数据信息，原数据id：" + tAuDataRuleCondition.getId());
                            }
                        }else {
                            throw new BusinessException("3011111412", getMessage("3011111412"));
                        }

                    }
                }
            }

        } else {
            //原来没有数据，只要有数据，就新增
            Integer count = 0;
            if (!CollectionUtils.isEmpty(dataRuleConditionDtos)) {
                for (DataRuleConditionDto dataRuleConditionDto : dataRuleConditionDtos) {
                    a = StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                    b = !StringUtils.isEmpty(dataRuleConditionDto.getLeftBrackets()) && !StringUtils.isEmpty(dataRuleConditionDto.getRightBrackets());
                    if (a || b) {
                        TAuDataRuleCondition tAuDataRuleCondition = new TAuDataRuleCondition();
                        BeanUtils.copyProperties(dataRuleConditionDto,tAuDataRuleCondition);
                        tAuDataRuleCondition.setDataRuleId(dataRuleId);
                        tAuDataRuleCondition.setCreateTime(new Date());
                        tAuDataRuleCondition.setCreateUser(loginUserId);
                        tAuDataRuleCondition.setUpdateTime(new Date());
                        tAuDataRuleCondition.setUpdateUser(loginUserId);
                        tAuDataRuleConditionMapper.insertSelective(tAuDataRuleCondition);
                        count++;
                    }else {
                        throw new BusinessException("3011111412", getMessage("3011111412"));
                    }
                    log.info("数据规则修改====>该数据规则绑定的条件原来没有数据，此时新增数据数量：" + count);
                }

            }
        }
    }

    /**
     * 根据业务对象id 和 数据规则名 判重
     * @param businessObjectId
     * @param dataRuleName
     */
    private void checkIsExist(Integer businessObjectId,String dataRuleName){
        TAuDataRuleExample example = new TAuDataRuleExample();
        TAuDataRuleExample.Criteria criteria = example.createCriteria();
        criteria.andBusinessObjectIdEqualTo(businessObjectId);
        criteria.andDataRuleNameEqualTo(dataRuleName);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuDataRule> tAuDataRuleList = tAuDataRuleMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(tAuDataRuleList)){
            throw new BusinessException("3011111411",getMessage("3011111411"));
        }
    }
}
