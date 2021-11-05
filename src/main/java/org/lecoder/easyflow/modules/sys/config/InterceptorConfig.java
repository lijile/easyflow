package org.lecoder.easyflow.modules.sys.config;

import lombok.Setter;
import org.lecoder.easyflow.modules.sys.interceptor.LoginInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 拦截器配置
 * @author: lijile
 * @date: 2021/10/27 10:58
 * @version: 1.0
 */
@Setter
@Configuration
@ConfigurationProperties(prefix = "org.lecoder.easyflow.interceptor")
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor).excludePathPatterns(excludedUrls);
    }

    private List<String> excludedUrls;

}
