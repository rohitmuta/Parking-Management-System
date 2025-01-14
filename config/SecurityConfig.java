package com.example.parking_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for testing
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/api/parking/**").permitAll()  // Correct method for POST
                .requestMatchers(HttpMethod.GET, "/api/parking/**").permitAll()   // Correct method for GET
                .anyRequest().authenticated();  // All other requests require authentication

        return http.build();
    }
}
