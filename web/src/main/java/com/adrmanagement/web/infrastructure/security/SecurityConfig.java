package com.adrmanagement.web.infrastructure.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.adrmanagement.common.member.domain.enums.MemberPermissions;
import com.adrmanagement.web.infrastructure.controller.AdrRecordController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.exceptionHandling((handling) -> handling.accessDeniedHandler(accessDeniedHandler())).csrf(csrf -> {
            csrf.disable();
        })
        .cors(cors -> cors.disable()).authorizeHttpRequests(
				(authorize) -> authorize
				.requestMatchers("/login.html").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/static/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/admin/teamUpdate/**").hasRole(MemberPermissions.ADMIN.name())
				.requestMatchers(HttpMethod.POST, "/admin/teamCreate/**").hasRole(MemberPermissions.ADMIN.name())
				.requestMatchers(HttpMethod.POST, "/admin/memberUpdate/**").hasRole(MemberPermissions.ADMIN.name())
				.requestMatchers(HttpMethod.POST, "/admin/memberUpdate/**").hasRole(MemberPermissions.ADMIN.name())
				.requestMatchers(HttpMethod.POST, "/admin/adrUpdate/**").hasRole(MemberPermissions.ADMIN.name())
				.requestMatchers(HttpMethod.POST, "/admin/adrCreate/**").hasRole(MemberPermissions.ADMIN.name())
				.anyRequest().authenticated()
				).formLogin(form -> form.loginPage("/login.html")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/admin", true).permitAll()
						
						).logout((logout) -> logout.logoutUrl("/logout"))
        ;
		return http.build();
	}
	
	@Autowired
	MemberDetailsService MyUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	

	
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new CustomAccessDeniedHandler();
	}
	
	public class CustomAccessDeniedHandler implements AccessDeniedHandler {

		Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	    @Override
	    public void handle(
	      HttpServletRequest request,
	      HttpServletResponse response, 
	      AccessDeniedException exc) throws IOException, ServletException {
	        
	        Authentication auth 
	          = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null) {
	        	log.warn("User: " + auth.getName() 
	              + " attempted to access the protected URL: "
	              + request.getRequestURI());
	        }

	        response.sendRedirect(request.getContextPath() + "/403Error");
	    }
	}


}


