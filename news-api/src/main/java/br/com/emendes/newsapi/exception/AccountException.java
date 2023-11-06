package br.com.emendes.newsapi.exception;

/**
 * Exception para ser usada em caso de problemas na parte de conta.
 */
public class AccountException extends RuntimeException {

  public AccountException(String message) {
    super(message);
  }

}
