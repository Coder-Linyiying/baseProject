package com.linyy.kitty.admin.annotation;


import com.linyy.kitty.admin.config.DataSourceName;

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
