package br.com.emendes.newsapi.util.component.impl;

import br.com.emendes.newsapi.dto.ConfirmationNotificationDTO;
import br.com.emendes.newsapi.dto.ContactDTO;
import br.com.emendes.newsapi.dto.MessageDTO;
import br.com.emendes.newsapi.model.entity.User;
import br.com.emendes.newsapi.service.JwtService;
import br.com.emendes.newsapi.util.component.NotificationGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Implementação de {@link NotificationGenerator}.
 */
@RequiredArgsConstructor
@Component
public class NotificationGeneratorImpl implements NotificationGenerator {

  private final JwtService jwtService;

  @Override
  public ConfirmationNotificationDTO generateConfirmationNotification(User user) {
    Assert.notNull(user, "user must not be null");

    String uriTemplate = "http://localhost:8080/api/users/%s?token=%s";
    String token = jwtService.generateToken(user);
    String uri = uriTemplate.formatted(user.getId(), token);

    ContactDTO contact = new ContactDTO(user.getEmail(), user.getPhone());
    MessageDTO message = new MessageDTO("Confirm your registration", "Lorem ipsum dolor sit amet", uri);

    return ConfirmationNotificationDTO.builder()
        .contact(contact)
        .message(message)
        .build();
  }

}
