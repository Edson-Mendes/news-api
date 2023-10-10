package br.com.emendes.newsapi.util.constant;

import br.com.emendes.newsapi.model.entity.Authority;

/**
 * Classe para manter as Authority constantes.
 */
public final class AuthorityConstant {

  /**
   * Authority dada a todos usuários básicos da aplicação.
   */
  public static final Authority USER_AUTHORITY = Authority.builder()
      .id(1)
      .name("USER")
      .build();

  private AuthorityConstant() {
  }

}
