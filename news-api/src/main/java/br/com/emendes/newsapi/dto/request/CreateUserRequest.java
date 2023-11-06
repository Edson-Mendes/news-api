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
    @Size(max = 320, message = "email must contain max {max} characters long")
    @Email(message = "email must be a well formed E-mail")
    String email,
    @NotBlank(message = "phone must not be blank")
    @Size(min = 12, max = 15, message = "phone must contain between {min} and {max} characters long")
    String phone,
    @NotBlank(message = "password must not be blank")
    @Size(min = 8, max = 30, message = "password must contain between {min} and {max} characters long")
    String password,
    @NotBlank(message = "confirmPassword must not be blank")
    String confirmPassword
) {
}
