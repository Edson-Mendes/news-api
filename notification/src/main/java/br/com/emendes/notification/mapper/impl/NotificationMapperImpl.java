package br.com.emendes.notification.mapper.impl;

import br.com.emendes.notification.dto.ConfirmationNotificationDTO;
import br.com.emendes.notification.mapper.NotificationMapper;
import br.com.emendes.notification.model.Notification;
import org.springframework.util.Assert;

import java.net.URI;

/**
 * Implementação de {@link NotificationMapper}.
 */
public class NotificationMapperImpl implements NotificationMapper {

  @Override
  public Notification toNotification(ConfirmationNotificationDTO confirmationNotificationDTO) {
    Assert.notNull(confirmationNotificationDTO, "confirmationNotificationDTO must not be null");

    return Notification.builder()
        .address(confirmationNotificationDTO.addresseeEmail())
        .title("Confirm your account at News APP")
        .content(confirmationNotificationDTO.content())
        .uri(URI.create(confirmationNotificationDTO.confirmationURI()))
        .build();
  }

}
