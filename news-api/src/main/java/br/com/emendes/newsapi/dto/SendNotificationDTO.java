package br.com.emendes.newsapi.dto;

/**
 * Record DTO sobre notificação.
 */
public record SendNotificationDTO(
    String email,
    String content
) {
}
