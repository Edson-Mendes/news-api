package br.com.emendes.newsapi.unit.service;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.exception.UserCreationException;
import br.com.emendes.newsapi.mapper.UserMapper;
import br.com.emendes.newsapi.repository.UserRepository;
import br.com.emendes.newsapi.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.emendes.newsapi.util.UserUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests for UserServiceImpl")
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;
  @Mock
  private UserMapper userMapperMock;
  @Mock
  private PasswordEncoder passwordEncoderMock;
  @Mock
  private UserRepository userRepositoryMock;

  @Nested
  @DisplayName("Tests for register method")
  class RegisterMethod {

    @Test
    @DisplayName("register must return UserSummaryResponse when register successfully")
    void register_MustReturnUserSummaryResponse_WhenRegisterSuccessfully() {
      when(userMapperMock.toUser(any())).thenReturn(mappedUser());
      when(passwordEncoderMock.encode(any())).thenReturn("1234567890encoded");
      when(userRepositoryMock.save(any())).thenReturn(user());
      when(userMapperMock.toUserSummaryResponse(any())).thenReturn(userSummaryResponse());

      UserSummaryResponse actualUserSummaryResponse = userService.register(userRequest());

      verify(userMapperMock).toUser(any());
      verify(passwordEncoderMock).encode(any());
      verify(userRepositoryMock).save(any());
      verify(userMapperMock).toUserSummaryResponse(any());

      assertThat(actualUserSummaryResponse).isNotNull();
      assertThat(actualUserSummaryResponse.id()).isNotNull();
      assertThat(actualUserSummaryResponse.name()).isNotNull().isEqualTo("Lorem Ipsum");
      assertThat(actualUserSummaryResponse.email()).isNotNull().isEqualTo("lorem@email.com");
    }

    @Test
    @DisplayName("register must throw UserCreationException when password and confirmPassword does not match")
    void register_MustThrowUserCreationException_WhenPasswordAndConfirmPasswordDoesNotMatch() {
      CreateUserRequest userRequest = CreateUserRequest.builder()
          .name("Lorem Ipsum")
          .email("lorem@email.com")
          .password("1234567890__")
          .confirmPassword("1234567890")
          .build();

      Assertions.assertThatExceptionOfType(UserCreationException.class)
          .isThrownBy(() -> userService.register(userRequest))
          .withMessage("password and confirmPassword do not match");
    }

    @Test
    @DisplayName("register must throw UserCreationException when email already in use")
    void register_MustThrowUserCreationException_WhenEmailAlreadyInUse() {
      when(userMapperMock.toUser(any())).thenReturn(mappedUser());
      when(passwordEncoderMock.encode(any())).thenReturn("1234567890encoded");
      when(userRepositoryMock.save(any()))
          .thenThrow(new DataIntegrityViolationException("Email violates unique constraint"));

      CreateUserRequest userRequest = userRequest();

      Assertions.assertThatExceptionOfType(UserCreationException.class)
          .isThrownBy(() -> userService.register(userRequest))
          .withMessage("E-mail {lorem@email.com} is already in use");

      verify(userMapperMock).toUser(any());
      verify(passwordEncoderMock).encode(any());
      verify(userRepositoryMock).save(any());
    }

  }

}