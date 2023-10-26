package br.com.emendes.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * Record DTO sobre notificação.
 */
public record ConfirmationNotificationDTO(
    @NotBlank(message = "addresseeEmail must not be blank")
    @Email(message = "addresseeEmail must be a well formed E-mail")
    String addresseeEmail,
    @NotBlank(message = "content must not be blank")
    String content,
    @URL(message = "confirmationURI must be a well formed URL")
    String confirmationURI
) {
}
