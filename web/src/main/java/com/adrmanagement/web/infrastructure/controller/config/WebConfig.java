package com.adrmanagement.web.infrastructure.controller.config;

import java.time.Duration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.CssLinkResourceTransformer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebConfig  implements WebMvcConfigurer{

	@Bean
	public FilterRegistrationBean<WebSiteMeshConfig> siteMeshFilter() {
		FilterRegistrationBean<WebSiteMeshConfig> filter = new FilterRegistrationBean<WebSiteMeshConfig>();
		WebSiteMeshConfig siteMeshFilter = new WebSiteMeshConfig();
		filter.setFilter(siteMeshFilter);
		return filter;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/**")
				.addResourceLocations("/")
				.setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));
	}


}
