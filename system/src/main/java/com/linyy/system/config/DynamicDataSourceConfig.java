package com.linyy.system.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源 ，将数据源添加到系统中
 */
@Configuration
public class DynamicDataSourceConfig {

    /**
     * 创建 DataSource Bean
     */

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource oneDataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.cluster")
    public DataSource twoDataSource() {
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    /**
     * 如果还有数据源,在这继续添加 DataSource Bean
     */

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource oneDataSource, DataSource twoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceName.MASTER, oneDataSource);
        targetDataSources.put(DataSourceName.CLUSTER, twoDataSource);
        // 还有数据源,在targetDataSources中继续添加
        return new DynamicDataSource(oneDataSource, targetDataSources);
    }

}
