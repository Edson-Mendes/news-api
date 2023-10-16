package br.com.emendes.newsapi.unit.dto.request;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.util.ViolationMessageUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Unit tests for CreateUserRequest")
class CreateUserRequestTest {

  private Validator validator;
  private final ViolationMessageUtil<CreateUserRequest> violationMessageUtil = new ViolationMessageUtil<>();

  @BeforeAll
  void setUp() {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
    validatorFactory.close();
  }

  @Nested
  @DisplayName("Tests for name validation")
  class NameValidation {

    public static final String PROPERTY_NAME = "name";

    @ParameterizedTest
    @ValueSource(strings = {"Jo", "Lorem Ipsum", "Name with 50 characters long Name with 50 characte"})
    @DisplayName("name validation must not return violations when name is valid")
    void nameValidation_MustNotReturnViolations_WhenNameIsValid(String validName) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .name(validName)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_NAME);

      Assertions.assertThat(actualViolations).isNotNull().isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("name validation must return violations when name is blank")
    void nameValidation_MustReturnViolations_WhenNameIsBlank(String blankName) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .name(blankName)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_NAME);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty().contains("name must not be blank");
    }

    @Test
    @DisplayName("name validation must return violations when name length is less than 2")
    void nameValidation_MustReturnViolations_WhenNameLengthIsLessThan2() {
      String nameWith1Character = "X";

      Assumptions.assumeThat(nameWith1Character).hasSizeLessThan(2);

      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .name(nameWith1Character)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_NAME);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty()
          .contains("name must contain between 2 and 50 characters long");
    }

    @Test
    @DisplayName("name validation must return violations when name length is greater than 50")
    void nameValidation_MustReturnViolations_WhenNameLengthIsGreaterThan50() {
      String nameWith51Character = "Lorem Ipsum dolor sit amet Lorem Ipsum dolor sit amet";

      Assumptions.assumeThat(nameWith51Character).hasSizeGreaterThan(50);

      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .name(nameWith51Character)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_NAME);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty()
          .contains("name must contain between 2 and 50 characters long");
    }

  }

  @Nested
  @DisplayName("Tests for email validation")
  class EmailValidation {

    public static final String PROPERTY_EMAIL = "email";

    @ParameterizedTest
    @ValueSource(strings = {"e@email", "lorem@email", "verylongemailverylongemailverylongemail@email.com"})
    @DisplayName("email validation must not return violations when email is valid")
    void emailValidation_MustNotReturnViolations_WhenEmailIsValid(String validEmail) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email(validEmail)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_EMAIL);

      Assertions.assertThat(actualViolations).isNotNull().isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("email validation must return violations when email is blank")
    void emailValidation_MustReturnViolations_WhenEmailIsBlank(String blankEmail) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email(blankEmail)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_EMAIL);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty().contains("email must not be blank");
    }

    @Test
    @DisplayName("email validation must return violations when email length is greater than 320")
    void emailValidation_MustReturnViolations_WhenEmailLengthIsGreaterThan320() {
      String emailWith322Character = "lo.rem.ip.sum.do.lor.si.t.am.e.tlo.rem.ip.sum.do.lor.si.t.am.e.t" +
          "@emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail" +
          "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail" +
          "emailemailemailemailemailemailemailemailemailemail.com.br";

      Assumptions.assumeThat(emailWith322Character).hasSizeGreaterThan(320);

      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email(emailWith322Character)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_EMAIL);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty()
          .contains("email must contain max 320 characters long");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "lorememail.com",
        "lorem@",
        "@email.com",
        "localpartwithmorethan64characterslocalpartwithmorethan64character@email.com",
        "lorem@" + "domainpartwithmorethan255charactersdomainpartwithmorethan255characters" +
            "domainpartwithmorethan255charactersdomainpartwithmorethan255characters" +
            "domainpartwithmorethan255charactersdomainpartwithmorethan255characters" +
            "domainpartwithmorethan255characters.google.com"
    })
    @DisplayName("email validation must return violations when email is not well formed")
    void emailValidation_MustReturnViolations_WhenEmailIsNotWellFormed(String emailNotWellFormed) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email(emailNotWellFormed)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_EMAIL);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty()
          .contains("email must be a well formed E-mail");
    }

  }

  @Nested
  @DisplayName("Tests for password validation")
  class PasswordValidation {

    public static final String PROPERTY_PASSWORD = "password";

    @ParameterizedTest
    @ValueSource(strings = {"12345678", "1234567890abcdef", "123456789012345678901234567890"})
    @DisplayName("password validation must not return violations when password is valid")
    void passwordValidation_MustNotReturnViolations_WhenPasswordIsValid(String validPassword) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password(validPassword)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_PASSWORD);

      Assertions.assertThat(actualViolations).isNotNull().isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("password validation must return violations when password is blank")
    void passwordValidation_MustReturnViolations_WhenPasswordIsBlank(String blankPassword) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password(blankPassword)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_PASSWORD);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty().contains("password must not be blank");
    }

    @Test
    @DisplayName("password validation must return violations when password length is less than 8")
    void passwordValidation_MustReturnViolations_WhenPasswordLengthIsLessThan8() {
      String passwordWith7Character = "1234567";

      Assumptions.assumeThat(passwordWith7Character).hasSizeLessThan(8);

      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password(passwordWith7Character)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_PASSWORD);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty()
          .contains("password must contain between 8 and 30 characters long");
    }

    @Test
    @DisplayName("password validation must return violations when password length is greater than 30")
    void passwordValidation_MustReturnViolations_WhenPasswordLengthIsGreaterThan30() {
      String passwordWith31Character = "123456789012345678901234567890_";

      Assumptions.assumeThat(passwordWith31Character).hasSizeGreaterThan(30);

      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password(passwordWith31Character)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_PASSWORD);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty()
          .contains("password must contain between 8 and 30 characters long");
    }

  }

  @Nested
  @DisplayName("Tests for confirmPassword validation")
  class ConfirmPasswordValidation {

    public static final String PROPERTY_CONFIRM_PASSWORD = "confirmPassword";

    @ParameterizedTest
    @ValueSource(strings = {"1", "1234567890abcdef", "123456789012345678901234567890_"})
    @DisplayName("confirmPassword validation must not return violations when confirmPassword is valid")
    void confirmPasswordValidation_MustNotReturnViolations_WhenConfirmPasswordIsValid(String validConfirmPassword) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .confirmPassword(validConfirmPassword)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_CONFIRM_PASSWORD);

      Assertions.assertThat(actualViolations).isNotNull().isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("confirmPassword validation must return violations when confirmPassword is blank")
    void confirmPasswordValidation_MustReturnViolations_WhenConfirmPasswordIsBlank(String blankConfirmPassword) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .confirmPassword(blankConfirmPassword)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, PROPERTY_CONFIRM_PASSWORD);
      List<String> actualViolationMessages = violationMessageUtil.getViolationMessages(actualViolations);

      Assertions.assertThat(actualViolations).isNotNull().isNotEmpty();
      Assertions.assertThat(actualViolationMessages).isNotEmpty().contains("confirmPassword must not be blank");
    }

  }

}