package br.com.emendes.newsapi.exception;

/**
 * Exception para ser usada em caso de usuário não encontrado.
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message) {
    super(message);
  }

}
