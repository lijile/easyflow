package org.lecoder.easyflow.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * swagger配置
 * test：测试环境下启用
 * @author: lijile
 * @date: 2021/10/25 14:00
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
@Profile({"test"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.lecoder.easyflow"))
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

    private List<ApiKey> securitySchemes() {
        return Arrays.asList(new ApiKey("Authorization", "Authorization", "header"));
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
