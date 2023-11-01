package br.com.emendes.newsapi.dto;

/**
 * Record DTO com as informações de contato do usuário.
 */
public record ContactDTO(
    String email,
    String phone
) {
}
