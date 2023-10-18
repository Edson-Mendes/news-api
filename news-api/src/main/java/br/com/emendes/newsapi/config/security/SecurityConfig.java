package br.com.emendes.newsapi.config.security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuração de segurança da aplicação.
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final Filter jwtAuthenticationFilter;
  private static final String[] AUTH_WHITELIST = {"/api/users", "/api/auth"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.httpBasic(HttpBasicConfigurer::disable);

    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated());

    http
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(exceptionHandlingConfig -> {
          exceptionHandlingConfig.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
          exceptionHandlingConfig.accessDeniedHandler(
              (request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value())
          );
        });

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}
