package br.com.emendes.notification.sender.impl;

import br.com.emendes.notification.model.Notification;
import br.com.emendes.notification.sender.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementação de {@link NotificationSender} para envio de notificação via SMS.
 */
@Slf4j
@Component
public class SMSNotificationSender implements NotificationSender {

  @Override
  public void send(Notification notification) {
    // TODO: Apenas uma implementação para executar a aplicação.
    log.info("Sending SMS para: {}", notification.getContact().getPhone());
    log.info("SMS sent!");
  }

}
