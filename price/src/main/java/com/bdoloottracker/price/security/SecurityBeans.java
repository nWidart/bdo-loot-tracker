package com.bdoloottracker.price.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
public class SecurityBeans {

  @Value("${security.secret-key:secret}")
  private String secretKey = "secret";

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean("simpleUserDetailsService")
  UserDetailsService simpleUserDetailsService() {
    return new SimpleUserDetailsService(restTemplate());
  }

  @Bean
  GenericFilterBean tokenFilter() {
    return new SimpleTokenFilter(restTemplate(), secretKey);
  }

  @Bean
  AuthenticationEntryPoint authenticationEntryPoint() {
    return new SimpleAuthenticationEntryPoint();
  }

  @Bean
  AccessDeniedHandler accessDeniedHandler() {
    return new SimpleAccessDeniedHandler();
  }

  @Bean
  TokenService tokenService() {
    return new TokenService(secretKey);
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService simpleUserDetailsService,
      PasswordEncoder passwordEncoder) throws Exception {
    auth.eraseCredentials(true)
        .userDetailsService(simpleUserDetailsService)
        .passwordEncoder(passwordEncoder);
  }
}
