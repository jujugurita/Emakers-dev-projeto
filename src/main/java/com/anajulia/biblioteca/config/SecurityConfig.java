package com.anajulia.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para permitir requisições POST/PUT dos seus testes
            .authorizeHttpRequests(auth -> auth
                // Deixa livre o Swagger-UI e os caminhos da documentação para você conseguir testar
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // Qualquer outro endpoint da aplicação (Livros, Pessoas, etc.) exigirá autenticação
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // Ativa autenticação básica (usuário e senha em caixas de diálogo ou headers)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Obrigatório pelo Spring Security para criptografar as senhas de forma segura
        return new BCryptPasswordEncoder();
    }
}
