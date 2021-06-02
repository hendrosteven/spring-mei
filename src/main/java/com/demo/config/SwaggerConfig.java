package com.demo.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
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

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    private List<Parameter> globalParameterList(){
        Parameter param = new ParameterBuilder()
            .name("SESSIONID")
            .modelRef(new ModelRef("string"))
            .required(false)
            .parameterType("header")
            .description("User Session ID").build();
            return Collections.singletonList(param);
    }

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
            .globalOperationParameters(globalParameterList())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }
    
    @Bean
    public ApiInfo getApiInfo(){
        return new ApiInfo(
            "Product API Demo",
            "Sample Rest API with SpringBoot Framework",
            "1.0",
            "http://www.domain.com",
            new Contact("Hendro Steven", "http://github.com/hendrosteven","hendro.steven@gmail.com"),
            "Apache 2.0",
            "http://www.apache.org",
            Collections.emptyList()
        );
    }
}
