package com.vosung.authapp.resource.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.button.ButtonListRequest;
import com.vosung.auapi.client.dto.requestdto.button.ButtonRequestDto;
import com.vosung.auapi.client.dto.requestdto.button.UpdateButtonStatusDto;
import com.vosung.auapi.client.dto.responsedto.button.ButtonResponse;
import com.vosung.auapi.client.dto.responsedto.button.ButtonResponseDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.roleresource.ButtonResourceResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.resource.mapper.TAuButtonMapper;
import com.vosung.authapp.resource.mapper.TAuMenuMapper;
import com.vosung.authapp.resource.service.ButtonService;
import com.vosung.authapp.role.mapper.TAuRoleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 按钮业务实现类
 * @Author 彬
 * @Date 2019/5/22
 */
@Slf4j
@Service
public class ButtonServiceImpl extends BaseServiceImpl implements ButtonService {
    @Autowired
    private TAuButtonMapper tAuButtonMapper;
    @Autowired
    private TAuRoleResourceMapper tAuRoleResourceMapper;
    @Autowired
    private TAuMenuMapper tAuMenuMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;

    /**
     * 保存按钮
     * @param buttonRequestDto
     * @return
     */
    @Override
    public RestResponse saveButton(ButtonRequestDto buttonRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuButton tAuButton = new TAuButton();
        Integer record =0;
        try {
            BeanUtils.copyProperties(tAuButton,buttonRequestDto);
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(buttonRequestDto.getMenuId());
            if(ConstantUtil.YES_OR_NO_Y.equalsIgnoreCase(tAuMenu.getIsMenuAndButton())){
                //选择所属菜单id 又是可点击按钮，那么父按钮id必填
                if(buttonRequestDto.getParentId()==null){
                    throw new BusinessException(ConstantUtil.ERROR,"该按钮的父按钮不能为空");
                }
            }else {
                //没有父按钮id  就是默认0
                tAuButton.setParentId(0);
            }
            if(buttonRequestDto.getId() == null){
                //新增按钮---根据按钮编码来判重（按钮名称可以重复）
                checkIsExist(buttonRequestDto.getButtonCode());
                tAuButton.setDataState(DataStateCode.CREATED);
                tAuButton.setCreateUser(loginUserId);
                tAuButton.setUpdateUser(loginUserId);
                tAuButton.setCreateTime(new Date());
                tAuButton.setUpdateTime(new Date());
                record = tAuButtonMapper.insertSelective(tAuButton);
            }else {
                //修改按钮
                TAuButton tAuButton1 = tAuButtonMapper.selectByPrimaryKey(buttonRequestDto.getId());
                if(tAuButton1 == null){
                    throw new BusinessException("3011111320",getMessage("3011111320"));
                }
                if(!tAuButton1.getButtonCode().equalsIgnoreCase(buttonRequestDto.getButtonCode())){
                    //编码变化，判重
                    checkIsExist(buttonRequestDto.getButtonCode());
                }
                tAuButton.setUpdateUser(loginUserId);
                tAuButton.setUpdateTime(new Date());
                record = tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("按钮保存成功 ");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 查看按钮详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getButtonInfoById(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(id);
        if(tAuButton == null){
            throw new BusinessException("3011111320",getMessage("3011111320"));
        }
        restResponseHeader.setMessage("查看按钮详请成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(tAuButton,restResponseHeader);
    }

    /**
     * 提交按钮
     * @param body
     * @return
     */
    @Override
    public RestResponse commitButton(UpdateButtonStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] buttonIdArray = body.getButtonIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < buttonIdArray.length; i++) {
            TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(Integer.valueOf(buttonIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuButton.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","按钮编码为:"+tAuButton.getButtonCode()+" 的按钮；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                tAuButton.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuButton.setUpdateUser(loginUserId);
                tAuButton.setUpdateTime(new Date());
                tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","按钮编码为:"+tAuButton.getButtonCode()+" 的按钮；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 撤销按钮
     * @param body
     * @return
     */
    @Override
    public RestResponse abolishButton(UpdateButtonStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] buttonIdArray = body.getButtonIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < buttonIdArray.length; i++) {
            TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(Integer.valueOf(buttonIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuButton.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y)&&b){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y)&&!b){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为重新审核
                tAuButton.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuButton.setUpdateUser(loginUserId);
                tAuButton.setUpdateTime(new Date());
                tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 审核按钮
     * @param body
     * @return
     */
    @Override
    public RestResponse auditButton(UpdateButtonStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] buttonIdArray = body.getButtonIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < buttonIdArray.length; i++) {
            TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(Integer.valueOf(buttonIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuButton.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                tAuButton.setDataState(DataStateCode.AUDITED);
                tAuButton.setReviewUser(loginUserId);
                tAuButton.setReviewTime(new Date());
                tAuButton.setUpdateUser(loginUserId);
                tAuButton.setUpdateTime(new Date());
                tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 反审核按钮
     * @param body
     * @return
     */
    @Override
    public RestResponse reverseAuditButton(UpdateButtonStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] buttonIdArray = body.getButtonIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < buttonIdArray.length; i++) {
            TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(Integer.valueOf(buttonIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuButton.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) &&d){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuButton.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                tAuButton.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuButton.setUpdateUser(loginUserId);
                tAuButton.setUpdateTime(new Date());
                tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 删除按钮（资源）
     * @param body
     * @return
     */
    @Override
    public RestResponse deleteButton(UpdateButtonStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] buttonIdArray = body.getButtonIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < buttonIdArray.length; i++) {
            TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(Integer.valueOf(buttonIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuButton.getDataState();
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) || DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(e){
                //符合要求，修改删除标识为"Y"
                tAuButton.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuButton.setUpdateUser(loginUserId);
                tAuButton.setUpdateTime(new Date());
                tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
                map.put("message","按钮编码为 ："+tAuButton.getButtonCode()+" 的按钮；删除成功");
                map.put("code",ConstantUtil.SUCCESS);
                //查角色关联此按钮资源list，同步删除
                TAuRoleResourceExample example = new TAuRoleResourceExample();
                TAuRoleResourceExample.Criteria criteria=example.createCriteria();
                criteria.andResourceItemIdEqualTo(Integer.valueOf(buttonIdArray[i]));
                criteria.andResourceTypeEqualTo(ConstantUtil.BUTTON);
                Integer count = tAuRoleResourceMapper.deleteByExample(example);
                log.info("同步删除该菜单资源关联角色得所有数据；删除数："+count);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","按钮编码为："+tAuButton.getButtonCode()+" 的按钮；删除失败，只有创建和暂存状态的按钮才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 禁用按钮（资源）
     * @param body
     * @return
     */
    @Override
    public RestResponse forbiddenButton(UpdateButtonStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] buttonIdArray = body.getButtonIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < buttonIdArray.length; i++) {
            TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(Integer.valueOf(buttonIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_N.equals(tAuButton.getProhibitState())) {
                tAuButton.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuButton.setProhibitUser(loginUserId);
                tAuButton.setProhibitTime(new Date());
                tAuButton.setUpdateTime(new Date());
                tAuButton.setUpdateUser(loginUserId);
                tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
                map.put("message", "按钮编码为：" + tAuButton.getButtonCode()+ " 的按钮禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
                TAuRoleResourceExample example = new TAuRoleResourceExample();
                TAuRoleResourceExample.Criteria criteria=example.createCriteria();
                criteria.andResourceItemIdEqualTo(Integer.valueOf(buttonIdArray[i]));
                criteria.andResourceTypeEqualTo(ConstantUtil.BUTTON);
                List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
                for (TAuRoleResource tAuRoleResource: roleResourceList) {
                    //设置状态--禁用
                    tAuRoleResource.setState("0");
                    tAuRoleResourceMapper.updateByPrimaryKeySelective(tAuRoleResource);
                    log.info("同步禁用该按钮资源关联角色得所有数据");
                }
            } else {
                map.put("message", "按钮编码为：" + tAuButton.getButtonCode()+ " 的按钮已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 反禁按钮（资源）
     * @param body
     * @return
     */
    @Override
    public RestResponse unForbiddenButton(UpdateButtonStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] buttonIdArray = body.getButtonIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < buttonIdArray.length; i++) {
            TAuButton tAuButton = tAuButtonMapper.selectByPrimaryKey(Integer.valueOf(buttonIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_Y.equals(tAuButton.getProhibitState())) {
                tAuButton.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuButton.setUpdateTime(new Date());
                tAuButton.setUpdateUser(loginUserId);
                tAuButtonMapper.updateByPrimaryKeySelective(tAuButton);
                map.put("message", "按钮编码为：" + tAuButton.getButtonCode()+ " 的按钮反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
                TAuRoleResourceExample example = new TAuRoleResourceExample();
                TAuRoleResourceExample.Criteria criteria=example.createCriteria();
                criteria.andResourceItemIdEqualTo(Integer.valueOf(buttonIdArray[i]));
                criteria.andResourceTypeEqualTo(ConstantUtil.BUTTON);
                List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
                for (TAuRoleResource tAuRoleResource: roleResourceList) {
                    //设置状态--禁用
                    tAuRoleResource.setState("1");
                    tAuRoleResourceMapper.updateByPrimaryKeySelective(tAuRoleResource);
                    log.info("同步反禁用该按钮资源关联角色得所有数据");
                }
            } else {
                map.put("message", "按钮编码为：" + tAuButton.getButtonCode()+ " 的按钮已是反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 查菜单下有效按钮:未删除、未禁用、已审核
     * @param menuId
     * @return
     */
    @Override
    public RestResponse getButtonListByMenuId(Integer menuId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<ButtonResourceResponseDto> responseDtoList = new ArrayList<>();
        TAuButtonExample example = new TAuButtonExample();
        TAuButtonExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria.andMenuIdEqualTo(menuId);
        List<TAuButton> tAuButtonList = tAuButtonMapper.selectByExample(example);
        try {
            for (TAuButton tAuButton: tAuButtonList) {
                ButtonResourceResponseDto buttonResourceResponseDto = new ButtonResourceResponseDto();
                BeanUtils.copyProperties(buttonResourceResponseDto,tAuButton);
                responseDtoList.add(buttonResourceResponseDto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查某菜单下有效按钮成功");
        return RestResponse.resultSuccess(responseDtoList,restResponseHeader);
    }

    /**
     * 获取按钮列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getButtonList(RestRequest<ButtonListRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        ButtonResponse buttonResponse = new ButtonResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(ButtonResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            if(StringUtils.isNotEmpty(restRequest.getBody().getQueryText())){
                restRequest.getBody().setQueryText("%"+restRequest.getBody().getQueryText()+"%");
            }
            List<ButtonResponseDto> buttonResponseDtos = tAuButtonMapper.getButtonList(restRequest.getBody());
            for (ButtonResponseDto buttonResponseDto: buttonResponseDtos) {

                buttonResponseDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),buttonResponseDto.getDataState()));
            }
            buttonResponse.setColumnList(viewColumnList);
            buttonResponse.setPageInfo(new PageInfo<>(buttonResponseDtos));
            buttonResponse.setSum((int) new PageInfo<>(buttonResponseDtos).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询按钮列表成功 ");
        return RestResponse.resultSuccess(buttonResponse,restResponseHeader);
    }

    /**
     * 根据code判重
     * @param buttonCode
     */
    private void checkIsExist(String buttonCode){
        TAuButtonExample example = new TAuButtonExample();
        TAuButtonExample.Criteria criteria = example.createCriteria();
        criteria.andButtonCodeEqualTo(buttonCode);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuButton> tAuButtonList = tAuButtonMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tAuButtonList)){
            throw new BusinessException("3011111319",getMessage("3011111319"));
        }
    }

}
