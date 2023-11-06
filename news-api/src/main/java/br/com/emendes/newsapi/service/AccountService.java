package br.com.emendes.newsapi.service;

import br.com.emendes.newsapi.exception.InvalidTokenException;

/**
 * Interface com as abstrações responsáveis por ativar uma conta.
 */
public interface AccountService {

  /**
   * Ativa a conta de um usuário.
   *
   * @param token objeto que representa o token de identificação do usuário.
   * @throws InvalidTokenException caso o token informado seja inválido.
   * @throws IllegalArgumentException caso o token seja null.
   */
  void enableAccount(String token);

}
