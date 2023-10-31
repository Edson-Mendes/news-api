package br.com.emendes.notification.listener;

import br.com.emendes.notification.dto.ConfirmationNotificationDTO;
import br.com.emendes.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.emendes.notification.config.constant.AMQPConstant.NOTIFICATION_QUEUE_NAME;

/**
 * Classe responsável por lidar com as mensagens da queue 'notification'.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationListener {

  private final NotificationService notificationService;

  @RabbitListener(queues = {NOTIFICATION_QUEUE_NAME})
  public void receiveNotification(ConfirmationNotificationDTO confirmationNotificationDTO) {
    log.info("EMAIL ::: {}", confirmationNotificationDTO.contact().email());
    log.info("TITLE ::: {}", confirmationNotificationDTO.message().title());
    log.info("CONTENT ::: {}", confirmationNotificationDTO.message().content());
    log.info("URI ::: {}", confirmationNotificationDTO.message().confirmationURL());

    notificationService.sendConfirmation(confirmationNotificationDTO);
  }

}
