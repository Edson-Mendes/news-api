package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.service.UserService;

/**
 * Implementação de {@link UserService}.
 */
public class UserServiceImpl implements UserService {

  @Override
  public UserSummaryResponse register(CreateUserRequest userRequest) {
    // TODO: map userRequest into User.
    // TODO: add createdAt and roles.
    // TODO: persistir no banco de dados.
    // TODO: tratar constraint exception (unique email).
    // TODO: map User into UserSummaryResponse.
    return null;
  }

}
