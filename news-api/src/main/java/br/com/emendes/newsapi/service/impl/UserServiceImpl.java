package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.exception.UserCreationException;
import br.com.emendes.newsapi.mapper.UserMapper;
import br.com.emendes.newsapi.model.entity.User;
import br.com.emendes.newsapi.repository.UserRepository;
import br.com.emendes.newsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static br.com.emendes.newsapi.util.constant.AuthorityConstant.USER_AUTHORITY;

/**
 * Implementação de {@link UserService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserSummaryResponse register(CreateUserRequest userRequest) {
    if (!isPasswordsMatch(userRequest.password(), userRequest.confirmPassword())) {
      throw new UserCreationException("password and confirmPassword do not match");
    }
    User user = userMapper.toUser(userRequest);

    user.setCreatedAt(LocalDateTime.now());
    user.setEnabled(true);
    user.addAuthority(USER_AUTHORITY);
    user.setPassword(passwordEncoder.encode(userRequest.password()));


    try {
      userRepository.save(user);
      log.info("User saved successfully with id : {}", user.getId());

      // TODO: Enviar email de registro de conta para o email informado.
      return userMapper.toUserSummaryResponse(user);
    } catch (DataIntegrityViolationException exception) {
      log.info("Data Integrity Violation, message : {}", exception.getMessage());
      throw new UserCreationException(String.format("E-mail {%s} is already in use", user.getEmail()));
    }
  }

  /**
   * Verifica se a String first é igual a String second.
   *
   * @return Retorna true caso fisrt seja igual a second, false caso contrário.
   */
  private static boolean isPasswordsMatch(String first, String second) {
    return Objects.equals(first, second);
  }

}
