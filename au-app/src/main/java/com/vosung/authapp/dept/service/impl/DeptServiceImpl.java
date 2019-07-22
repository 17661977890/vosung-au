package com.vosung.authapp.dept.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.github.pagehelper.util.StringUtil;

import com.vosung.auapi.client.dto.requestdto.dept.*;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.dept.DeptInfoResponseDto;
import com.vosung.auapi.client.dto.responsedto.dept.DeptResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;

import com.vosung.authapp.dept.mapper.TAuDepartemntMapper;
import com.vosung.authapp.dept.mapper.TAuDeptGroupMapper;
import com.vosung.authapp.dept.service.DeptService;
import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.position.mapper.TAuPositionMapper;
import com.vosung.authapp.sysdata.mapper.TSysDictItemMapper;
import com.vosung.authapp.user.mapper.TAuUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 部门业务实现类
 * @Author 彬
 * @Date 2019/4/24
 */
@Slf4j
@Service
public class DeptServiceImpl extends BaseServiceImpl implements DeptService {

    @Autowired
    private TAuDepartemntMapper tAuDepartemntMapper;
    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private TAuDeptGroupMapper tAuDeptGroupMapper;
    @Autowired
    private TSysDictItemMapper tSysDictItemMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private TAuUserMapper tAuUserMapper;
    @Autowired
    private TAuPositionMapper tAuPositionMapper;


