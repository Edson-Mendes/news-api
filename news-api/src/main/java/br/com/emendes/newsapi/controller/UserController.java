package br.com.emendes.newsapi.controller;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.service.AccountService;
import br.com.emendes.newsapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
  private final AccountService accountService;

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

  /**
   * Método responsável por PATCH /api/users/{id}.
   */
  @PatchMapping("/{id}")
  public ResponseEntity<Void> activate(@PathVariable(name = "id") long id, @RequestParam(name = "token") String token) {
    accountService.enableAccount(id, token);

    return ResponseEntity.noContent().build();
  }

}
