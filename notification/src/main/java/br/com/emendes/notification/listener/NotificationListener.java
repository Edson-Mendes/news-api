package br.com.emendes.notification.listener;

import br.com.emendes.notification.dto.ConfirmationNotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.emendes.notification.config.constant.AMQPConstant.NOTIFICATION_QUEUE_NAME;

/**
 * Classe responsável por ouvir a queue 'notifiation'.
 */
@Slf4j
@Component
public class NotificationListener {

  @RabbitListener(queues = {NOTIFICATION_QUEUE_NAME})
  public void receiveNotification(ConfirmationNotificationDTO confirmationNotificationDTO) {
    log.info("EMAIL ::: {}", confirmationNotificationDTO.addresseeEmail());
    log.info("CONTENT ::: {}", confirmationNotificationDTO.content());
    log.info("URI ::: {}", confirmationNotificationDTO.confirmationURI());

    // TODO: Delegar para um service realizar a notificação.
  }

}
