package br.com.emendes.newsapi.exception;

/**
 * Exception para ser usada em caso de token inválido.
 */
public class InvalidTokenException extends RuntimeException {

  public InvalidTokenException(String message) {
    super(message);
  }

}
