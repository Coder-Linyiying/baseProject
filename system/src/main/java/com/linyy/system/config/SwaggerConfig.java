package com.linyy.system.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        // 添加请求参数，我们这里把token作为请求头部参数传入后端
        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> parameters = Lists.newArrayList();
        builder.name("token").description("令牌").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        parameters.add(builder.build());
        return new Docket(DocumentationType.SWAGGER_2) //指定api类型为swagger2
                .apiInfo(apiInfo()) //用于指定api文档汇总信息
                .select().apis(RequestHandlerSelectors.basePackage("com.linyy.system.controller")) //指定controller包
                .paths(PathSelectors.any()) //所有controller
                .build().globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口api")       //文档页标题
                .contact(new Contact("linyy", "http://www.linyy.com", "lyy911@foxmail.com")) //联系人信息
                .description("api文档") //详细信息
                .version("1.0.1") //文档版本号
                .termsOfServiceUrl("http://www.linyy.com") //网站地址
                .build();
    }
}
