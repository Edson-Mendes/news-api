package br.com.emendes.newsapi.controller;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/users/**.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  /**
   * Método responsável por POST /api/users.
   */
  @PostMapping
  public ResponseEntity<UserSummaryResponse> register(
      @RequestBody @Valid CreateUserRequest createUserRequest,
      UriComponentsBuilder uriBuilder) {
    UserSummaryResponse userSummaryResponse = userService.register(createUserRequest);
    URI location = uriBuilder.path("/api/users/{id}").build(userSummaryResponse.id());

    return ResponseEntity.created(location).body(userSummaryResponse);
  }

}
