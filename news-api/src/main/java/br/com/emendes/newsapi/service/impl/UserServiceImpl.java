package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.mapper.UserMapper;
import br.com.emendes.newsapi.model.entity.User;
import br.com.emendes.newsapi.repository.UserRepository;
import br.com.emendes.newsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.emendes.newsapi.util.constant.AuthorityConstant.USER_AUTHORITY;

/**
 * Implementação de {@link UserService}.
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Override
  public UserSummaryResponse register(CreateUserRequest userRequest) {
    User user = userMapper.toUser(userRequest);

    user.setCreatedAt(LocalDateTime.now());
    user.setEnabled(true);
    user.addAuthority(USER_AUTHORITY);

    // TODO: tratar constraint exception (unique email).
    userRepository.save(user);
    // TODO: Enviar email de registro de conta para o email informado.

    return userMapper.toUserSummaryResponse(user);
  }

}
