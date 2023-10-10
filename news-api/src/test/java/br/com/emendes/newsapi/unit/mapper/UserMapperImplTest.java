package br.com.emendes.newsapi.unit.mapper;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.mapper.impl.UserMapperImpl;
import br.com.emendes.newsapi.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static br.com.emendes.newsapi.util.constant.AuthorityConstant.USER_AUTHORITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("Unit tests for UserMapperImpl")
class UserMapperImplTest {

  private UserMapperImpl userMapper;

  @BeforeEach
  void setUp() {
    userMapper = new UserMapperImpl();
  }

  @Test
  @DisplayName("toUser must return User when map successfully")
  void toUser_MustReturnUser_WhenMapSuccessfully() {
    CreateUserRequest userRequest = CreateUserRequest.builder()
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .password("1234567890")
        .confirmPassword("1234567890")
        .build();

    User actualUser = userMapper.toUser(userRequest);

    assertThat(actualUser).isNotNull();
    assertThat(actualUser.getName()).isNotNull().isEqualTo("Lorem Ipsum");
    assertThat(actualUser.getEmail()).isNotNull().isEqualTo("lorem@email.com");
  }

  @Test
  @DisplayName("toUser must throw IllegalArgumentException when userRequest is Null")
  void toUser_MustThrowIllegalArgumentException_WhenUserRequestIsNull() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> userMapper.toUser(null))
        .withMessage("userRequest must not be null");
  }

  @Test
  @DisplayName("toUserSummaryResponse must return UserSummaryResponse when map successfully")
  void toUserSummaryResponse_MustReturnUserSummaryResponse_WhenMapSuccessfully() {
    User user = User.builder()
        .id(100L)
        .name("Lorem Ipsum")
        .email("lorem@email.com")
        .password("1234567890")
        .enabled(true)
        .createdAt(LocalDateTime.parse("2023-10-10T17:00:00"))
        .authorities(Set.of(USER_AUTHORITY))
        .build();

    UserSummaryResponse actualUserSummaryResponse = userMapper.toUserSummaryResponse(user);

    assertThat(actualUserSummaryResponse).isNotNull();
    assertThat(actualUserSummaryResponse.id()).isNotNull().isEqualTo(100L);
    assertThat(actualUserSummaryResponse.name()).isNotNull().isEqualTo("Lorem Ipsum");
    assertThat(actualUserSummaryResponse.email()).isNotNull().isEqualTo("lorem@email.com");
  }

  @Test
  @DisplayName("toUserSummaryResponse must throw IllegalArgumentException when user is Null")
  void toUserSummaryResponse_MustThrowIllegalArgumentException_WhenUserIsNull() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> userMapper.toUserSummaryResponse(null))
        .withMessage("user must not be null");
  }

}
