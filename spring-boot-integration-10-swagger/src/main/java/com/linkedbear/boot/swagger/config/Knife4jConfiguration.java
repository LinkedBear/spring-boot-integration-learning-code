package com.linkedbear.boot.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration(proxyBeanMethods = false)
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.linkedbear.boot.swagger")).build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("SpringBoot集成Knife4j")
                // 接口描述
                .description("test swagger document")
                // 联系方式
                .contact(new Contact("LinkedBear", "https://github.com/LinkedBear", ""))
                // 版本信息
                .version("1.0")
                // 构建
                .build();
    }
}
