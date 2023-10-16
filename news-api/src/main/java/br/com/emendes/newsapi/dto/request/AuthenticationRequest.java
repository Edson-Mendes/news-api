package br.com.emendes.newsapi.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Record DTO para receber os dados de autenticação de usuário.
 */
public record AuthenticationRequest(
    @NotBlank(message = "username must not be blank")
    String username,
    @NotBlank(message = "password must not be blank")
    String password
) {
}
