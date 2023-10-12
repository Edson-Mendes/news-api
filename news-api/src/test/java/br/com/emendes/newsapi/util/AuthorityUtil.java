package br.com.emendes.newsapi.util;

import br.com.emendes.newsapi.model.entity.Authority;

import java.util.Collection;
import java.util.HashSet;

/**
 * Classe com objetos relacionados a Authority para auxiliar em testes automatizados.
 */
public final class AuthorityUtil {

  private AuthorityUtil() {
  }

  /**
   * Retorna uma collections de todas as Authorities de um User.
   */
  public static Collection<Authority> userAuthorities() {
    Authority userAuthority = Authority.builder()
        .id(1)
        .name("USER")
        .build();

    Collection<Authority> authorities = new HashSet<>();
    authorities.add(userAuthority);

    return authorities;
  }

}
