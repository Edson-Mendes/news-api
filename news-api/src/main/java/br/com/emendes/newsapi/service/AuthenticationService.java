package br.com.emendes.newsapi.service;

import br.com.emendes.newsapi.dto.request.AuthenticationRequest;
import br.com.emendes.newsapi.dto.response.AuthenticationResponse;

/**
 * Interface service com as abstrações responsáveis pela autentição de usuário.
 */
public interface AuthenticationService {

  /**
   * Autentica um usuário atrávés de username e password.
   *
   * @param authenticationRequest contendo username e password do usuário.
   * @return AuthenticationResponse contendo o JWT.
   */
  AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
