package br.com.emendes.newsapi.service;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;

/**
 * Interface service com as abstrações responsáveis pela manipulação do recurso User.
 */
public interface UserService {

  /**
   * Cadastrar um usuário no sistema.
   *
   * @param userRequest DTO contendo as informações do usuário a ser cadastrado.
   * @return UserSummaryResponse contendo informações resumidas do usuário cadastrado.
   */
  UserSummaryResponse register(CreateUserRequest userRequest);

}
