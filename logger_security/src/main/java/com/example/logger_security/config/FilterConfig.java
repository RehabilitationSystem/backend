package com.example.logger_security.config;


import com.example.logger_security.service.MdcTraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MdcTraceFilter> registrationTrackFilter() {

        FilterRegistrationBean<MdcTraceFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MdcTraceFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("trackFilter");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
