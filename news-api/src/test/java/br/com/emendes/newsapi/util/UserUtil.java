package br.com.emendes.newsapi.util;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.model.entity.User;

import java.time.LocalDateTime;

import static br.com.emendes.newsapi.util.AuthorityUtil.userAuthorities;

/**
 * Classe com objetos relacionados a User para auxiliar em testes automatizados.
 */
public final class UserUtil {

  private UserUtil() {
  }

  /**
   * Retorna um User com todos os campos name e email.
   */
  public static User user() {
    return User.builder()
        .id(100L)
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .password("1234567890encoded")
        .createdAt(LocalDateTime.parse("2023-10-12T10:00:00"))
        .enabled(true)
        .authorities(userAuthorities())
        .build();
  }

  /**
   * Retorna um User com os campos name e email.
   */
  public static User mappedUser() {
    return User.builder()
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .build();
  }

  /**
   * Retorna um CreateUserRequest com todos os campos válidos.
   */
  public static CreateUserRequest userRequest() {
    return CreateUserRequest.builder()
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .password("1234567890")
        .confirmPassword("1234567890")
        .build();
  }

  /**
   * Retorna um UserSummaryResponse com todos os campos.
   */
  public static UserSummaryResponse userSummaryResponse() {
    return UserSummaryResponse.builder()
        .id(100L)
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .build();
  }

}
