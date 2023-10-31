package br.com.emendes.notification.mapper.impl;

import br.com.emendes.notification.dto.ConfirmationNotificationDTO;
import br.com.emendes.notification.mapper.NotificationMapper;
import br.com.emendes.notification.model.Contact;
import br.com.emendes.notification.model.Message;
import br.com.emendes.notification.model.Notification;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.net.URI;

/**
 * Implementação de {@link NotificationMapper}.
 */
@Component
public class NotificationMapperImpl implements NotificationMapper {

  @Override
  public Notification toNotification(ConfirmationNotificationDTO confirmationNotificationDTO) {
    Assert.notNull(confirmationNotificationDTO, "confirmationNotificationDTO must not be null");
    Assert.notNull(confirmationNotificationDTO.contact(), "confirmationNotificationDTO#contact must not be null");
    Assert.notNull(confirmationNotificationDTO.message(), "confirmationNotificationDTO#message must not be null");

    Contact contact = Contact.builder()
        .email(confirmationNotificationDTO.contact().email())
        .phone(confirmationNotificationDTO.contact().phone())
        .build();

    Message message = Message.builder()
        .title(confirmationNotificationDTO.message().title())
        .content(confirmationNotificationDTO.message().content())
        .uri(URI.create(confirmationNotificationDTO.message().confirmationURL()))
        .build();

    return Notification.builder()
        .contact(contact)
        .message(message)
        .build();
  }

}
