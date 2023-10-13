package br.com.emendes.newsapi.unit.controller;

import br.com.emendes.newsapi.controller.UserController;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.exception.UserCreationException;
import br.com.emendes.newsapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.emendes.newsapi.util.UserUtil.userSummaryResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@DisplayName("Tests for UserController")
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private UserService userServiceMock;

  private final String CONTENT_TYPE = "application/json;charset=UTF-8";

  @Nested
  @DisplayName("Tests for register endpoint")
  class RegisterEndpoint {

    private static final String REGISTER_URI = "/api/users";

    @Test
    @DisplayName("register must return status 201 and UserSummaryResponse when register user successfully")
    void register_MustReturnStatus201AndUserSummaryResponse_WhenRegisterUserSuccessfully() throws Exception {
      when(userServiceMock.register(any())).thenReturn(userSummaryResponse());

      String requestBody = """
          {
            "name": "Lorem Ipsum",
            "email": "lorem@email.com",
            "password": "1234567890",
            "confirmPassword": "1234567890"
          }
          """;

      String actualContent = mockMvc.perform(post(REGISTER_URI).contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isCreated())
          .andReturn().getResponse().getContentAsString();
      UserSummaryResponse actualUserSummaryResponse = mapper.readValue(actualContent, UserSummaryResponse.class);

      assertThat(actualUserSummaryResponse).isNotNull();
      assertThat(actualUserSummaryResponse.id()).isNotNull().isEqualTo(100L);
      assertThat(actualUserSummaryResponse.name()).isNotNull().isEqualTo("Lorem Ipsum");
      assertThat(actualUserSummaryResponse.email()).isNotNull().isEqualTo("lorem@email.com");
    }

    @Test
    @DisplayName("register must return status 400 and ProblemDetail when request body has invalid fields")
    void register_MustReturnStatus400AndProblemDetail_WhenRequestBodyHasInvalidFields() throws Exception {
      String requestBody = """
          {
            "name": "",
            "email": "lorem@email.com",
            "password": "1234567890",
            "confirmPassword": "1234567890"
          }
          """;

      String actualContent = mockMvc.perform(post(REGISTER_URI).contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();
      ProblemDetail actualProblemDetail = mapper.readValue(actualContent, ProblemDetail.class);

      String actualFields = (String) actualProblemDetail.getProperties().get("fields");
      String actualMessages = (String) actualProblemDetail.getProperties().get("messages");

      assertThat(actualProblemDetail).isNotNull();
      assertThat(actualProblemDetail.getStatus()).isEqualTo(400);
      assertThat(actualProblemDetail.getTitle()).isNotNull().isEqualTo("Invalid fields");
      assertThat(actualProblemDetail.getDetail()).isNotNull().isEqualTo("Some fields are invalids");
      assertThat(actualFields).isNotNull().contains("name");
      assertThat(actualMessages).isNotNull().contains("name must not be blank");
    }

    @Test
    @DisplayName("register must return status 400 and ProblemDetail when password and confirmPassword does not match")
    void register_MustReturnStatus400AndProblemDetail_WhenPasswordAndConfirmPasswordDoesNotMatch() throws Exception {
      when(userServiceMock.register(any()))
          .thenThrow(new UserCreationException("password and confirmPassword do not match"));

      String requestBody = """
          {
            "name": "Lorem Ipsum",
            "email": "lorem@email.com",
            "password": "1234567890__",
            "confirmPassword": "1234567890"
          }
          """;

      String actualContent = mockMvc.perform(post(REGISTER_URI).contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();
      ProblemDetail actualProblemDetail = mapper.readValue(actualContent, ProblemDetail.class);

      assertThat(actualProblemDetail).isNotNull();
      assertThat(actualProblemDetail.getStatus()).isEqualTo(400);
      assertThat(actualProblemDetail.getTitle()).isNotNull().isEqualTo("Bad request");
      assertThat(actualProblemDetail.getDetail()).isNotNull().isEqualTo("password and confirmPassword do not match");
    }

    @Test
    @DisplayName("register must return status 400 and ProblemDetail when email already exists")
    void register_MustReturnStatus400AndProblemDetail_WhenEmailAlreadyExists() throws Exception {
      when(userServiceMock.register(any()))
          .thenThrow(new UserCreationException("E-mail {lorem@email.com} is already in use"));

      String requestBody = """
          {
            "name": "Lorem Ipsum",
            "email": "lorem@email.com",
            "password": "1234567890",
            "confirmPassword": "1234567890"
          }
          """;

      String actualContent = mockMvc.perform(post(REGISTER_URI).contentType(CONTENT_TYPE).content(requestBody))
          .andExpect(status().isBadRequest())
          .andReturn().getResponse().getContentAsString();
      ProblemDetail actualProblemDetail = mapper.readValue(actualContent, ProblemDetail.class);

      assertThat(actualProblemDetail).isNotNull();
      assertThat(actualProblemDetail.getStatus()).isEqualTo(400);
      assertThat(actualProblemDetail.getTitle()).isNotNull().isEqualTo("Bad request");
      assertThat(actualProblemDetail.getDetail()).isNotNull().isEqualTo("E-mail {lorem@email.com} is already in use");
    }

  }

}