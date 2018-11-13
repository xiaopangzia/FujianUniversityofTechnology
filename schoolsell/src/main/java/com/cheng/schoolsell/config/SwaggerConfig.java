package com.cheng.schoolsell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * Swagger2 Restful api配置
 * user: cheng
 * Date: 2018-08-04
 * Time: 上午08：45
 */
@Component
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cheng.schoolsell.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("BinCher","https://blog.antice.top","739237663@qq.com");
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建Controller")
                .description("更多Spring Boot相关文章请关注：https://blog.antice.top")
                .contact(contact)
                .version("1.0")
                .build();
    }

}
