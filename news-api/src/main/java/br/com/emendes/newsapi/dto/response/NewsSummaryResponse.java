package br.com.emendes.newsapi.dto.response;

/**
 * Record DTO para enviar informações resumidas da News.
 */
public record NewsSummaryResponse(
    Long id,
    String title,
    String tag
) {
}
