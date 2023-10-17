package br.com.emendes.newsapi.util.component.impl;

import br.com.emendes.newsapi.exception.NoUserAuthenticatedException;
import br.com.emendes.newsapi.model.entity.User;
import br.com.emendes.newsapi.util.component.AuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementação de {@link AuthenticationFacade}.
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

  @Override
  public User getCurrentUser() {
    Optional<Authentication> optionalAuthentication = getAuthentication();

    if (optionalAuthentication.isEmpty()) {
      throw new NoUserAuthenticatedException("No user authenticated");
    }

    return (User) optionalAuthentication.get().getPrincipal();
  }

  /**
   * Retorna a autenticação atual.
   *
   * @return {@code Optional<Authentication>} podendo conter um {@link Authentication}.
   */
  private Optional<Authentication> getAuthentication() {
    return Optional.of(SecurityContextHolder.getContext().getAuthentication());
  }

}
