package br.com.emendes.newsapi.dto;

/**
 * Record DTO com as informações da mensagem da notificação.
 */
public record MessageDTO(
    String title,
    String content,
    String confirmationURL
) {
}
