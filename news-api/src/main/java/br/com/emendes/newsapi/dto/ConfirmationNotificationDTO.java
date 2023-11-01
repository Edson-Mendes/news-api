package br.com.emendes.newsapi.dto;

import lombok.Builder;

/**
 * Record DTO sobre notificação.
 */
@Builder
public record ConfirmationNotificationDTO(
    ContactDTO contact,
    MessageDTO message
) {
}
