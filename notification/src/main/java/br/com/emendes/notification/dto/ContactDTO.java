package br.com.emendes.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Record DTO com as informações de contato.
 */
public record ContactDTO(
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a well formed E-mail")
    String email,
    @NotBlank(message = "phone must not be blank")
    String phone
) {
}
