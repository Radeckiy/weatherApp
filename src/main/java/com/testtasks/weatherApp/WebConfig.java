package com.testtasks.weatherApp;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public ServletRegistrationBean<GetWeatherServlet> getWeatherServlet() {
        ServletRegistrationBean<GetWeatherServlet> servRegBean = new ServletRegistrationBean<>();
        servRegBean.setServlet(new GetWeatherServlet());
        servRegBean.addUrlMappings("/getWeatherServlet");
        servRegBean.setLoadOnStartup(1);
        return servRegBean;
    }
}
