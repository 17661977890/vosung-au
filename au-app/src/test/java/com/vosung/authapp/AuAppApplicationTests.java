package com.vosung.authapp;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.entity.TSysDict;
import com.vosung.auapi.client.entity.TSysDictExample;
import com.vosung.auapi.client.entity.TSysDictItem;
import com.vosung.auapi.client.entity.TSysDictItemExample;
import com.vosung.authapp.common.constant.RedisUtil;
import com.vosung.authapp.sysdata.mapper.TSysDictItemMapper;
import com.vosung.authapp.sysdata.mapper.TSysDictMapper;

import com.vosung.boapi.client.BoClient;
import com.vosung.boapi.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuAppApplicationTests {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TSysDictMapper tSysDictMapper;
    @Autowired
    private TSysDictItemMapper tSysDictItemMapper;

    @Autowired
    private BoClient boClient;
//    @Autowired
//    private KZReportService kzReportService;


    /**
     * 将字典表数据写入到redis 中 （要写入那个服务器的redis ，配置文件yml修改redis 的host配置）
     */
    @Test
    public void saveRedisData() {
        TSysDictExample example = new TSysDictExample();
        List<TSysDict> tSysDictList = tSysDictMapper.selectByExample(example);
        for (TSysDict tSysDict: tSysDictList) {
            Map<String,Object> map = new HashMap<>();
            TSysDictItemExample example1 = new TSysDictItemExample();
            TSysDictItemExample.Criteria criteria = example1.createCriteria();
            criteria.andDictCodeEqualTo(tSysDict.getCode());
            List<TSysDictItem> tSysDictItemList = tSysDictItemMapper.selectByExample(example1);
            for(TSysDictItem tSysDictItem:tSysDictItemList){
                map.put(tSysDictItem.getItemCode(),tSysDictItem.getItemName());
            }
            redisUtil.hmset(tSysDict.getCode(),map);
            log.info("redis 新增数据："+redisUtil.hmget(tSysDict.getCode()));
        }
    }
    @Test
    public void testBo(){
        Result result = boClient.auDomainBo(null,true);
        log.info("=================调bo接口查询结果："+ JSON.toJSONString(result));

//        String s = "a+b+1";
//        JexlContext mapContext = new MapContext();
//        mapContext.set("a",5);
//        mapContext.set("b",2);
//        JexlEngine je= new JexlBuilder().create();
//        JexlExpression expression = je.createExpression(s);
//        Object evaluate = expression.evaluate(mapContext);
//
//        System.out.println(evaluate);
    }
//    @Test
//    public void testKsfApp(){
//        KZReportRequestDto kzReportRequestDto = new KZReportRequestDto();
//        kzReportRequestDto.setAreaNo("L02");
//        kzReportRequestDto.setCollectObjectType(1);
//        kzReportRequestDto.setTableDataType(4);
//        kzReportRequestDto.setEquipmentNo("EQ10300_2003");
//        KZAvgCountReportDto kzAvgCountReportDto = kzReportService.getAvgCountReport(kzReportRequestDto);
//        log.info("=================调ksf接口："+JSON.toJSONString(kzAvgCountReportDto));
//    }

}
