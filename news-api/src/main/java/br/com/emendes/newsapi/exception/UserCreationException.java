package br.com.emendes.newsapi.exception;

/**
 * Exception para ser lançada em caso de problemas na criação de usuário.
 */
public class UserCreationException extends RuntimeException {

  public UserCreationException(String message) {
    super(message);
  }

}
