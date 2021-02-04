package com.linyy.kitty.admin;

import com.linyy.kitty.admin.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;


@Import({DynamicDataSourceConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.linyy.kitty.**.mapper")
public class KittyAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KittyAdminApplication.class, args);
    }

}
