package br.com.emendes.newsapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Record DTO para receber os dados de criação de User.
 */
@Builder
public record CreateUserRequest(
    @NotBlank(message = "name must not be blank")
    @Size(min = 2, max = 50, message = "name must contain between {min} and {max} characters long")
    String name,
    @NotBlank(message = "email must not be blank")
    @Size(max = 320, message = "email must contain {max} characters long")
    @Email(message = "email must be a well formed E-mail")
    String email,
    @NotBlank(message = "password must not be blank")
    @Size(min = 8, max = 30, message = "password must contain between {min} and {max} characters long")
    String password,
    @NotBlank(message = "confirmPassword must not be blank")
    String confirmPassword
) {
}
