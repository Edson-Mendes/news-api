package br.com.emendes.newsapi.exception;

/**
 * Exception a ser usada quando não há usuário autenticado na requisição atual.
 */
public class NoUserAuthenticatedException extends RuntimeException {

  public NoUserAuthenticatedException(String message) {
    super(message);
  }

}
