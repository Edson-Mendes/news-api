package br.com.emendes.newsapi.dto;

import lombok.Builder;

import java.net.URI;

/**
 * Record DTO sobre notificação.
 */
@Builder
public record ConfirmationNotificationDTO(
    String addresseeEmail,
    String content,
    URI confirmationURI
) {
}
