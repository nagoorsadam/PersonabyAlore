package com.plash.configurator.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Utsav on 06-May-17.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
    public void addInterceptors(InterceptorRegistry registry) {
        /** pattern(/**) is for match entire directory tree where as pattern(/*) is level specific*/
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**");
    }
}
