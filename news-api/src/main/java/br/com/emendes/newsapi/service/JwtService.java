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

}
