package org.lecoder.easyflow.common.config;

import org.lecoder.easyflow.EasyflowApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * swagger配置
 * test：测试环境下启用
 * @author: lijile
 * @date: 2021/10/25 14:00
 * @version: 1.0
 */
@Profile({"test"})
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(EasyflowApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("简易流程审批系统")
                .description("简易流程审批系统API文档")
                .version("1.0.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemeList = new ArrayList<>();
        securitySchemeList.add(new ApiKey("Authorization", "Authorization", "header"));
        return securitySchemeList;
    }

    private List<SecurityContext> securityContexts() {
        return Arrays.asList(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        return Arrays.asList(
                new SecurityReference("Authorization",
                        new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")}));
    }

}
