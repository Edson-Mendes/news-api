package br.com.emendes.notification.sender.impl;

import br.com.emendes.notification.model.Notification;
import br.com.emendes.notification.sender.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementação de {@link NotificationSender} para envio de notificação via E-mail.
 */
@Slf4j
@Component
public class EmailNotificationSender implements NotificationSender {

  @Override
  public void send(Notification notification) {
    // TODO: Apenas uma implementação para executar a aplicação.
    log.info("Sending email para: {}", notification.getAddress());
    log.info("email sent!");
  }

}
