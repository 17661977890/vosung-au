package com.vosung.authapp.org.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.*;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.orgrelation.OrgRelationResponseDto;
import com.vosung.auapi.client.dto.responsedto.orgrelationscheme.GetSchemeInfoResponseDto;
import com.vosung.auapi.client.dto.responsedto.orgrelationscheme.OrgSchemeListResponse;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.org.mapper.TAuOrgRelationMapper;
import com.vosung.authapp.org.mapper.TAuOrgSchemeMapper;
import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.org.service.OrgRelationSchemeService;
import com.vosung.authapp.org.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 组织机构隶属关系方案 业务逻辑层
 * @Author 彬
 * @Date 2019/4/26
 */
@Slf4j
@Service
public class OrgRelationSchemeServiceImpl extends BaseServiceImpl implements OrgRelationSchemeService {

    @Autowired
    private TAuOrgSchemeMapper tAuOrgSchemeMapper;
    @Autowired
    private TAuOrgRelationMapper tAuOrgRelationMapper;
    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private OrgService orgService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;


    /**
     * 保存组织机构隶属关系方案
     * @param relationSchemeRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveOrgRelationScheme(OrgRelationSchemeRequestDto relationSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuOrgScheme tAuOrgScheme = new TAuOrgScheme();
        try {
            BeanUtils.copyProperties(tAuOrgScheme,relationSchemeRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer record = 0;
        if(relationSchemeRequestDto.getId() == null){
            //没有id 新增 判重
            checkSchemeIsExist(relationSchemeRequestDto.getSchemeCode());
            tAuOrgScheme.setDataState(DataStateCode.CREATED);
            tAuOrgScheme.setCreateUser(loginUserId);
            tAuOrgScheme.setCreateTime(new Date());
            tAuOrgScheme.setUpdateUser(loginUserId);
            tAuOrgScheme.setUpdateTime(new Date());
            record = tAuOrgSchemeMapper.insertSelective(tAuOrgScheme);
            //保存组织关系(递归拆解树型结构json，组装组织关系对象)
            JSONObject jsonObject = relationSchemeRequestDto.getOrgRelationJsonTree();
            if(jsonObject != null){
                List<TAuOrgRelation> tAuOrgRelationList = new ArrayList<>();
                //因为只有一个顶层节点，首先将顶级组织添加，后面只是负责将子节点部分进行解析
                TAuOrgRelation tAuOrgRelation = new TAuOrgRelation();
                tAuOrgRelation.setOrgId((Integer)jsonObject.get("orgId"));
                tAuOrgRelation.setSuperiorOrgId(0);
                tAuOrgRelationList.add(tAuOrgRelation);
                tAuOrgRelationList = getOrgRelationList(tAuOrgRelationList,jsonObject, (Integer)jsonObject.get("orgId"));
                log.info("此方案下增设的组织关系实体有：",Arrays.toString(tAuOrgRelationList.toArray()));
                List<Integer> ids = new ArrayList<>();
                for (TAuOrgRelation tAuOrgRelation1: tAuOrgRelationList) {
                    tAuOrgRelation1.setDataState(DataStateCode.CREATED);
                    tAuOrgRelation1.setUpdateTime(new Date());
                    tAuOrgRelation1.setUpdateUser(loginUserId);
                    tAuOrgRelation1.setCreateUser(loginUserId);
                    tAuOrgRelation1.setCreateTime(new Date());
                    tAuOrgRelationMapper.insertSelective(tAuOrgRelation1);
                    //返回组织关系表主键id
                    ids.add(tAuOrgRelation1.getId());
                }
                //将关系表id 集合解析为逗号分隔的字符串
                relationSchemeRequestDto.setRelationIds(StringUtils.join(ids,","));
                //关系表关联方案id，修改relation表
                relatedSchemeId(relationSchemeRequestDto.getRelationIds(),tAuOrgScheme.getId());
            }

        }else {
            //有id 修改 编码变化判重---已审核、审核中不能修改（创建 暂存和重新审核可以修改，前端控制）
            TAuOrgScheme tAuOrgScheme1 = tAuOrgSchemeMapper.selectByPrimaryKey(relationSchemeRequestDto.getId());
            if(!tAuOrgScheme1.getSchemeCode().equalsIgnoreCase(relationSchemeRequestDto.getSchemeCode())){
                checkSchemeIsExist(relationSchemeRequestDto.getSchemeCode());
            }
            tAuOrgScheme.setUpdateUser(loginUserId);
            tAuOrgScheme.setUpdateTime(new Date());
            record = tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
            //修改组织关系(递归拆解树型结构json，组装组织关系对象)------查之前方案下的组织关系list ，对比，有的不做操作，没有的删除，多的新增
            JSONObject jsonObject = relationSchemeRequestDto.getOrgRelationJsonTree();
            if(jsonObject != null){
                List<TAuOrgRelation> tAuOrgRelationList = new ArrayList<>();
                //因为只有一个顶层节点，首先将顶级组织添加，后面只是负责将子节点部分进行解析
                TAuOrgRelation tAuOrgRelation = new TAuOrgRelation();
                tAuOrgRelation.setOrgId((Integer)jsonObject.get("orgId"));
                tAuOrgRelation.setSuperiorOrgId(0);
                tAuOrgRelationList.add(tAuOrgRelation);
                tAuOrgRelationList = getOrgRelationList(tAuOrgRelationList,jsonObject, (Integer)jsonObject.get("orgId"));
                log.info("此方案下增设的组织关系实体有：",Arrays.toString(tAuOrgRelationList.toArray()));
                //删除之前所有的该方案下的组织关系list，重新保存新数据
                TAuOrgRelationExample example1 = new TAuOrgRelationExample();
                TAuOrgRelationExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andSchemeIdEqualTo(relationSchemeRequestDto.getId());
                Integer count = tAuOrgRelationMapper.deleteByExample(example1);
                log.info("删除之前改方案关联的组织关系列表数量："+count);
                for (TAuOrgRelation tAuOrgRelation1: tAuOrgRelationList) {
                    tAuOrgRelation1.setSchemeId(relationSchemeRequestDto.getId());
                    tAuOrgRelation1.setDataState(DataStateCode.CREATED);
                    tAuOrgRelation1.setUpdateTime(new Date());
                    tAuOrgRelation1.setUpdateUser(loginUserId);
                    tAuOrgRelation1.setCreateTime(new Date());
                    tAuOrgRelation1.setCreateUser(loginUserId);
                    tAuOrgRelationMapper.insertSelective(tAuOrgRelation1);
                }

            }
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("组织机构隶属关系方案保存成功 ");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 查看组织隶属方案详情--含组织关系树
     * @param getSchemeInfoRequestDto
     * @return
     */
    @Override
    public RestResponse getSchemeInfoById(GetSchemeInfoRequestDto getSchemeInfoRequestDto) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(getSchemeInfoRequestDto.getId());
        if(tAuOrgScheme == null){
            throw new BusinessException("3011111142",getMessage("3011111142"));
        }
        GetSchemeInfoResponseDto getSchemeInfoResponseDto = new GetSchemeInfoResponseDto();
        try {
            //方案信息复制
            BeanUtils.copyProperties(getSchemeInfoResponseDto,tAuOrgScheme);
            TAuOrganization tAuOrganization1 = tAuOrganizationMapper.selectByPrimaryKey(tAuOrgScheme.getTopOrgId());
            if(tAuOrganization1 ==null){
                throw new BusinessException("3011111116",getMessage("3011111116"));
            }else {
                getSchemeInfoResponseDto.setTopOrgName(tAuOrganization1.getOrgName());
            }
            //组织关系关系信息复制--树节点信息
            TAuOrgRelationExample example = new TAuOrgRelationExample();
            TAuOrgRelationExample.Criteria criteria = example.createCriteria();
            criteria.andSchemeIdEqualTo(getSchemeInfoRequestDto.getId());
            List<TAuOrgRelation> tAuOrgRelations = tAuOrgRelationMapper.selectByExample(example);
            //过滤条件结果集
            List<Integer> orgIdList = new ArrayList<>();
            //关联方案组织关系列表
            List<OrgRelationResponseDto> list = new ArrayList<>();
            for (TAuOrgRelation t: tAuOrgRelations) {
                OrgRelationResponseDto orgRelationResponseDto = new OrgRelationResponseDto();
                BeanUtils.copyProperties(orgRelationResponseDto,t);
                TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(t.getOrgId());
                orgRelationResponseDto.setOrgName(tAuOrganization.getOrgName());
                orgIdList.add(t.getOrgId());
                list.add(orgRelationResponseDto);
            }
            OrgRelationTreeUtils treeUtils = new OrgRelationTreeUtils();
            List<Object> treeList = treeUtils.treeMenu(list);
            //封装出参----组织关系树
            getSchemeInfoResponseDto.setTreeList(treeList);
            //还有未关联组织关系的组织列表
            List<TAuOrganization> noSelectedList = getSelectAbleOrgList(orgIdList);
            getSchemeInfoResponseDto.setSelectAbleOrgList(noSelectedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("方案详情查询成功");
        return RestResponse.resultSuccess(getSchemeInfoResponseDto,restResponseHeader);
    }

    /**
     * 组织机构方案列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getOrgSchemeList(RestRequest<GetSchemeListRequestDto> restRequest) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        OrgSchemeListResponse orgSchemeListResponse = new OrgSchemeListResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(GetSchemeInfoResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            TAuOrgSchemeExample example = new TAuOrgSchemeExample();
            TAuOrgSchemeExample.Criteria criteria = example.createCriteria();
            //显示未删除的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);

            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuOrgScheme> tAuOrgSchemeList =  tAuOrgSchemeMapper.selectByExample(example);
            List<GetSchemeInfoResponseDto> schemeInfoResponseDtoList = new ArrayList<>();

            for (TAuOrgScheme tAuOrgScheme: tAuOrgSchemeList) {
                GetSchemeInfoResponseDto getSchemeInfoResponseDto=new GetSchemeInfoResponseDto();
                BeanUtils.copyProperties(getSchemeInfoResponseDto,tAuOrgScheme);
                getSchemeInfoResponseDto.setOrgFunctionTypeName((String) redisUtil.hget(SysDictEnum.OPERATION_ORG.getCode(),getSchemeInfoResponseDto.getOrgFunctionType()));
                getSchemeInfoResponseDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),getSchemeInfoResponseDto.getDataState()));
                if(getSchemeInfoResponseDto.getTopOrgId()!=null){
                    TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(getSchemeInfoResponseDto.getTopOrgId());
                    if(tAuOrganization!=null){
                        getSchemeInfoResponseDto.setTopOrgName(tAuOrganization.getOrgName());
                    }
                }
                schemeInfoResponseDtoList.add(getSchemeInfoResponseDto);
            }
            Integer sum = tAuOrgSchemeMapper.countByExample(example);
            orgSchemeListResponse.setColumnList(viewColumnList);
            orgSchemeListResponse.setPageInfo(new PageInfo<>(schemeInfoResponseDtoList));
            orgSchemeListResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询组织机构方案列表成功 ");
        return RestResponse.resultSuccess(orgSchemeListResponse,restResponseHeader);
    }

    /**
     * 反禁用组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    @Override
    public RestResponse unForbiddenScheme(UpdateSchemeRequestDto updateSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] schemeIds = updateSchemeRequestDto.getOrgSchemeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<schemeIds.length;i++) {
            //将每个修改的结果信息，放在每个map中
            TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(Integer.valueOf(schemeIds[i]));
            if(tAuOrgScheme==null){
                throw new BusinessException("3011111142",getMessage("3011111142"));
            }
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_Y.equals(tAuOrgScheme.getProhibitState())) {
                tAuOrgScheme.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuOrgScheme.setProhibitUser(loginUserId);
                tAuOrgScheme.setProhibitTime(new Date());
                tAuOrgScheme.setUpdateTime(new Date());
                tAuOrgScheme.setUpdateUser(loginUserId);
                tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
                map.put("message", "编号为：" + tAuOrgScheme.getSchemeCode() + "  的方案反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "编号为：" + tAuOrgScheme.getSchemeCode() + "  的方案已处于反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        if(messageList.size() == 1){
            restResponseHeader.setMessage((String) messageList.get(0).get("message"));
            restResponseHeader.setCode((String)messageList.get(0).get("code"));
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 禁用组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    @Override
    public RestResponse forbiddenScheme(UpdateSchemeRequestDto updateSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        //禁用
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] schemeIds = updateSchemeRequestDto.getOrgSchemeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<schemeIds.length;i++) {
            //将每个修改的结果信息，放在每个map中
            TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(Integer.valueOf(schemeIds[i]));
            if(tAuOrgScheme==null){
                throw new BusinessException("3011111142",getMessage("3011111142"));
            }
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_N.equals(tAuOrgScheme.getProhibitState())) {
                tAuOrgScheme.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuOrgScheme.setProhibitUser(loginUserId);
                tAuOrgScheme.setProhibitTime(new Date());
                tAuOrgScheme.setUpdateTime(new Date());
                tAuOrgScheme.setUpdateUser(loginUserId);
                tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
                map.put("message", "编号为：" + tAuOrgScheme.getSchemeCode() + "  的方案禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "编号为：" + tAuOrgScheme.getSchemeCode() + "  的方案已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 删除组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    @Override
    public RestResponse deleteScheme(UpdateSchemeRequestDto updateSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        //删除
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] schemeIds = updateSchemeRequestDto.getOrgSchemeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<schemeIds.length;i++) {
            //将每个修改的结果信息，放在每个map中
            TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(Integer.valueOf(schemeIds[i]));
            if(tAuOrgScheme==null){
                throw new BusinessException("3011111142",getMessage("3011111142"));
            }
            Map map = new HashMap();
            String dataState = tAuOrgScheme.getDataState();
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) || DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(e){
                //符合要求，修改删除标识为"Y"
                tAuOrgScheme.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuOrgScheme.setUpdateUser(loginUserId);
                tAuOrgScheme.setUpdateTime(new Date());
                tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
                //删除时，也将对应的方案关联的组织关系进行逻辑删除
                syncOrgRelation(tAuOrgScheme.getId(),ConstantUtil.DELETE);
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；删除成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","编号为："+tAuOrgScheme.getSchemeCode()+" 的方案；删除失败，只有创建和暂存状态的组织机构才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反审核组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    @Override
    public RestResponse reverseAuditScheme(UpdateSchemeRequestDto updateSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] schemeIds = updateSchemeRequestDto.getOrgSchemeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<schemeIds.length;i++) {
            //将每个修改的结果信息，放在每个map中
            TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(Integer.valueOf(schemeIds[i]));
            if(tAuOrgScheme==null){
                throw new BusinessException("3011111142",getMessage("3011111142"));
            }
            Map map = new HashMap();
            String dataState = tAuOrgScheme.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && d){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuOrgScheme.getSchemeCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                tAuOrgScheme.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuOrgScheme.setUpdateUser(loginUserId);
                tAuOrgScheme.setUpdateTime(new Date());
                tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
                //同步修改组织关系数据状态
                syncOrgRelation(tAuOrgScheme.getId(),ConstantUtil.REVERSE_AUDIT);
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的部门；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为："+tAuOrgScheme.getSchemeCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 提交组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    @Override
    public RestResponse commitScheme(UpdateSchemeRequestDto updateSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] schemeIds = updateSchemeRequestDto.getOrgSchemeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<schemeIds.length;i++) {
            //将每个修改的结果信息，放在每个map中
            TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(Integer.valueOf(schemeIds[i]));
            if(tAuOrgScheme==null){
                throw new BusinessException("3011111142",getMessage("3011111142"));
            }
            Map map = new HashMap();
            String dataState = tAuOrgScheme.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 :"+tAuOrgScheme.getSchemeCode()+" 的方案；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                tAuOrgScheme.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuOrgScheme.setUpdateUser(loginUserId);
                tAuOrgScheme.setUpdateTime(new Date());
                tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
                //同步修改组织关系数据状态
                syncOrgRelation(tAuOrgScheme.getId(),ConstantUtil.COMMIT);
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","编号为 :"+tAuOrgScheme.getSchemeCode()+" 的方案；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
}
    /**
     * 撤销组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    @Override
    public RestResponse abolishScheme(UpdateSchemeRequestDto updateSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] schemeIds = updateSchemeRequestDto.getOrgSchemeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<schemeIds.length;i++) {
            TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(Integer.valueOf(schemeIds[i]));
            if(tAuOrgScheme==null){
                throw new BusinessException("3011111142",getMessage("3011111142"));
            }
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuOrgScheme.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && b){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !b){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuOrgScheme.getSchemeCode()+" 的方案；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为重新审核
                tAuOrgScheme.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuOrgScheme.setUpdateUser(loginUserId);
                tAuOrgScheme.setUpdateTime(new Date());
                tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
                //同步修改组织关系数据状态
                syncOrgRelation(tAuOrgScheme.getId(),ConstantUtil.ABOLISH);
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为："+tAuOrgScheme.getSchemeCode()+" 的方案；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 审核组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    @Override
    public RestResponse auditScheme(UpdateSchemeRequestDto updateSchemeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] schemeIds = updateSchemeRequestDto.getOrgSchemeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<schemeIds.length;i++) {
            TAuOrgScheme tAuOrgScheme = tAuOrgSchemeMapper.selectByPrimaryKey(Integer.valueOf(schemeIds[i]));
            if(tAuOrgScheme==null){
                throw new BusinessException("3011111142",getMessage("3011111142"));
            }
            Map map = new HashMap();
            String dataState = tAuOrgScheme.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrgScheme.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                tAuOrgScheme.setDataState(DataStateCode.AUDITED);
                tAuOrgScheme.setReviewUser(loginUserId);
                tAuOrgScheme.setReviewTime(new Date());
                tAuOrgScheme.setUpdateUser(loginUserId);
                tAuOrgScheme.setUpdateTime(new Date());
                tAuOrgSchemeMapper.updateByPrimaryKeySelective(tAuOrgScheme);
                //同步修改组织关系数据状态
                syncOrgRelation(tAuOrgScheme.getId(),ConstantUtil.AUDIT);
                map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为 ："+tAuOrgScheme.getSchemeCode()+" 的方案；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 过滤结果集（未选组织关系的组织列表）
     * @param relatedOrgIdList
     * @return
     */
    private List<TAuOrganization> getSelectAbleOrgList(List<Integer> relatedOrgIdList){
        //先查所有可选组织
        RestResponse restResponse = orgService.getSelectableOrgList();
        List<TAuOrganization> allSelectAbleOrgList = (List<TAuOrganization>) restResponse.getBody();
        List<TAuOrganization> result = new ArrayList<>();
        result = allSelectAbleOrgList.stream()
                .filter((TAuOrganization t) -> !relatedOrgIdList.contains(t.getId()))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 同步处理隶属方案关联的组织关系数据状态
     * @param schemeId
     * @return
     */
    private void syncOrgRelation(Integer schemeId,String type){
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        TAuOrgRelationExample example = new TAuOrgRelationExample();
        TAuOrgRelationExample.Criteria criteria =example.createCriteria();
        criteria.andSchemeIdEqualTo(schemeId);
        List<TAuOrgRelation> tAuOrgRelations = tAuOrgRelationMapper.selectByExample(example);
        for (TAuOrgRelation t:tAuOrgRelations) {
            if(type.equalsIgnoreCase(ConstantUtil.COMMIT)){
                t.setUpdateUser(loginUserId);
                t.setUpdateTime(new Date());
                t.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
            }else if(type.equalsIgnoreCase(ConstantUtil.ABOLISH)){
                t.setUpdateUser(loginUserId);
                t.setUpdateTime(new Date());
                t.setDataState(DataStateCode.REVIEW_AGAIN);
            }else if(type.equalsIgnoreCase(ConstantUtil.AUDIT)){
                t.setUpdateUser(loginUserId);
                t.setUpdateTime(new Date());
                t.setDataState(DataStateCode.AUDITED);
            }else if(type.equalsIgnoreCase(ConstantUtil.REVERSE_AUDIT)){
                t.setUpdateUser(loginUserId);
                t.setUpdateTime(new Date());
                t.setDataState(DataStateCode.REVIEW_AGAIN);
            }else if(type.equalsIgnoreCase(ConstantUtil.DELETE)){
                t.setUpdateUser(loginUserId);
                t.setUpdateTime(new Date());
                t.setIsDelete(ConstantUtil.YES_OR_NO_Y);
            }
            tAuOrgRelationMapper.updateByPrimaryKeySelective(t);
        }
    }

    /**
     * 组织机构关系表关联方案id
     */
    private void relatedSchemeId(String ids, Integer id){
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        if(StringUtils.isNotEmpty(ids)){
            TAuOrgRelation tAuOrgRelation = new TAuOrgRelation();
            String[] idArray = ids.split(",");
            for(int i=0;i<idArray.length;i++){
                tAuOrgRelation.setId(Integer.valueOf(idArray[i]));
                tAuOrgRelation.setSchemeId(id);
                tAuOrgRelation.setUpdateUser(loginUserId);
                tAuOrgRelation.setUpdateTime(new Date());
                tAuOrgRelationMapper.updateByPrimaryKeySelective(tAuOrgRelation);
            }
            log.info("===============组织关系关联方案结束===========");
        }
    }

    /**
     * 对方案编码进行判重
     * @param schemeCode
     */
    private void checkSchemeIsExist(String schemeCode){
        TAuOrgSchemeExample example = new TAuOrgSchemeExample();
        TAuOrgSchemeExample.Criteria criteria = example.createCriteria();
        criteria.andSchemeCodeEqualTo(schemeCode);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuOrgScheme> tAuOrgSchemeList = tAuOrgSchemeMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(tAuOrgSchemeList)){
            throw new BusinessException("3011111141",getMessage("3011111141"));
        }
    }

    /**
     * 通过递归解析前端提供的组织关系树型结构的json字符串，组装成我们所需的组织关系实体
     * @param tAuOrgRelationList
     * @param jsonObject
     * @param parentId
     * @return
     */
    private List<TAuOrgRelation> getOrgRelationList(List<TAuOrgRelation> tAuOrgRelationList, JSONObject jsonObject, int parentId){
        for (Object object: (List)jsonObject.get("children")) {
            if(!CollectionUtils.isEmpty((List)jsonObject.get("children"))) {
                JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(object);
                TAuOrgRelation tAuOrgRelation = new TAuOrgRelation();
                tAuOrgRelation.setOrgId((Integer) jsonObject1.get("orgId"));
                tAuOrgRelation.setSuperiorOrgId(parentId);
                tAuOrgRelationList.add(tAuOrgRelation);
                getOrgRelationList(tAuOrgRelationList,jsonObject1, (Integer) jsonObject1.get("orgId"));
            }
        }
        return tAuOrgRelationList;
    }
}
