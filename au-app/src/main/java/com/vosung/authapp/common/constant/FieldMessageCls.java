package com.vosung.authapp.common.constant;

import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.restparam.FiledMessage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 彬
 * @Date 2019/5/24
 */
public class FieldMessageCls {
    /**
     * 反射获取字段code name
     * @param obj
     * @return
     */
    public static List<ViewColumn> getViewColumn(String obj) throws ClassNotFoundException {
        List<ViewColumn> viewColumnList = new ArrayList<>();
        //获取入参类对象
        Class clazz = Class.forName(obj);

        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            //查看是否有该注解并获取注解name值
            boolean fieldHasAnno = field.isAnnotationPresent(FiledMessage.class);
            if(fieldHasAnno){
                ViewColumn viewColumn = new ViewColumn();
                String code = field.getName();
                FiledMessage filedMessage = field.getAnnotation(FiledMessage.class);
                String name = filedMessage.name();
                viewColumn.setColumnCode(code);
                viewColumn.setColumnName(name);
                viewColumnList.add(viewColumn);
            }
        }
        return viewColumnList;
    }
}
