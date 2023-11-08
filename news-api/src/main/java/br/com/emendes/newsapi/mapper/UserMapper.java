package br.com.emendes.newsapi.mapper;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserDetailsResponse;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.model.entity.User;

/**
 * Interface component com as abstrações responsáveis pelo mapeamento do recurso User em DTOs e vice-versa.
 */
public interface UserMapper {

  /**
   * Mapeia o DTO CreateUserRequest para User.
   *
   * @param userRequest contendo as informações a serem mepeadas para User.
   * @return User com as informações que estavam em userRequest.
   */
  User toUser(CreateUserRequest userRequest);

  /**
   * Mapeia a entidade User para o DTO UserSummaryResponse.
   *
   * @param user entidade contendo as informações do usuário.
   * @return UserSummaryResponse contendo um resumo das informações do usuário.
   */
  UserSummaryResponse toUserSummaryResponse(User user);

  /**
   * Mapeia a entidade User para o DTO UserDetailsResponse.
   *
   * @param user objeto a ser mapeado.
   * @return Objeto UserDetailsResponse contendo as informações que estavam em User.
   */
  UserDetailsResponse toUserDetailsResponse(User user);
}
