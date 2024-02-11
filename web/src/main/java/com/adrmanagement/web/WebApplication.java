package com.adrmanagement.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
//	@Bean
//	public TomcatServletWebServerFactory tomcatFactory() {
//		return new TomcatServletWebServerFactory() {
//			@Override
//			protected void postProcessContext(Context context) {
//				((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
//			}
//		};
//	}

}
