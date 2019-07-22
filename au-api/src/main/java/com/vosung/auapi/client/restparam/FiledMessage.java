package com.vosung.auapi.client.restparam;

import java.lang.annotation.*;

/**
 * 此注解标识这是个查询方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FiledMessage {
    String name();
}
