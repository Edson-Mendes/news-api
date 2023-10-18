package br.com.emendes.newsapi.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface service com as abstrações para manipulação de JWT.
 */
public interface JwtService {

  /**
   * Gera um JWT de acordo com os dados em userDetails.
   */
  String generateToken(UserDetails userDetails);

  /**
   * Verifica se o token informado é válido.
   *
   * @param token objeto a ser válidado.
   * @return true caso o token seja válido, false caso contrário.
   */
  boolean isTokenValid(String token);

  /**
   * Extrai o subject do token.
   *
   * @param token objeto o qual o subject será estraído.
   * @return o subject.
   * @throws io.jsonwebtoken.JwtException caso o token sejá inválido.
   */
  String extractSubject(String token);

}
