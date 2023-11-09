package br.com.emendes.newsapi.exception;

/**
 * Exception para ser usada em caso de alguma ação não autorizada.
 */
public class UnauthorizedUserException extends RuntimeException {

  public UnauthorizedUserException(String message) {
    super(message);
  }

}
