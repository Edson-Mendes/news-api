package br.com.emendes.newsapi.config.security.filter;

import br.com.emendes.newsapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * Filtro responsável por autenticar cada requisição via JWT.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    Optional<String> optionalAuthHeaderValue = extractAuthorizationHeader(request);

    if (optionalAuthHeaderValue.isEmpty()) {
      log.info("empty header authorization");
      filterChain.doFilter(request, response);
      return;
    }

    String authHeaderValue = optionalAuthHeaderValue.get();
    String token = getToken(authHeaderValue);

    if (jwtService.isTokenValid(token)) {
      String username = jwtService.extractSubject(token);
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authenticationToken = generateAuthenticationToken(userDetails);

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      filterChain.doFilter(request, response);
      return;
    }

    log.info("invalid jwt: {}", token);
    filterChain.doFilter(request, response);
  }

  /**
   * Gera UsernamePasswordAuthenticationToken.
   */
  private static UsernamePasswordAuthenticationToken generateAuthenticationToken(UserDetails userDetails) {
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }

  /**
   * Retorna um {@code Optinal<String>} do header authorization da requisição.
   */
  private Optional<String> extractAuthorizationHeader(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader("Authorization"));
  }

  /**
   * Extrai o token de uma string no formado 'Bearer token_value'.
   */
  private String getToken(String authHeaderValue) {
    return authHeaderValue.substring(7);
  }

}
