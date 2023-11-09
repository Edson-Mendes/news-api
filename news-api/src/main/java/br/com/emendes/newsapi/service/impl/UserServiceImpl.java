package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserDetailsResponse;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.exception.UserCreationException;
import br.com.emendes.newsapi.exception.UserNotFoundException;
import br.com.emendes.newsapi.mapper.UserMapper;
import br.com.emendes.newsapi.model.entity.User;
import br.com.emendes.newsapi.repository.UserRepository;
import br.com.emendes.newsapi.service.NotificationSenderService;
import br.com.emendes.newsapi.service.UserService;
import br.com.emendes.newsapi.util.component.NotificationGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
  private final NotificationSenderService notificationSenderService;
  private final NotificationGenerator notificationGenerator;

  @Override
  public UserSummaryResponse register(CreateUserRequest userRequest) {
    if (!isPasswordsMatch(userRequest.password(), userRequest.confirmPassword())) {
      throw new UserCreationException("password and confirmPassword do not match");
    }
    User user = userMapper.toUser(userRequest);
    initializeData(user);

    try {
      userRepository.save(user);

      log.info("User saved successfully with id : {}", user.getId());
      notificationSenderService.send(notificationGenerator.generateConfirmationNotification(user));

      return userMapper.toUserSummaryResponse(user);
    } catch (DataIntegrityViolationException exception) {
      log.info("Data Integrity Violation, message : {}", exception.getMessage());
      throw new UserCreationException(String.format("E-mail {%s} is already in use", user.getEmail()));
    }
  }

  @Override
  public Page<UserSummaryResponse> fetch(Pageable pageable) {
    log.info("Search for page {} with size {} of users", pageable.getPageNumber(), pageable.getPageSize());

    return userRepository.findBy(pageable);
  }

  @Override
  public UserDetailsResponse findById(Long id) {
    Assert.notNull(id, "id must not be null");
    log.info("Search for user with id {}", id);

    return userRepository.findProjectedById(id)
        .orElseThrow(() -> new UserNotFoundException("user not found for id %d".formatted(id)));
  }

  /**
   * Adiciona dados do usuário (createdAt, enabled, authorities, password).
   *
   * @param user objeto que terá os dados inicializados.
   */
  private void initializeData(User user) {
    user.setCreatedAt(LocalDateTime.now());
    user.setEnabled(false);
    user.addAuthority(USER_AUTHORITY);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
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
