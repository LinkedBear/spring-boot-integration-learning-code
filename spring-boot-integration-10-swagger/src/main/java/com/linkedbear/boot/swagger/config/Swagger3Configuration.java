//package com.linkedbear.boot.swagger.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration(proxyBeanMethods = false)
//public class Swagger3Configuration {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.linkedbear.boot.swagger"))
//                .paths(PathSelectors.any()).build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("SpringBoot自动集成Swagger3")
//                .description("test swagger document")
//                .contact(new Contact("LinkedBear", "https://github.com/LinkedBear", ""))
//                .version("1.0")
//                .build();
//    }
//}
