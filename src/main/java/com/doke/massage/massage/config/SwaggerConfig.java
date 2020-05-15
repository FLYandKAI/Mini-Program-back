package com.doke.massage.massage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("树凯一直不帅")
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.doke.massage.massage.controller"))
                    .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("树凯","https://www.mofashiteam.com","1006229732@qq.com");
        return new ApiInfo(
                "树凯不帅",
                "敌军将在五秒后到达战场",
                "1.0",
                "https://www.mofashiteam.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org",
                new ArrayList()
        );
    }
}
