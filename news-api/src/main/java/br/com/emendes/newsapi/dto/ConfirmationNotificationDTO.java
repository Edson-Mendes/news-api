package br.com.emendes.newsapi.dto;

/**
 * Record DTO sobre notificação.
 */
public record ConfirmationNotificationDTO(
    String email,
    String content
) {
}
