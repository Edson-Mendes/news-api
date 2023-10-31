package br.com.emendes.notification.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Record DTO sobre notificação.
 */
public record ConfirmationNotificationDTO(
    @Valid
    @NotNull(message = "contact must not be null")
    ContactDTO contact,
    @Valid
    @NotNull(message = "message must not be null")
    MessageDTO message
) {
}
