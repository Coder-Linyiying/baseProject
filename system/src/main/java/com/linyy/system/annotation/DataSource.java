package com.linyy.system.annotation;


import com.linyy.system.config.DataSourceName;

import java.lang.annotation.*;

/**
 * 多数据源注解
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value() default DataSourceName.MASTER;
}
