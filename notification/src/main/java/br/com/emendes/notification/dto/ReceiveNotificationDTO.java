package br.com.emendes.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Record DTO sobre notificação.
 */
public record ReceiveNotificationDTO(
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a well formed E-mail")
    String email,
    @NotBlank(message = "content must not be blank")
    String content
) {
}