    /**
     * 保存部门
     * -----根据名称判重
     * -----初始数据状态为： 暂存
     * -----如果编码前端输入也做判重，如果后端自动生成不需要判重
     * @param deptRequestDto
     * @return
     */
    @Override
    public RestResponse saveNewDept(DeptRequestDto deptRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuDepartemnt tAuDepartemnt = new TAuDepartemnt();
        //属性复制
        try {
            BeanUtils.copyProperties(tAuDepartemnt,deptRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer record = 0;
        if(deptRequestDto.getId() == null){
            //新增判重
            List<TAuDepartemnt> tAuDepartemntList = checkIsExist(deptRequestDto.getDeptName());
            if(!CollectionUtils.isEmpty(tAuDepartemntList)){
                throw new BusinessException("3011111121",getMessage("3011111121"));
            }
            //id为空，新增---并返回主键,设置部门编码再次修改。
            //初始数据状态为：暂存---前端可传，不需要设
            if(deptRequestDto.getParentDeptId()!=null){
                TAuDepartemnt departemnt = tAuDepartemntMapper.selectByPrimaryKey(deptRequestDto.getParentDeptId());
                tAuDepartemnt.setDeptFullName(departemnt.getDeptName()+"/"+deptRequestDto.getDeptName());
            }
            tAuDepartemnt.setCreateUser(loginUserId);
            tAuDepartemnt.setCreateTime(new Date());
            tAuDepartemnt.setUpdateTime(new Date());
            tAuDepartemnt.setUpdateUser(loginUserId);
            tAuDepartemnt.setDataState(DataStateCode.TEMPORARY_STORAGE);
            //mapper.xml文件配置，插入自动返回主键id
            record = tAuDepartemntMapper.insertSelective(tAuDepartemnt);
            tAuDepartemnt.setDeptCode("BM_"+tAuDepartemnt.getId());
            //只是修改部分字段：updateByPrimaryKeySelective
            tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
        }else {
            //id存在，修改(已审核不可修改)---可从入参中获取，不需要在查
            TAuDepartemnt tAuDepartemnt1 = tAuDepartemntMapper.selectByPrimaryKey(deptRequestDto.getId());
            if(tAuDepartemnt1 == null){
                throw new BusinessException("3011111123",getMessage("3011111123"));
            }
            if(DataStateCode.AUDITED.equals(tAuDepartemnt1.getDataState())){
                throw new BusinessException("3011111122",getMessage("3011111122"));
            }
            if(!tAuDepartemnt1.getDeptName().equalsIgnoreCase(deptRequestDto.getDeptName())){
                //如果修改后部门名和之前不同，则需要再次判重，不同，不判
                List<TAuDepartemnt> tAuDepartemntList = checkIsExist(deptRequestDto.getDeptName());
                if(!CollectionUtils.isEmpty(tAuDepartemntList)){
                    throw new BusinessException("3011111121",getMessage("3011111121"));
                }
            }
            tAuDepartemnt.setUpdateTime(new Date());
            tAuDepartemnt.setUpdateUser(loginUserId);
            record = tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存部门信息成功 ");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 部门列表分页展示------有些没有写，和数据库字段类型不对应
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getDeptList(RestRequest<GetDeptListRequestDto> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        DeptResponseDto deptResponseDto = new DeptResponseDto();
        Integer pageNum = restRequest.getHeader().getPageNum();
        Integer pageSize = restRequest.getHeader().getPageSize();
        try{
            //显示字段
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(DeptInfoResponseDto.class.getName());

            GetDeptListRequestDto getDeptListRequestDto = restRequest.getBody();
            TAuDepartemntExample example = new TAuDepartemntExample();
            TAuDepartemntExample.Criteria criteria = example.createCriteria();
            //确定条件：未删除的  和  某使用组织下的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            if(getDeptListRequestDto.getUseOrgId()!=null){
                criteria.andUseOrgIdEqualTo(getDeptListRequestDto.getUseOrgId());
            }
            if(StringUtil.isNotEmpty(getDeptListRequestDto.getDeptCode())){
                criteria.andDeptCodeLike("%"+getDeptListRequestDto.getDeptCode()+"%");
            }
            if(StringUtil.isNotEmpty(getDeptListRequestDto.getMnemonicCode())){
                criteria.andMnemonicCodeLike("%"+getDeptListRequestDto.getMnemonicCode()+"%");
            }
            if(StringUtil.isNotEmpty(getDeptListRequestDto.getMnemonicCode())){
                criteria.andMnemonicCodeLike("%"+getDeptListRequestDto.getMnemonicCode()+"%");
            }
            if(StringUtil.isNotEmpty(getDeptListRequestDto.getDeptProperty())){
                criteria.andMnemonicCodeLike("%"+getDeptListRequestDto.getMnemonicCode()+"%");
            }
            if(StringUtil.isNotEmpty(getDeptListRequestDto.getSummary())){
                criteria.andDeptPropertyLike("%"+getDeptListRequestDto.getDeptProperty()+"%");
            }
            if(StringUtil.isNotEmpty(getDeptListRequestDto.getDeptName())){
                criteria.andDeptNameLike("%"+getDeptListRequestDto.getDeptName()+"%");
            }
            if(getDeptListRequestDto.getParentDeptId()!=null){
                criteria.andParentDeptIdEqualTo(getDeptListRequestDto.getParentDeptId());
            }
            if(getDeptListRequestDto.getEffectDate()!=null){
                criteria.andEffectDateEqualTo(getDeptListRequestDto.getEffectDate());
            }
            if(getDeptListRequestDto.getAbateDate()!=null){
                criteria.andAbateDateEqualTo(getDeptListRequestDto.getAbateDate());
            }
            if(getDeptListRequestDto.getDeptGroupId()!=null){
                criteria.andDeptGroupIdEqualTo(getDeptListRequestDto.getDeptGroupId());
            }
            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuDepartemnt> tAuDepartemntList = tAuDepartemntMapper.selectByExample(example);
            List<DeptInfoResponseDto> deptInfoResponseDtos = new ArrayList<>();
            for (TAuDepartemnt tAuDepartemnt: tAuDepartemntList) {
                DeptInfoResponseDto deptInfoResponseDto= new DeptInfoResponseDto();
                BeanUtils.copyProperties(deptInfoResponseDto,tAuDepartemnt);
                deptInfoResponseDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),deptInfoResponseDto.getDataState()));
                //所属组织
                TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(deptInfoResponseDto.getCreateOrgId());
                String createOrgName = tAuOrganization.getOrgName();
                deptInfoResponseDto.setCreateOrgName(createOrgName);
                deptInfoResponseDto.setUseOrgName(createOrgName);

                if(deptInfoResponseDto.getParentDeptId()!=null){
                    //父级部门名称
                    tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(deptInfoResponseDto.getParentDeptId());
                    deptInfoResponseDto.setParentDeptName(tAuDepartemnt.getDeptName());
                }

                deptInfoResponseDtos.add(deptInfoResponseDto);
            }
            Integer sumCount = tAuDepartemntMapper.countByExample(example);

            deptResponseDto.setColumnList(viewColumnList);
            deptResponseDto.setPageInfo(new PageInfo<>(deptInfoResponseDtos));
            deptResponseDto.setSum(sumCount);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询部门列表成功 ");
        return RestResponse.resultSuccess(deptResponseDto,restResponseHeader);
    }

    /**
     * 根据id查询部门详情
     * @param deptInfoRequestDto
     * @return
     */
    @Override
    public RestResponse getDeptInfoById(DeptInfoRequestDto deptInfoRequestDto) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        DeptInfoResponseDto deptInfoResponseDto = new DeptInfoResponseDto();
        //查询部门信息（属性复制）
        TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(deptInfoRequestDto.getId());
        if(tAuDepartemnt == null){
            throw new BusinessException("3011111123",getMessage("3011111123"));
        }
        try {
            BeanUtils.copyProperties(deptInfoResponseDto,tAuDepartemnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //组织机构名称赋值
        TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(deptInfoResponseDto.getCreateOrgId());
        String createOrgName = tAuOrganization.getOrgName();
        tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(deptInfoResponseDto.getUseOrgId());
        String useOrgName = tAuOrganization.getOrgName();
        deptInfoResponseDto.setCreateOrgName(createOrgName);
        deptInfoResponseDto.setUseOrgName(useOrgName);
        if(deptInfoResponseDto.getDeptGroupId() != null){
            //部门分组名称
            TAuDeptGroup tAuDeptGroup = tAuDeptGroupMapper.selectByPrimaryKey(deptInfoResponseDto.getDeptGroupId());
            deptInfoResponseDto.setDeptGroupName(tAuDeptGroup.getDeptGroupName());
        }
        if(deptInfoResponseDto.getParentDeptId()!=null){
            //父级部门名称
            tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(deptInfoResponseDto.getParentDeptId());
            deptInfoResponseDto.setParentDeptName(tAuDepartemnt.getDeptName());
        }
        if(StringUtil.isNotEmpty(deptInfoResponseDto.getDeptProperty())){
            //部门属性名称
            TSysDictItemExample example = new TSysDictItemExample();
            TSysDictItemExample.Criteria criteria = example.createCriteria();
            criteria.andDictCodeEqualTo(SysDictEnum.DEPT_PROPERTY.getCode());
            criteria.andItemCodeEqualTo(deptInfoResponseDto.getDeptProperty());
            List<TSysDictItem> tSysDictItems = tSysDictItemMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(tSysDictItems)){
                deptInfoResponseDto.setDeptPropertyName(tSysDictItems.get(0).getItemName());
            }
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查看部门详情成功");
        return RestResponse.resultSuccess(deptInfoResponseDto,restResponseHeader);
    }

    /**
     * 提交部门
     * @param body
     * @return
     */
    @Override
    public RestResponse commitDept(UpdateDeptDataStateDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] deptIds = body.getDeptIds().split(",");
        for (int i=0;i<deptIds.length;i++) {
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(Integer.valueOf(deptIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuDepartemnt.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为:"+tAuDepartemnt.getDeptCode()+" 的部门；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                tAuDepartemnt.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuDepartemnt.setUpdateUser(loginUserId);
                tAuDepartemnt.setUpdateTime(new Date());
                tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","编号为:"+tAuDepartemnt.getDeptCode()+" 的部门；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 撤销部门
     * @param body
     * @return
     */
    @Override
    public RestResponse abolishDept(UpdateDeptDataStateDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] deptIds = body.getDeptIds().split(",");
        for (int i=0;i<deptIds.length;i++) {
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(Integer.valueOf(deptIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuDepartemnt.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && b){
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !b){
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为重新审核
                tAuDepartemnt.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuDepartemnt.setUpdateUser(loginUserId);
                tAuDepartemnt.setUpdateTime(new Date());
                tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 审核部门
     * @param body
     * @return
     */
    @Override
    public RestResponse auditDept(UpdateDeptDataStateDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] deptIds = body.getDeptIds().split(",");
        for (int i=0;i<deptIds.length;i++) {
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(Integer.valueOf(deptIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuDepartemnt.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                tAuDepartemnt.setDataState(DataStateCode.AUDITED);
                tAuDepartemnt.setReviewUser(loginUserId);
                tAuDepartemnt.setReviewTime(new Date());
                tAuDepartemnt.setUpdateUser(loginUserId);
                tAuDepartemnt.setUpdateTime(new Date());
                tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反审核部门
     * @param body
     * @return
     */
    @Override
    public RestResponse reverseAuditDept(UpdateDeptDataStateDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] deptIds = body.getDeptIds().split(",");
        for (int i=0;i<deptIds.length;i++) {
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(Integer.valueOf(deptIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuDepartemnt.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && d){
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuDepartemnt.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map = new HashMap();
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                tAuDepartemnt.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuDepartemnt.setUpdateUser(loginUserId);
                tAuDepartemnt.setUpdateTime(new Date());
                tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 删除部门
     * @param body
     * @return
     */
    @Override
    public RestResponse deleteDept(UpdateDeptDataStateDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        //删除
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] deptIds = body.getDeptIds().split(",");
        for (int i=0;i<deptIds.length;i++) {
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(Integer.valueOf(deptIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuDepartemnt.getDataState();
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) || DataStateCode.REVIEW_AGAIN.equals(dataState);
            //删除之前检查其他表有没有引用，没有才会删除-----检查用户表T_AU_USER和岗位信息表T_AU_POSITION
            Boolean e1 = testUserAndPosition(Integer.valueOf(deptIds[i]));
            if(e1 && e){
                map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门被岗位或者用户使用，请检查");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(e1 && !e){
                map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门被岗位或者用户使用，请检查");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的岗位；删除失败，只有创建和暂存状态的部门才可删除");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(e){
                //符合要求，修改删除标识为"Y"
                tAuDepartemnt.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuDepartemnt.setUpdateUser(loginUserId);
                tAuDepartemnt.setUpdateTime(new Date());
                tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
                map.put("message","编号为 ："+tAuDepartemnt.getDeptCode()+" 的部门；删除成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门；删除失败，只有创建和暂存状态的部门才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 禁用部门
     * @param body
     * @return
     */
    @Override
    public RestResponse forbiddenDept(UpdateDeptDataStateDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] deptIds = body.getDeptIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < deptIds.length; i++) {
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(Integer.valueOf(deptIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            Boolean q = ConstantUtil.YES_OR_NO_N.equals(tAuDepartemnt.getProhibitState());
            // 检查岗位是否存在有效岗位
            Boolean q1 = testUserAndPosition2(Integer.valueOf(deptIds[i]));
            if(q1){
                map.put("message","编号为："+tAuDepartemnt.getDeptCode()+" 的部门存在非禁用岗位或用户");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if (q) {
                tAuDepartemnt.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuDepartemnt.setProhibitUser(loginUserId);
                tAuDepartemnt.setProhibitTime(new Date());
                tAuDepartemnt.setUpdateTime(new Date());
                tAuDepartemnt.setUpdateUser(loginUserId);
                tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
                map.put("message", "编号为：" + tAuDepartemnt.getDeptCode() + " 的部门禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "编号为：" + tAuDepartemnt.getDeptCode() + " 的部门已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反禁部门
     * @param body
     * @return
     */
    @Override
    public RestResponse unForbiddenDept(UpdateDeptDataStateDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] deptIds = body.getDeptIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < deptIds.length; i++) {
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(Integer.valueOf(deptIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_Y.equals(tAuDepartemnt.getProhibitState())) {
                tAuDepartemnt.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuDepartemnt.setUpdateTime(new Date());
                tAuDepartemnt.setUpdateUser(loginUserId);
                tAuDepartemntMapper.updateByPrimaryKeySelective(tAuDepartemnt);
                map.put("message", "编号为：" + tAuDepartemnt.getDeptCode()  + " 的部门反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "编号为：" +tAuDepartemnt.getDeptCode() + " 的部门已处于反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 检查是否存在同名部门
     * @param deptName
     * @return
     */
    private List<TAuDepartemnt> checkIsExist(String deptName){
        TAuDepartemntExample tAuDepartemntExample = new TAuDepartemntExample();
        TAuDepartemntExample.Criteria criteria = tAuDepartemntExample.createCriteria();
        criteria.andDeptNameEqualTo(deptName);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuDepartemnt> tAuDepartemntList = tAuDepartemntMapper.selectByExample(tAuDepartemntExample);
        return tAuDepartemntList;
    }

    /**
     * 根据部门id 查 岗位和用户表 看部门是否被使用(未删除)
     * @param deptId
     * @return
     */
    private Boolean testUserAndPosition(Integer deptId){
        TAuUserExample example = new TAuUserExample();
        TAuUserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andBelongDeptIdEqualTo(deptId);
        List<TAuUser> userList= tAuUserMapper.selectByExample(example);

        TAuPositionExample example1 = new TAuPositionExample();
        TAuPositionExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria1.andBelongDeptIdEqualTo(deptId);
        List<TAuPosition> positionList = tAuPositionMapper.selectByExample(example1);

        if(CollectionUtils.isEmpty(userList)&& CollectionUtils.isEmpty(positionList)){
            return false;
        }else {
            return true;
        }

    }

    /**
     * 检查部门下是否存在非禁用岗位或者用户
     * @param deptId
     * @return
     */
    private Boolean testUserAndPosition2(Integer deptId){
        TAuUserExample example = new TAuUserExample();
        TAuUserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andBelongDeptIdEqualTo(deptId);
        criteria.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuUser> userList= tAuUserMapper.selectByExample(example);

        TAuPositionExample example1 = new TAuPositionExample();
        TAuPositionExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria1.andBelongDeptIdEqualTo(deptId);
        criteria1.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuPosition> positionList = tAuPositionMapper.selectByExample(example1);

        if(CollectionUtils.isEmpty(userList)&& CollectionUtils.isEmpty(positionList)){
            return false;
        }else {
            return true;
        }

    }

}
