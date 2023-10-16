package br.com.emendes.newsapi.unit.service;

import br.com.emendes.newsapi.dto.request.AuthenticationRequest;
import br.com.emendes.newsapi.dto.response.AuthenticationResponse;
import br.com.emendes.newsapi.service.JwtService;
import br.com.emendes.newsapi.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.emendes.newsapi.util.UserUtil.user;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests for AuthenticationServiceImpl")
class AuthenticationServiceImplTest {

  @InjectMocks
  private AuthenticationServiceImpl authenticationService;
  @Mock
  private AuthenticationManager authenticationManagerMock;
  @Mock
  private JwtService jwtServiceMock;
  @Mock
  private Authentication authenticationMock;

  @Test
  @DisplayName("authenticate must return AuthenticationResponse when authenticate successfully")
  void authenticate_MustReturnAuthenticationResponse_WhenAuthenticateSuccessfully() {
    when(authenticationManagerMock.authenticate(any())).thenReturn(authenticationMock);
    when(authenticationMock.getPrincipal()).thenReturn(user());
    when(jwtServiceMock.generateToken(any())).thenReturn("json.web.token");

    AuthenticationRequest authenticationRequest =
        new AuthenticationRequest("lorem@email.com", "1234567890");

    AuthenticationResponse actualAuthenticationResponse = authenticationService.authenticate(authenticationRequest);

    assertThat(actualAuthenticationResponse).isNotNull();
    assertThat(actualAuthenticationResponse.token()).isNotNull().isNotBlank();
    assertThat(actualAuthenticationResponse.type()).isNotNull().isEqualTo("Bearer");
  }

  @Test
  @DisplayName("authenticate must throw BadCredentialsException when credentials are invalid")
  void authenticate_MustThrowBadCredentialsException_WhenCredentialsAreInvalid() {
    when(authenticationManagerMock.authenticate(any())).thenThrow(new BadCredentialsException("Bad credentials"));

    AuthenticationRequest authenticationRequest =
        new AuthenticationRequest("lorem@email.com", "1234567890");

    assertThatExceptionOfType(BadCredentialsException.class)
        .isThrownBy(() -> authenticationService.authenticate(authenticationRequest))
        .withMessage("Bad credentials");
  }

}