package br.com.emendes.newsapi.dto.request;

import lombok.Builder;

/**
 * Record DTO para receber os dados de criação de User.
 */
// TODO: adicionar as validações de dados.
@Builder
public record CreateUserRequest(
    String name,
    String email,
    String password,
    String confirmPassword
) {
}
