package com.vosung.authapp.sysdata.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.sysdict.SysDictItemRequestDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.sysdict.DictItemResponseDto;
import com.vosung.auapi.client.dto.responsedto.sysdict.SysDictResponse;
import com.vosung.auapi.client.entity.TSysDictItem;
import com.vosung.auapi.client.entity.TSysDictItemExample;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.FieldMessageCls;
import com.vosung.authapp.sysdata.mapper.TSysDictItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典项业务实现类
 * @Author 彬
 * @Date 2019/4/29
 */
@Slf4j
@Service
public class DictItemServiceImpl extends BaseServiceImpl implements DictItemService {
    @Autowired
    private TSysDictItemMapper tSysDictItemMapper;
    /**
     * 根据字典code 获取对应字典项
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getDictItemListByDictCode(RestRequest<SysDictItemRequestDto> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        SysDictResponse sysDictResponse = new SysDictResponse();
        Integer pageNum = restRequest.getHeader().getPageNum();
        Integer pageSize = restRequest.getHeader().getPageSize();
        try{
            //显示字段
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(DictItemResponseDto.class.getName());

            SysDictItemRequestDto sysDictItemRequestDto = restRequest.getBody();
            TSysDictItemExample example = new TSysDictItemExample();
            TSysDictItemExample.Criteria criteria = example.createCriteria();
            criteria.andDictCodeEqualTo(sysDictItemRequestDto.getDictCode());
            PageMethod.startPage(pageNum,pageSize,"id");
            List<TSysDictItem> tSysDictItems = tSysDictItemMapper.selectByExample(example);
            List<DictItemResponseDto> dictItemResponseDtos = new ArrayList<>();
            for (TSysDictItem tSysDictItem: tSysDictItems) {
                DictItemResponseDto dictItemResponseDto= new DictItemResponseDto();
                BeanUtils.copyProperties(dictItemResponseDto,tSysDictItem);

                dictItemResponseDtos.add(dictItemResponseDto);
            }
            sysDictResponse.setColumnList(viewColumnList);
            sysDictResponse.setPageInfo(new PageInfo<>(dictItemResponseDtos));
            sysDictResponse.setSum((int) new PageInfo<>(dictItemResponseDtos).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询字典项列表成功 ");
        return RestResponse.resultSuccess(sysDictResponse,restResponseHeader);
    }
}
