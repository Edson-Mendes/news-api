package br.com.emendes.newsapi.dto.response;

import lombok.Builder;

/**
 * Record DTO para enviar informações resumidas do User.
 */
@Builder
public record UserSummaryResponse(
    Long id,
    String name,
    String email,
    boolean enabled
) {
}
