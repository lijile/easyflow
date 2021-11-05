## swagger2 + springboot使用文档

### 引入依赖
```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

### 引入配置类
```
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
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("简易流程系统")
                .description("简易流程系统API文档")
                .version("1.0.0")
                .build();
    }

}
```

### 如果需要在测试时，带上登录会话凭证，配置类改为
```
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
                .title("简易流程系统")
                .description("简易流程系统API文档")
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
```
另外需要放开对应的静态页面，和登录地址一样，不验证用户登录
```
- /swagger-ui.html # swagger免登陆地址
- /v2/**
- /swagger-resources/**
- /webjars/**
```