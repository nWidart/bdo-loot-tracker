package com.bdoloottracker.price.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final GenericFilterBean tokenFilter;
  private final AuthenticationEntryPoint authenticationEntryPoint;
  private final AccessDeniedHandler accessDeniedHandler;

  public SecurityConfig(GenericFilterBean tokenFilter, AuthenticationEntryPoint authenticationEntryPoint,
      AccessDeniedHandler accessDeniedHandler) {
    this.tokenFilter = tokenFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
        http
            // AUTHORIZE
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "**")
                    .permitAll()
                .mvcMatchers("/api/price/**")
                    .hasRole("USER")
                .anyRequest()
                    .authenticated()
            .and()
            // EXCEPTION
            .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .accessDeniedHandler(this.accessDeniedHandler)
            .and()
            // CSRF
            .csrf()
                .disable()
            // AUTHORIZE
            .addFilterBefore(this.tokenFilter, UsernamePasswordAuthenticationFilter.class)
            // SESSION
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;
        // @formatter:on
  }
}
