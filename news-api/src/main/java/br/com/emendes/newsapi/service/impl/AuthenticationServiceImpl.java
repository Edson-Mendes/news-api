package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.dto.request.AuthenticationRequest;
import br.com.emendes.newsapi.dto.response.AuthenticationResponse;
import br.com.emendes.newsapi.service.AuthenticationService;
import br.com.emendes.newsapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link AuthenticationService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
    Authentication authentication = authenticationManager
        .authenticate(getAuthentication(authenticationRequest));

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    String token = jwtService.generateToken(userDetails);
    log.info("token generate successfully for user : {}", userDetails.getUsername());

    return AuthenticationResponse.builder()
        .type("Bearer")
        .token(token)
        .build();
  }

  /**
   * Retorna um Authentication com os dados de AuthenticationRequest.
   *
   * @param authenticationRequest contendo os dados de autenticação.
   * @return {@link Authentication}.
   */
  private static Authentication getAuthentication(AuthenticationRequest authenticationRequest) {
    return new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password());
  }

}
