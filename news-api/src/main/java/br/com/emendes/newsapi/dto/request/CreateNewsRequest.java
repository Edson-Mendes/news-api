package br.com.emendes.newsapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/**
 * Record DTO para receber os dados de criação de News.
 */
public record CreateNewsRequest(
    @NotBlank(message = "title must not be blank")
    @Size(max = 150, message = "title must contains max {max} characters long")
    String title,
    @NotBlank(message = "content must not be blank")
    String content,
    @NotBlank(message = "tag must not be blank")
    String tag,
    boolean verified,
    @URL(message = "source must be a valid URL")
    String source
) {
}
