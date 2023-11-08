package br.com.emendes.newsapi.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Record DTO para enviar informações detalhadas do User.
 */
@Builder
public record UserDetailsResponse(
    Long id,
    String name,
    String email,
    String phone,
    boolean enabled,
    LocalDateTime createdAt
) {
}
