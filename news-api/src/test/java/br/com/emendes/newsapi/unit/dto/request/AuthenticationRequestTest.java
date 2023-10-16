package br.com.emendes.newsapi.unit.dto.request;

import br.com.emendes.newsapi.dto.request.AuthenticationRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Unit tests for AuthenticationRequest")
class AuthenticationRequestTest {

  private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private Validator validator;

  @BeforeAll
  void setUp() {
    validator = validatorFactory.getValidator();
  }

  @AfterAll
  void afterAll() {
    validatorFactory.close();
  }

  @Nested
  @DisplayName("Tests for username validation")
  class UsernameValidation {

    public static final String PROPERTY_USERNAME = "username";

    @ParameterizedTest
    @ValueSource(strings = {"lorem@email.com", "lorem", "L"})
    @DisplayName("username validation must not return violations when username is valid")
    void usernameValidation_MustNotReturnViolations_WhenUsernameIsValid(String validUsername) {
      AuthenticationRequest authenticationRequest = new AuthenticationRequest(validUsername, null);

      Set<ConstraintViolation<AuthenticationRequest>> actualViolations = validator
          .validateProperty(authenticationRequest, PROPERTY_USERNAME);

      assertThat(actualViolations).isNotNull().isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("username validation must return violations when username is blank")
    void usernameValidation_MustReturnViolations_WhenUsernameIsBlank(String blankUsername) {
      AuthenticationRequest authenticationRequest = new AuthenticationRequest(blankUsername, null);

      Set<ConstraintViolation<AuthenticationRequest>> actualViolations = validator
          .validateProperty(authenticationRequest, PROPERTY_USERNAME);
      List<String> actualViolationMessages = getViolationMessages(actualViolations);

      assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty().contains("username must not be blank");
    }

  }

  @Nested
  @DisplayName("Tests for password validation")
  class PasswordValidation {

    public static final String PROPERTY_PASSWORD = "password";

    @ParameterizedTest
    @ValueSource(strings = {"1234567890", "a", "abceaohsdoahphahfashfdash"})
    @DisplayName("password validation must not return violations when password is valid")
    void passwordValidation_MustNotReturnViolations_WhenPasswordIsValid(String validPassword) {
      AuthenticationRequest authenticationRequest = new AuthenticationRequest(null, validPassword);

      Set<ConstraintViolation<AuthenticationRequest>> actualViolations = validator
          .validateProperty(authenticationRequest, PROPERTY_PASSWORD);

      assertThat(actualViolations).isNotNull().isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("password validation must return violations when password is blank")
    void passwordValidation_MustReturnViolations_WhenPasswordIsBlank(String blankPassword) {
      AuthenticationRequest authenticationRequest = new AuthenticationRequest(null, blankPassword);

      Set<ConstraintViolation<AuthenticationRequest>> actualViolations = validator
          .validateProperty(authenticationRequest, PROPERTY_PASSWORD);
      List<String> actualViolationMessages = getViolationMessages(actualViolations);

      assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty().contains("password must not be blank");
    }

  }

  private static List<String> getViolationMessages(Set<ConstraintViolation<AuthenticationRequest>> actualViolations) {
    return actualViolations.stream().map(ConstraintViolation::getMessage).toList();
  }

}