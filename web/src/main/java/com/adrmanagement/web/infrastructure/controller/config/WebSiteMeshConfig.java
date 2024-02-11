package com.adrmanagement.web.infrastructure.controller.config;

import java.time.Duration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.annotation.WebFilter;


public class WebSiteMeshConfig extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/admin/**", "decorator.jsp")
        .addDecoratorPath("/admin", "decorator.jsp")
        .addDecoratorPath("/404Error", "decorator.jsp")
        .addDecoratorPath("/403Error", "decorator.jsp")
        .addExcludedPath("/static/**")
        .addExcludedPath("/adr_record_publishing.html")
        .addExcludedPath("/login.html")
        .addExcludedPath("/");
    }
	


}