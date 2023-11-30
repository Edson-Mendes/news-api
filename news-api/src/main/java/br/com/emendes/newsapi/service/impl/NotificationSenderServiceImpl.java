package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.dto.ConfirmationNotificationDTO;
import br.com.emendes.newsapi.service.NotificationSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static br.com.emendes.newsapi.util.NotificationSchedule.NOTIFICATION_STORAGE;

/**
 * Implementação de {@link NotificationSenderService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationSenderServiceImpl implements NotificationSenderService {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void send(ConfirmationNotificationDTO confirmationNotificationDTO) {
    try {
      rabbitTemplate.convertAndSend(
          "notification.ex", "notification.route", confirmationNotificationDTO);
      log.info("notification has been sent");
    } catch (Exception exception) {
      log.error("Something bad happen ::: {}", exception.getMessage());
      NOTIFICATION_STORAGE.push(confirmationNotificationDTO);
    }
  }

}
