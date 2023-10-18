package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.service.JwtService;
import br.com.emendes.newsapi.util.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Implementação de {@link JwtService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

  private final JwtProperties jwtProperties;

  @Override
  public String generateToken(UserDetails userDetails) {
    long currentTimeMillis = System.currentTimeMillis();
    Date now = new Date(currentTimeMillis);
    Date expiration = new Date(currentTimeMillis + jwtProperties.expiration());

    return Jwts.builder()
        .setIssuer("News API")
        .setSubject(userDetails.getUsername())
        .setIssuedAt(now)
        .setExpiration(expiration)
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public boolean isTokenValid(String token) {
    try {
      extractAllClaims(token);
      return true;
    } catch (JwtException exception) {
      log.info("invalid token, exception message: {}", exception.getMessage());
      return false;
    }
  }

  @Override
  public String extractSubject(String token) {
    return extractAllClaims(token).getSubject();
  }

  /**
   * Extrai todas as Claims do token.
   *
   * @throws JwtException caso o token sejá inválido (null, em branco, expirado, mal formado).
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * Gera uma Key com base na secret.
   */
  private Key getKey() {
    byte[] secretBytes = jwtProperties.secret().getBytes();
    return Keys.hmacShaKeyFor(secretBytes);
  }

}
