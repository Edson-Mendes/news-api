package br.com.emendes.notification.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 * Record DTO com as informações de mensagem.
 */
public record MessageDTO(
    @NotBlank(message = "title must not be blank")
    String title,
    @NotBlank(message = "title must not be blank")
    String content,
    @NotBlank(message = "confirmationURL must not be blank")
    @URL(message = "confirmationURL must be a well formed URL")
    String confirmationURL
) {
}
