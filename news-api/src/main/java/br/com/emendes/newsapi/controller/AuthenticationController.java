package br.com.emendes.newsapi.controller;

import br.com.emendes.newsapi.dto.request.AuthenticationRequest;
import br.com.emendes.newsapi.dto.response.AuthenticationResponse;
import br.com.emendes.newsapi.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável pelo endpoint /api/auth/**.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Método responsável por POST /api/auth.
   */
  @PostMapping
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
    AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

    return ResponseEntity.ok(authenticationResponse);
  }

}
