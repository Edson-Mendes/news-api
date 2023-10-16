package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.service.JwtService;
import br.com.emendes.newsapi.util.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * Implementação de {@link JwtService}.
 */
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

  /**
   * Gera uma Key com base na secret.
   */
  private Key getKey() {
    byte[] secretBytes = jwtProperties.secret().getBytes();
    return Keys.hmacShaKeyFor(secretBytes);
  }

}
