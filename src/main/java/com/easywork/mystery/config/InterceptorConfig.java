package com.easywork.mystery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.easywork.mystery.interceptor.MyInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/**/*login*/**",
				"/**/*register*/**", "/**/*success*/**", "/**/*error*/**");
		super.addInterceptors(registry);
	}
}
