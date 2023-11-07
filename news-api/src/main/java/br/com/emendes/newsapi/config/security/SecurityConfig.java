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

import static br.com.emendes.newsapi.util.constant.AuthorityConstant.ADMIN_AUTHORITY;

/**
 * Configuração de segurança da aplicação.
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

  private final Filter jwtAuthenticationFilter;
  private static final String[] AUTH_WHITELIST = {"/api/users", "/api/auth"};
  private static final String[] PATCH_WHITELIST = {"/api/users/*"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.httpBasic(HttpBasicConfigurer::disable);

    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, AUTH_WHITELIST).permitAll()
            .requestMatchers(HttpMethod.PATCH, PATCH_WHITELIST).permitAll()
            .requestMatchers(HttpMethod.GET, "/api/users").hasAuthority(ADMIN_AUTHORITY.getAuthority())
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
