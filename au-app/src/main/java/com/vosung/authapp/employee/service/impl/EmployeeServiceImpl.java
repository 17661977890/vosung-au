package com.vosung.authapp.employee.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.employee.*;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.employee.EmployeeDetailResponseDto;
import com.vosung.auapi.client.dto.responsedto.employee.EmployeeResponse;
import com.vosung.auapi.client.dto.responsedto.employee.EmployeeResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.dept.mapper.TAuDepartemntMapper;
import com.vosung.authapp.employee.mapper.TAuEmployeeCardMapper;
import com.vosung.authapp.employee.mapper.TAuEmployeeMapper;
import com.vosung.authapp.employee.mapper.TAuEmployeePositionMapper;
import com.vosung.authapp.employee.mapper.TAuPapersMapper;
import com.vosung.authapp.employee.service.EmployeeService;
import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.position.mapper.TAuPositionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * 员工管理业务层实现类
 * @Author 彬
 * @Date 2019/5/8
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
    @Autowired
    private TAuEmployeeMapper tAuEmployeeMapper;
    @Autowired
    private TAuEmployeePositionMapper tAuEmployeePositionMapper;
    @Autowired
    private TAuEmployeeCardMapper tAuEmployeeCardMapper;
    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private TAuDepartemntMapper tAuDepartemntMapper;
    @Autowired
    private TAuPapersMapper tAuPapersMapper;
    @Autowired
    private TAuPositionMapper tAuPositionMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;


    /**
     * 保存员工信息---------未审核组织不能新增员工
     * @param employeeRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuEmployee tAuEmployee = new TAuEmployee();
        Integer record =0;
        try {
            BeanUtils.copyProperties(tAuEmployee,employeeRequestDto);
            if(employeeRequestDto.getId() == null){
                //新增员工信息 根据员工姓名和编号进行判重
                checkIsExist(employeeRequestDto.getEmployeeName(),employeeRequestDto.getEmployeeCode());
                tAuEmployee.setDataState(DataStateCode.CREATED);
                tAuEmployee.setCreateUser(loginUserId);
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployee.setCreateTime(new Date());
                tAuEmployee.setUpdateTime(new Date());
                record = tAuEmployeeMapper.insertSelective(tAuEmployee);
                //员工任刚信息非空，同步保存
                if(!CollectionUtils.isEmpty(employeeRequestDto.getPostDtoList())){
                    //参数必要性校验
                    validParam(employeeRequestDto.getPostDtoList());
                    for (PostDto postDto:employeeRequestDto.getPostDtoList()){
                        TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                        BeanUtils.copyProperties(tAuEmployeePosition,postDto);
                        tAuEmployeePosition.setEmployeeId(tAuEmployee.getId());
                        tAuEmployeePosition.setDataState(DataStateCode.CREATED);
                        tAuEmployeePosition.setCreateUser(loginUserId);
                        tAuEmployeePosition.setUpdateUser(loginUserId);
                        tAuEmployeePosition.setCreateTime(new Date());
                        tAuEmployeePosition.setUpdateTime(new Date());
                        Integer count = tAuEmployeePositionMapper.insertSelective(tAuEmployeePosition);
                        log.info("==============新增员工时，同步保存员工任岗信息===============:"+count);
                    }
                }
                //员工财务信息非空，同步保存
                if(!CollectionUtils.isEmpty(employeeRequestDto.getEmployeeCardDtoList())){
                    for (EmployeeCardDto employeeCardDto: employeeRequestDto.getEmployeeCardDtoList()) {
                        TAuEmployeeCard tAuEmployeeCard = new TAuEmployeeCard();
                        BeanUtils.copyProperties(tAuEmployeeCard,employeeCardDto);
                        tAuEmployeeCard.setEmployeeId(tAuEmployee.getId());
                        Integer count = tAuEmployeeCardMapper.insertSelective(tAuEmployeeCard);
                        log.info("==============新增员工时，同步保存员工财务信息===============:"+count);
                    }

                }
            }else {
                //1、修改员工信息 员工姓名和编号发生变化判重
                TAuEmployee tAuEmployee1 = tAuEmployeeMapper.selectByPrimaryKey(employeeRequestDto.getId());
                boolean a = !tAuEmployee1.getEmployeeName().equalsIgnoreCase(employeeRequestDto.getEmployeeName());
                boolean b = !tAuEmployee1.getEmployeeCode().equalsIgnoreCase(employeeRequestDto.getEmployeeCode());
                if(a || b){
                    checkIsExist(employeeRequestDto.getEmployeeName(),employeeRequestDto.getEmployeeCode());
                }
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployee.setUpdateTime(new Date());
                record = tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                //2、修改任岗信息
                updatePostList(employeeRequestDto.getId(),employeeRequestDto.getPostDtoList());
                //3、修改财务信息--和任岗情况一样（这里采取删除后新增得快速方法，日后再改）
                updateCardList(employeeRequestDto.getId(),employeeRequestDto.getEmployeeCardDtoList());
            }
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(record==1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存员工信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据员工id查看员工详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getEmployeeInfoById(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(id);
        try {
            BeanUtils.copyProperties(employeeResponseDto,tAuEmployee);
            //组织
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(tAuEmployee.getCreateOrgId());
            if (tAuOrganization == null){
                throw new BusinessException("3011111116",getMessage("3011111116"));
            }
            employeeResponseDto.setCreateOrgName(tAuOrganization.getOrgName());
            employeeResponseDto.setUserOrgName(tAuOrganization.getOrgName());
            //查任岗信息
            List<PostDto> postDtoList = getPostListByEmployeeId(id);
            employeeResponseDto.setPostDtoList(postDtoList);
            //查财务信息
            List<EmployeeCardDto> employeeCardDtoList = getCardListByEmployeeId(id);
            employeeResponseDto.setEmployeeCardDtoList(employeeCardDtoList);
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("查看员工详情成功");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return RestResponse.resultSuccess(employeeResponseDto,restResponseHeader);
    }

    /**
     * 员工列表查询
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getEmployeeList(RestRequest<EmployeeListRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(EmployeeResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            List<EmployeeResponseDto> employeeResponseDtoList = tAuEmployeeMapper.getEmployeeList(restRequest.getBody());

            for (EmployeeResponseDto employeeResponseDto: employeeResponseDtoList) {
                employeeResponseDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),employeeResponseDto.getDataState()));
                EmployeeResponseDto employeeResponseDto1 = tAuEmployeeMapper.getEmployeeMainPost(employeeResponseDto.getId());
                if(employeeResponseDto1!=null){
                    employeeResponseDto.setMainPositionName(employeeResponseDto1.getMainPositionName());
                    employeeResponseDto.setDirectorPositionStartDate(employeeResponseDto1.getDirectorPositionStartDate());
                }

            }
            employeeResponse.setColumnList(viewColumnList);
            employeeResponse.setPageInfo(new PageInfo<>(employeeResponseDtoList));
            employeeResponse.setSum((int) new PageInfo<>(employeeResponseDtoList).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询员工列表成功 ");
        return RestResponse.resultSuccess(employeeResponse,restResponseHeader);
    }

    /**
     * 查看人员基本信息（详细信息，含证件/任岗信息）
     * @param employeeId
     * @return
     */
    @Override
    public RestResponse getEmployeeDetailedById(Integer employeeId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        EmployeeDetailResponseDto employeeDetailResponseDto = new EmployeeDetailResponseDto();
        TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(employeeId);
        try {
            BeanUtils.copyProperties(employeeDetailResponseDto,tAuEmployee);
            //查任岗信息
            List<PostDto> postDtoList = getPostListByEmployeeId(employeeId);
            employeeDetailResponseDto.setPostDtoList(postDtoList);
            //查证件信息
            List<PapersDto> papersDtos = getPapersListByEmployeeId(employeeId);
            employeeDetailResponseDto.setPapersList(papersDtos);
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("查看人员基本信息成功");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return RestResponse.resultSuccess(employeeDetailResponseDto,restResponseHeader);
    }

    /**
     * 修改保存人员基本信息
     * @param employeeDetailRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveEmployeeDetailed(EmployeeDetailRequestDto employeeDetailRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(employeeDetailRequestDto.getId());
        Integer record =0;
        boolean a = !tAuEmployee.getEmployeeName().equalsIgnoreCase(employeeDetailRequestDto.getEmployeeName());
        if(a){
            //如果姓名变化，编号没变，判重
            checkIsExist(employeeDetailRequestDto.getEmployeeName(),tAuEmployee.getEmployeeCode());
        }
        try {
            BeanUtils.copyProperties(tAuEmployee,employeeDetailRequestDto);
            tAuEmployee.setUpdateUser(loginUserId);
            tAuEmployee.setUpdateTime(new Date());
            record = tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
            //修改证件信息
            updatePapersList(employeeDetailRequestDto.getId(),employeeDetailRequestDto.getPapersDtoList());
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存人员基本信息成功");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据组织id和员工id 查任岗list
     * @param employeeId
     * @param orgId
     * @return
     */
    @Override
    public RestResponse getPostListByEmployeeIdOrgId(Integer employeeId, Integer orgId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuEmployeePositionExample example = new TAuEmployeePositionExample();
        TAuEmployeePositionExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeId);
        criteria.andWorkOrgIdEqualTo(orgId);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuEmployeePosition> tAuEmployeePositionList = tAuEmployeePositionMapper.selectByExample(example);
        List<PostDto> postDtoList = new ArrayList<>();
        for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
            PostDto postDto = new PostDto();
            try {
                BeanUtils.copyProperties(postDto,tAuEmployeePosition);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //出参部分信息赋值
            //组织
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(tAuEmployeePosition.getWorkOrgId());
            if (tAuOrganization == null){
                throw new BusinessException("3011111116",getMessage("3011111116"));
            }
            postDto.setWorkOrgName(tAuOrganization.getOrgName());
            //部门
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(tAuEmployeePosition.getBelongDeptId());
            if (tAuDepartemnt == null){
                throw new BusinessException("3011111123",getMessage("3011111123"));
            }
            postDto.setBelongDeptName(tAuDepartemnt.getDeptName());
            postDto.setDeptFullName(tAuDepartemnt.getDeptFullName());
            //岗位
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(tAuEmployeePosition.getDirectorPositionId());
            if (tAuPosition == null){
                throw new BusinessException("3011111212",getMessage("3011111212"));
            }
            postDto.setDirectorPositionName(tAuPosition.getPositionName());
            postDtoList.add(postDto);
        }
        restResponseHeader.setMessage("查询成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(postDtoList,restResponseHeader);
    }

    /**
     * 提交员工
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse commitEmployee(UpdateEmployeeStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] employeeIds = body.getEmployeeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < employeeIds.length; i++) {
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(Integer.valueOf(employeeIds[i]));
            //查对应任岗数据
            List<TAuEmployeePosition> tAuEmployeePositionList = getEmployeePostListById(Integer.valueOf(employeeIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuEmployee.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为:"+tAuEmployee.getEmployeeCode()+" 的员工；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                tAuEmployee.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployee.setUpdateTime(new Date());
                tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
                for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
                    tAuEmployeePosition.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                    tAuEmployeePosition.setUpdateTime(new Date());
                    tAuEmployeePosition.setUpdateUser(1);
                    Integer record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                    log.info("===============修改员工数据状态，同步修改员工任岗数据状态："+record);
                }
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","编号为:"+tAuEmployee.getEmployeeCode()+" 的员工；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 撤销员工
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse abolishEmployee(UpdateEmployeeStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] employeeIds = body.getEmployeeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < employeeIds.length; i++) {
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(Integer.valueOf(employeeIds[i]));
            //查对应任岗数据
            List<TAuEmployeePosition> tAuEmployeePositionList = getEmployeePostListById(Integer.valueOf(employeeIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuEmployee.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && b){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !b){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为重新审核
                tAuEmployee.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployee.setUpdateTime(new Date());
                tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
                for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
                    tAuEmployeePosition.setDataState(DataStateCode.REVIEW_AGAIN);
                    tAuEmployeePosition.setUpdateTime(new Date());
                    tAuEmployeePosition.setUpdateUser(1);
                    Integer record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                    log.info("===============修改员工数据状态，同步修改员工任岗数据状态："+record);
                }
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 审核员工
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse auditEmployee(UpdateEmployeeStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] employeeIds = body.getEmployeeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //审核
        for (int i = 0; i < employeeIds.length; i++) {
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(Integer.valueOf(employeeIds[i]));
            //查对应任岗数据
            List<TAuEmployeePosition> tAuEmployeePositionList = getEmployeePostListById(Integer.valueOf(employeeIds[i]));
            Map map = new HashMap();
            String dataState = tAuEmployee.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                tAuEmployee.setDataState(DataStateCode.AUDITED);
                tAuEmployee.setReviewUser(loginUserId);
                tAuEmployee.setReviewTime(new Date());
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployee.setUpdateTime(new Date());
                tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
                for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
                    tAuEmployeePosition.setDataState(DataStateCode.AUDITED);
                    tAuEmployeePosition.setUpdateTime(new Date());
                    tAuEmployeePosition.setUpdateUser(1);
                    Integer record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                    log.info("===============修改员工数据状态，同步修改员工任岗数据状态："+record);
                }
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 反审核员工
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse reverseAuditEmployee(UpdateEmployeeStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] employeeIds = body.getEmployeeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < employeeIds.length; i++) {
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(Integer.valueOf(employeeIds[i]));
            //查对应任岗数据
            List<TAuEmployeePosition> tAuEmployeePositionList = getEmployeePostListById(Integer.valueOf(employeeIds[i]));
            Map map = new HashMap();
            String dataState = tAuEmployee.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && d){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuEmployee.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                tAuEmployee.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployee.setUpdateTime(new Date());
                tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
                for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
                    tAuEmployeePosition.setDataState(DataStateCode.REVIEW_AGAIN);
                    tAuEmployeePosition.setUpdateTime(new Date());
                    tAuEmployeePosition.setUpdateUser(loginUserId);
                    Integer record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                    log.info("================修改员工数据状态，同步修改员工任岗数据状态："+record);
                }
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 删除员工
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse deleteEmployee(UpdateEmployeeStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] employeeIds = body.getEmployeeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < employeeIds.length; i++) {
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(Integer.valueOf(employeeIds[i]));
            //查对应任岗数据
            List<TAuEmployeePosition> tAuEmployeePositionList = getEmployeePostListById(Integer.valueOf(employeeIds[i]));
            Map map = new HashMap();
            String dataState = tAuEmployee.getDataState();
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) || DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(e){
                //符合要求，修改删除标识为"Y"
                tAuEmployee.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployee.setUpdateTime(new Date());
                tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                map.put("message","编号为 ："+tAuEmployee.getEmployeeCode()+" 的员工；删除成功");
                map.put("code",ConstantUtil.SUCCESS);
                for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
                    tAuEmployeePosition.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    tAuEmployeePosition.setUpdateTime(new Date());
                    tAuEmployeePosition.setUpdateUser(loginUserId);
                    Integer record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                    log.info("================逻辑删除员工，同步逻辑删除员工任岗数："+record);
                }
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","编号为："+tAuEmployee.getEmployeeCode()+" 的员工；删除失败，只有创建和暂存状态的的员工才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 禁用员工
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse forbiddenEmployee(UpdateEmployeeStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] employeeIds = body.getEmployeeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //禁用
        for (int i = 0; i < employeeIds.length; i++) {
            Map map = new HashMap();
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(Integer.valueOf(employeeIds[i]));
            //查对应任岗数据
            List<TAuEmployeePosition> tAuEmployeePositionList = getEmployeePostListById(Integer.valueOf(employeeIds[i]));
            if (ConstantUtil.YES_OR_NO_N.equals(tAuEmployee.getProhibitState())) {
                tAuEmployee.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuEmployee.setProhibitUser(loginUserId);
                tAuEmployee.setProhibitTime(new Date());
                tAuEmployee.setUpdateTime(new Date());
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                map.put("message", "编号为：" + tAuEmployee.getEmployeeCode() + " 的员工禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
                for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
                    tAuEmployeePosition.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                    tAuEmployeePosition.setProhibitUser(loginUserId);
                    tAuEmployeePosition.setProhibitTime(new Date());
                    tAuEmployeePosition.setUpdateTime(new Date());
                    tAuEmployeePosition.setUpdateUser(loginUserId);
                    Integer record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                    log.info("================禁用员工，同步禁用员工任岗数："+record);
                }
            } else {
                map.put("message", "编号为：" + tAuEmployee.getEmployeeCode() + " 的员工已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 反禁员工
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse unForbiddenEmployee(UpdateEmployeeStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] employeeIds = body.getEmployeeIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < employeeIds.length; i++) {
            Map map = new HashMap();
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(Integer.valueOf(employeeIds[i]));
            //查对应任岗数据
            List<TAuEmployeePosition> tAuEmployeePositionList = getEmployeePostListById(Integer.valueOf(employeeIds[i]));
            if (ConstantUtil.YES_OR_NO_Y.equals(tAuEmployee.getProhibitState())) {
                tAuEmployee.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuEmployee.setUpdateTime(new Date());
                tAuEmployee.setUpdateUser(loginUserId);
                tAuEmployeeMapper.updateByPrimaryKeySelective(tAuEmployee);
                map.put("message", "编号为：" + tAuEmployee.getEmployeeCode() + " 的员工反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
                for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
                    tAuEmployeePosition.setProhibitState(ConstantUtil.YES_OR_NO_N);
                    tAuEmployeePosition.setUpdateTime(new Date());
                    tAuEmployeePosition.setUpdateUser(loginUserId);
                    Integer record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                    log.info("================反禁用员工，同步反禁用员工任岗数："+record);
                }
            } else {
                map.put("message", "编号为：" + tAuEmployee.getEmployeeCode() + " 的员工已处于反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 员工保存判重
     */
    private void checkIsExist(String employeeName,String employeeCode){
        TAuEmployeeExample example = new TAuEmployeeExample();
        TAuEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeCodeEqualTo(employeeCode);
        criteria.andEmployeeNameEqualTo(employeeName);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuEmployee> tAuEmployeeList = tAuEmployeeMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(tAuEmployeeList)){
            throw new BusinessException("3011111210",getMessage("3011111210"));
        }
    }

    /**
     * 必要参数校验
     * @param postDtoList
     */
    private void validParam(List<PostDto> postDtoList){
        List<Map> messageList = new ArrayList<>();
        for (int i =0;i<postDtoList.size();i++){
            if(postDtoList.get(i).getBelongDeptId()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段所属部门是必填项");
                messageList.add(map);
            }
            if(postDtoList.get(i).getDirectorPositionId()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段就任岗位是必填项");
                messageList.add(map);
            }
            if(postDtoList.get(i).getWorkOrgId()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段工作组织是必填项");
                messageList.add(map);
            }
        }
        if(!CollectionUtils.isEmpty(messageList)){
            throw new BusinessException(ConstantUtil.ERROR,messageList.toString());
        }
    }

    /**
     * 修改任岗列表信息
     * @param employeeId
     * @param postDtoList
     * @throws Exception
     */
    private void updatePostList(Integer employeeId,List<PostDto> postDtoList) throws InvocationTargetException, IllegalAccessException {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        TAuEmployeePositionExample example = new TAuEmployeePositionExample();
        TAuEmployeePositionExample.Criteria criteria =example.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeId);
        List<TAuEmployeePosition> tAuEmployeePositions = tAuEmployeePositionMapper.selectByExample(example);
        List<Integer> postIds = new ArrayList<>();
        if(!CollectionUtils.isEmpty(tAuEmployeePositions)){

            //员工本来有任岗信息
            //(1)传参集合为空，删除原有数据
            if(CollectionUtils.isEmpty(postDtoList)){
                Integer count = tAuEmployeePositionMapper.deleteByExample(example);
                log.info("修改员工信息，员工任岗数据传参为空，本有任岗数据已删除，数量为："+count+" 删除得任岗数据为："+Arrays.toString(tAuEmployeePositions.toArray()));
            }
            //(2)传参集合不为空，校验参数，遍历任岗信息---> 有id | 有id和没有id | 没有id
            validParam(postDtoList);
            for (PostDto postDto:postDtoList) {
                if(postDto.getId()==null){
                    //没有id 新增任岗数据
                    TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                    BeanUtils.copyProperties(tAuEmployeePosition,postDto);
                    tAuEmployeePosition.setEmployeeId(employeeId);
                    tAuEmployeePosition.setDataState(DataStateCode.CREATED);
                    tAuEmployeePosition.setCreateUser(loginUserId);
                    tAuEmployeePosition.setUpdateUser(loginUserId);
                    tAuEmployeePosition.setCreateTime(new Date());
                    tAuEmployeePosition.setUpdateTime(new Date());
                    Integer count = tAuEmployeePositionMapper.insertSelective(tAuEmployeePosition);
                    log.info("==============修改员工时，同步修改员工任岗信息，此处新增任岗数据===============:"+count);
                }
                if(postDto.getId()!=null){
                    //有id，与原有数据比较多少情况
                    postIds.add(postDto.getId());
                }
            }
            for (TAuEmployeePosition tAuEmployeePosition: tAuEmployeePositions) {
                if(!postIds.contains(tAuEmployeePosition.getId())){
                    //不包含以前有的，逻辑删除之前有的任岗数据
                    tAuEmployeePosition.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    tAuEmployeePositionMapper.updateByPrimaryKey(tAuEmployeePosition);
                }else {
                    //包含，就在原有数据修改一次
                    for (PostDto postDto:postDtoList) {
                        if (tAuEmployeePosition.getId() == postDto.getId()){
                            BeanUtils.copyProperties(tAuEmployeePosition,postDto);
                            tAuEmployeePosition.setUpdateUser(loginUserId);
                            tAuEmployeePosition.setUpdateTime(new Date());
                            Integer count = tAuEmployeePositionMapper.updateByPrimaryKey(tAuEmployeePosition);
                            log.info("==============修改员工时，同步修改员工任岗信息，此处在原数据基础上修改任岗数据===============:"+count);
                        }
                    }

                }
            }
        }else {
            //员工本来没有任岗信息
            if(!CollectionUtils.isEmpty(postDtoList)){
                //（1）传参集合有值，新增数据 | 传参集合为空，则不变化
                //参数必要性校验
                validParam(postDtoList);
                for (PostDto postDto:postDtoList){
                    TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                    BeanUtils.copyProperties(tAuEmployeePosition,postDto);
                    tAuEmployeePosition.setDataState(DataStateCode.CREATED);
                    tAuEmployeePosition.setEmployeeId(employeeId);
                    tAuEmployeePosition.setCreateUser(loginUserId);
                    tAuEmployeePosition.setUpdateUser(loginUserId);
                    tAuEmployeePosition.setCreateTime(new Date());
                    tAuEmployeePosition.setUpdateTime(new Date());
                    Integer count = tAuEmployeePositionMapper.insertSelective(tAuEmployeePosition);
                    log.info("==============修改员工时，原本没有数据，此时新增关联任岗数据===============:"+count);
                }
            }

        }
    }

    /**
     * 修改财务信息
     * @param employeeId
     * @param employeeCardDtoList
     * @throws Exception
     */
    private void updateCardList(Integer employeeId,List<EmployeeCardDto> employeeCardDtoList) throws InvocationTargetException, IllegalAccessException {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        TAuEmployeeCardExample example = new TAuEmployeeCardExample();
        TAuEmployeeCardExample.Criteria criteria =example.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeId);
        List<Integer> cardIds = new ArrayList<>();
        List<TAuEmployeeCard> tAuEmployeeCardList = tAuEmployeeCardMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(tAuEmployeeCardList)){
            //员工本来有财务信息
            //(1)传参集合为空，删除原有数据
            if(CollectionUtils.isEmpty(employeeCardDtoList)){
                Integer count = tAuEmployeeCardMapper.deleteByExample(example);
                log.info("修改员工信息，员工财务数据传参为空，本有财务数据已删除，数量为："+count+" 删除得财务数据为："+Arrays.toString(tAuEmployeeCardList.toArray()));
            }
            //(2)传参集合不为空，遍历财务信息---> 有id | 有id和没有id | 没有id
            for (EmployeeCardDto employeeCardDto:employeeCardDtoList) {
                if(employeeCardDto.getId()==null){
                    //没有id 新增任岗数据
                    TAuEmployeeCard tAuEmployeeCard= new TAuEmployeeCard();
                    BeanUtils.copyProperties(tAuEmployeeCard,employeeCardDto);
                    tAuEmployeeCard.setEmployeeId(employeeId);
                    Integer count = tAuEmployeeCardMapper.insertSelective(tAuEmployeeCard);
                    log.info("==============修改员工时，同步修改员工财务信息，此处新增财务数据===============:"+count);
                }
                if(employeeCardDto.getId()!=null){
                    cardIds.add(employeeCardDto.getId());
                    //有id，在原数据进行修改

                }
            }
            for (TAuEmployeeCard tAuEmployeeCard: tAuEmployeeCardList) {
                if(!cardIds.contains(tAuEmployeeCard.getId())){
                    //不包含以前有的，逻辑删除之前有的任岗数据
                    tAuEmployeeCardMapper.deleteByPrimaryKey(tAuEmployeeCard.getId());
                    log.info("删除之前财务信息："+tAuEmployeeCard.toString());
                }else {
                    //包含，就在原有数据修改一次
                    for (EmployeeCardDto employeeCardDto:employeeCardDtoList) {
                        if (tAuEmployeeCard.getId() == employeeCardDto.getId()){
                            BeanUtils.copyProperties(tAuEmployeeCard,employeeCardDto);
                            Integer count = tAuEmployeeCardMapper.updateByPrimaryKey(tAuEmployeeCard);
                            log.info("==============修改员工时，同步修改员工财务信息，此处在原数据基础上修改财务数据===============:"+count);
                        }
                    }

                }
            }
        }else {
            //员工本来没有财务信息
            if(!CollectionUtils.isEmpty(employeeCardDtoList)){
                //（1）传参集合有值，新增数据 | 传参集合为空，则不变化
                for (EmployeeCardDto employeeCardDto:employeeCardDtoList){
                    TAuEmployeeCard tAuEmployeeCard= new TAuEmployeeCard();
                    BeanUtils.copyProperties(tAuEmployeeCard,employeeCardDto);
                    tAuEmployeeCard.setEmployeeId(employeeId);
                    Integer count = tAuEmployeeCardMapper.insertSelective(tAuEmployeeCard);
                    log.info("==============修改员工时，原本没有数据，此时新增关联财务数据===============:"+count);
                }
            }

        }
    }

    /**
     * 修改证件信息
     * @param employeeId
     * @param papersDtoList
     * @throws Exception
     */
    private void updatePapersList(Integer employeeId,List<PapersDto> papersDtoList) throws InvocationTargetException, IllegalAccessException {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        TAuPapersExample example = new TAuPapersExample();
        TAuPapersExample.Criteria criteria =example.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeId);
        List<Integer> papersIds = new ArrayList<>();
        List<TAuPapers> originalPapers = tAuPapersMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(originalPapers)){
            //员工本来有证件信息
            //(1)传参集合为空，删除原有数据
            if(CollectionUtils.isEmpty(papersDtoList)){
                Integer count = tAuPapersMapper.deleteByExample(example);
                log.info("修改人员基本信息，员工证件数据传参为空，本有证件数据已删除，数量为："+count+" 删除得证件数据为："+Arrays.toString(originalPapers.toArray()));
            }
            //(2)传参集合不为空，遍历财务信息---> 有id | 有id和没有id | 没有id
            //校验必要参数
            validPaperParam(papersDtoList);
            for (PapersDto papersDto:papersDtoList) {
                if(papersDto.getId()==null){
                    //没有id 新增任岗数据
                    TAuPapers tAuPapers= new TAuPapers();
                    BeanUtils.copyProperties(tAuPapers,papersDto);
                    tAuPapers.setEmployeeId(employeeId);
                    Integer count = tAuPapersMapper.insertSelective(tAuPapers);
                    log.info("==============修改人员基本信息时，同步修改员工证件信息，此处新增证件数据===============:"+count);
                }
                if(papersDto.getId()!=null){
                    //有id，在原数据进行修改
                    papersIds.add(papersDto.getId());
                }
            }
            for (TAuPapers originalPaper: originalPapers) {
                if(!papersIds.contains(originalPaper.getId())){
                    //不包含以前有的，逻辑删除之前有的任岗数据
                    tAuPapersMapper.deleteByPrimaryKey(originalPaper.getId());
                    log.info("删除之前证件信息："+originalPaper.toString());
                }else {
                    //包含，就在原有数据修改一次
                    for (PapersDto papersDto:papersDtoList) {
                        if (Objects.equals(originalPaper.getId(), papersDto.getId())){
                            TAuPapers tAuPapers= new TAuPapers();
                            BeanUtils.copyProperties(tAuPapers,papersDto);
                            tAuPapers.setEmployeeId(employeeId);
                            Integer count = tAuPapersMapper.updateByPrimaryKey(originalPaper);
                            log.info("==============修改人员基本信息时，同步修改员工证件信息，此处在原数据基础上修改证件数据===============:"+count);
                        }
                    }

                }
            }
        }else {
            //员工本来没有财务信息
            if(!CollectionUtils.isEmpty(papersDtoList)){
                //（1）传参集合有值，新增数据 | 传参集合为空，则不变化
                for (PapersDto papersDto:papersDtoList){
                    TAuPapers t= new TAuPapers();
                    BeanUtils.copyProperties(t,papersDto);
                    t.setEmployeeId(employeeId);
                    Integer count = tAuPapersMapper.insertSelective(t);
                    log.info("==============修改人员基本信息时，原本没有数据，此时新增关联证件数据===============:"+count);
                }
            }

        }
    }

    /**
     * 校验证件必要参数
     * @param papersDtoList
     */
    private void validPaperParam(List<PapersDto> papersDtoList){
        List<Map> messageList = new ArrayList<>();
        for (int i =0;i<papersDtoList.size();i++){
            if(papersDtoList.get(i).getCountry()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段国家地区是必填项");
                messageList.add(map);
            }
            if(papersDtoList.get(i).getPapersType()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段证件类型是必填项");
                messageList.add(map);
            }
            if(papersDtoList.get(i).getPapersNo()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段证件号码是必填项");
                messageList.add(map);
            }
        }
        if(!CollectionUtils.isEmpty(messageList)){
            throw new BusinessException(ConstantUtil.ERROR,messageList.toString());
        }
    }

    /**
     * 根据员工id 查任岗信息list---返回出参类
     * @param id
     * @return
     */
    private List<PostDto> getPostListByEmployeeId(Integer id) throws InvocationTargetException, IllegalAccessException {
        TAuEmployeePositionExample example = new TAuEmployeePositionExample();
        TAuEmployeePositionExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(id);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuEmployeePosition> tAuEmployeePositionList = tAuEmployeePositionMapper.selectByExample(example);
        List<PostDto> postDtoList = new ArrayList<>();
        for (TAuEmployeePosition tAuEmployeePosition:tAuEmployeePositionList) {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(postDto,tAuEmployeePosition);
            //出参部分信息赋值
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(tAuEmployeePosition.getWorkOrgId());
            if (tAuOrganization == null){
                throw new BusinessException("3011111116",getMessage("3011111116"));
            }
            postDto.setWorkOrgName(tAuOrganization.getOrgName());
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(tAuEmployeePosition.getBelongDeptId());
            if (tAuDepartemnt == null){
                throw new BusinessException("3011111123",getMessage("3011111123"));
            }
            postDto.setBelongDeptName(tAuDepartemnt.getDeptName());
            postDto.setDeptFullName(tAuDepartemnt.getDeptFullName());
            //岗位
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(tAuEmployeePosition.getDirectorPositionId());
            if (tAuPosition == null){
                throw new BusinessException("3011111212",getMessage("3011111212"));
            }
            postDto.setDirectorPositionName(tAuPosition.getPositionName());
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    /**
     * 根据员工id 查任岗信息list---返回实体类
     * @param id
     * @return
     */
    private List<TAuEmployeePosition> getEmployeePostListById(Integer id){
        TAuEmployeePositionExample example = new TAuEmployeePositionExample();
        TAuEmployeePositionExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(id);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuEmployeePosition> tAuEmployeePositionList = tAuEmployeePositionMapper.selectByExample(example);
        return tAuEmployeePositionList;
    }

    /**
     * 根据员工id 查财务信息列表
     * @param id
     * @return
     */
    private List<EmployeeCardDto> getCardListByEmployeeId(Integer id) throws InvocationTargetException, IllegalAccessException {
        TAuEmployeeCardExample example1 = new TAuEmployeeCardExample();
        TAuEmployeeCardExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andEmployeeIdEqualTo(id);
        List<TAuEmployeeCard> tAuEmployeeCardList = tAuEmployeeCardMapper.selectByExample(example1);
        List<EmployeeCardDto> employeeCardDtoList = new ArrayList<>();
        for (TAuEmployeeCard tAuEmployeeCard:tAuEmployeeCardList) {
            EmployeeCardDto employeeCardDto = new EmployeeCardDto();
            BeanUtils.copyProperties(employeeCardDto,tAuEmployeeCard);
            employeeCardDto.setCountryName((String)redisUtil.hget(SysDictEnum.COUNTRY.getCode(),tAuEmployeeCard.getCountry()));
            employeeCardDto.setCurrencyName((String)redisUtil.hget(SysDictEnum.CURRENCY.getCode(),tAuEmployeeCard.getCurrency()));
            employeeCardDtoList.add(employeeCardDto);
        }
        return employeeCardDtoList;
    }

    /**
     * 根据员工id查询人员证件信息
     * @param id
     * @return
     */
    private List<PapersDto> getPapersListByEmployeeId(Integer id) throws InvocationTargetException, IllegalAccessException {
        TAuPapersExample example = new TAuPapersExample();
        TAuPapersExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(id);
        List<TAuPapers> papersList = tAuPapersMapper.selectByExample(example);
        List<PapersDto> papersDtos = new ArrayList<>();
        //todo 国家证件类型字典项的配置
        for (TAuPapers tAuPapers:papersList) {
            PapersDto papersDto = new PapersDto();
            BeanUtils.copyProperties(papersDto,tAuPapers);
            papersDtos.add(papersDto);
        }
        return papersDtos;
    }
}
