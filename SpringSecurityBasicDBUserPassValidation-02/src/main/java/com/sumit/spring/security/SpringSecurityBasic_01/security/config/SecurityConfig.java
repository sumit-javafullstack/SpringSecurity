package com.sumit.spring.security.SpringSecurityBasic_01.security.config;

import com.sumit.spring.security.SpringSecurityBasic_01.security.service.BookUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Autowired
  private BookUserDetailsService userDetailsService;
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(customizer -> customizer.disable())
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers("login", "register", "/public/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .httpBasic(Customizer.withDefaults()) //for postman api
        .formLogin(Customizer.withDefaults()) // chrome form
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){

    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder());
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder(12);
  }
}
