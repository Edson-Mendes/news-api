package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.service.NotificationSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link NotificationSenderService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationSenderServiceImpl implements NotificationSenderService {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void send(String message) {
    rabbitTemplate.convertAndSend("notification.ex", "notification.route", message);

    log.info("notification has been sent");
  }

}
