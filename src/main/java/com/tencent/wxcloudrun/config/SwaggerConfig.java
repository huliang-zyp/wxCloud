package com.tencent.wxcloudrun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 配置 apiInfo
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.tencent.wxcloudrun.controller")) // // 对哪些 api进行监控，RequestHandlerSelectors.basePackage 基于包扫描
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                // 添加和 Security 相关的配置。
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }
    // 以下方法相对于给 Swagger 添加了一个在 Security 的全局授权，并且以正则的形式设置了授权的请求 url
    private List<SecurityContext> securityContexts(){
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(getContextByPath("/public/.*"));
        return securityContexts;
    }

    // 通过正则表达式来设置哪些路径
    // 通过 Path 获取到对应的 SecurityContext
    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex)) // 按照 String 的 matches 方法进行匹配
                .build();
    }

    /**
     * 配置默认的全局鉴权策略；其中返回的 SecurityReference 中，reference 即为 ApiKey 对象里面的name，保持一致才能开启全局鉴权
     * @return SecurityReference
     */
    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> references = new ArrayList<>();
        // scope 参数：
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        references.add(new SecurityReference("Authorization",authorizationScopes));
        return references;

    }

    /**
     * securitySchemes
     * 安全体方案
     */
    private List<SecurityScheme> securitySchemes(){
        List<SecurityScheme> apiKeys = new ArrayList<>();
        // 设置请求头信息
        apiKeys.add(new ApiKey("Authorization","Authorization","Header"));
        return apiKeys;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("众流API")
                .version("1.0.0")
                .build();
    }
}

