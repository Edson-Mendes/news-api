package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.exception.AccountException;
import br.com.emendes.newsapi.exception.InvalidTokenException;
import br.com.emendes.newsapi.model.entity.User;
import br.com.emendes.newsapi.repository.UserRepository;
import br.com.emendes.newsapi.service.AccountService;
import br.com.emendes.newsapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Implementação de {@link AccountService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

  private final JwtService jwtService;
  private final UserRepository userRepository;
  private static final String ACCOUNT_EXCEPTION_MESSAGE_TEMPLATE = "User with email: %s not found";

  @Override
  public void enableAccount(long id, String token) {
    Assert.notNull(token, "token must not be null");
    if (!jwtService.isTokenValid(token)) {
      throw new InvalidTokenException("invalid token");
    }

    String email = jwtService.extractSubject(token);
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty()) {
      log.info("user not found for email: {}", email);
      throw new AccountException(ACCOUNT_EXCEPTION_MESSAGE_TEMPLATE.formatted(email));
    }
    User user = optionalUser.get();
    user.setEnabled(true);

    userRepository.save(user);
    log.info("user with email: {} has been activated", email);
  }

}
