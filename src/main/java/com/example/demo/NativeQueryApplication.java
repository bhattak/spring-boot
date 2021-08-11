package com.example.demo;

import com.example.demo.filter.LogFilter;
import com.example.demo.filter.MethodFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NativeQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NativeQueryApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<LogFilter> loggingFilter(){
		FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new LogFilter());
		registrationBean.addUrlPatterns("/filter/*");
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean<MethodFilter> loggingFilter1(){
		FilterRegistrationBean<MethodFilter> registrationBean1 = new FilterRegistrationBean<>();
		registrationBean1.setFilter(new MethodFilter());
		registrationBean1.addUrlPatterns("/filter/*");
		return registrationBean1;
	}
}
