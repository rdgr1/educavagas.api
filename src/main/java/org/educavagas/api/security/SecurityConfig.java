package org.educavagas.api.security;  // precisa ser sub‐pacote de "org.educavagas.api"

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // desabilita CSRF completamente
                .csrf(csrf -> csrf.disable())

                // stateless, sem sessão
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // libera tudo em /autenticacao/**, protege o resto
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/autenticacao/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Basic Auth para o restante
                .httpBasic();

        return http.build();
    }
}