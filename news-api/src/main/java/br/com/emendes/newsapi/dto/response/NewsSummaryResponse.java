package br.com.emendes.newsapi.dto.response;

import lombok.Builder;

/**
 * Record DTO para enviar informações resumidas da News.
 */
@Builder
public record NewsSummaryResponse(
    Long id,
    String title,
    String tag
) {
}
