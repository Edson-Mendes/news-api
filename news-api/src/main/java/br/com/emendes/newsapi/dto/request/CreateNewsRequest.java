package br.com.emendes.newsapi.dto.request;

/**
 * Record DTO para receber os dados de criação de News.
 */
public record CreateNewsRequest(
    String title,
    String content,
    String tag,
    boolean verified,
    String source
) {
}
