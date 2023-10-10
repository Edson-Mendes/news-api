package br.com.emendes.newsapi.mapper.impl;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.mapper.UserMapper;
import br.com.emendes.newsapi.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Implementação de {@link UserMapper}.
 */
@Component
public class UserMapperImpl implements UserMapper {

  /**
   * @throws IllegalArgumentException caso userRequest seja null.
   */
  @Override
  public User toUser(CreateUserRequest userRequest) {
    Assert.notNull(userRequest, "userRequest must not be null");

    return User.builder()
        .name(userRequest.name())
        .email(userRequest.email())
        .build();
  }

  /**
   * @throws IllegalArgumentException caso userRequest seja null.
   */
  @Override
  public UserSummaryResponse toUserSummaryResponse(User user) {
    Assert.notNull(user, "user must not be null");

    return UserSummaryResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }

}
