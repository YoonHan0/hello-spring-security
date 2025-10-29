package com.example.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {

        // "/user" 경로에 대해서만 맵핑이 됨 (필수)
        http
                .securityMatchers((auth) -> auth.requestMatchers("/user"));

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user").permitAll());

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {

        // "/admin" 경로에 대해서만 맵핑이 됨 (필수)
        http
                .securityMatchers((auth) -> auth.requestMatchers("/admin"));

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin").permitAll());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return web -> web.ignoring().requestMatchers("/img/**");
    }
}
