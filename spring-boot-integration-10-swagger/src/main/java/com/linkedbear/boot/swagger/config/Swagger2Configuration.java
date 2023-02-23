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
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration(proxyBeanMethods = false)
//@EnableSwagger2
//public class Swagger2Configuration {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                // 指定构建api文档的详细信息的方法：apiInfo()
//                .apiInfo(apiInfo())
//                .select()
//                // 指定要生成api接口的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.linkedbear.boot.swagger"))
//                //使用了 @ApiOperation 注解的方法生成api接口文档
//                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                //可以根据url路径设置哪些请求加入文档，忽略哪些请求
//                .build();
//    }
//
//    /**
//     * 设置api文档的详细信息
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                // 标题
//                .title("SpringBoot手动集成Swagger2")
//                // 接口描述
//                .description("test swagger document")
//                // 联系方式
//                .contact(new Contact("LinkedBear", "https://github.com/LinkedBear", ""))
//                // 版本信息
//                .version("1.0")
//                // 构建
//                .build();
//    }
//}
