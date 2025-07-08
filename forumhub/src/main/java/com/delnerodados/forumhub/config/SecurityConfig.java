package com.delnerodados.forumhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF (para dev/local)
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Libera o uso de frames (H2 Console, modo atual!)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Libera acesso ao H2 Console
                        .anyRequest().permitAll() // Libera o resto da API (ajuste depois em produção)
                );
        return http.build();
    }
}
