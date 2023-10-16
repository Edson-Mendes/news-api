package br.com.emendes.newsapi.dto.response;

import lombok.Builder;

/**
 * Record DTO para enviar JWT ao cliente.
 */
@Builder
public record AuthenticationResponse(
    String token,
    String type
) {
}
