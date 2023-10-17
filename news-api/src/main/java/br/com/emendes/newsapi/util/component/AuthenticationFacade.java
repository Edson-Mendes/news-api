package br.com.emendes.newsapi.util.component;

import br.com.emendes.newsapi.model.entity.User;

/**
 * Interface component que contém as abstrações que interagem com o Current User.
 */
public interface AuthenticationFacade {

  /**
   * Retorna o usuário atual.
   *
   * @return User atual.
   */
  User getCurrentUser();

}
