package com.cmloveletter.config;

import com.cmloveletter.filter.LogCostFilter;
import com.cmloveletter.filter.LogCostInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogCostInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/loginUser","/login");
    }

    //过滤器
    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogCostFilter());
        registration.addUrlPatterns("/loginUser");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }
}
