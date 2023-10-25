package com.platzi.pizza.web.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizeRequest -> {
                    customizeRequest
                            .requestMatchers("/api/customer/**").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN", "CHEF")
                            .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers("/api/order/random").hasAuthority("random_order")
                            .requestMatchers("/api/order/**").hasAnyRole("ADMIN", "CHEF")
                            .anyRequest()
                            .authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
