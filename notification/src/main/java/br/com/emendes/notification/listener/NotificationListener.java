package br.com.emendes.notification.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por ouvir a queue 'notifiation'.
 */
@Slf4j
@Component
public class NotificationListener {

  @RabbitListener(queues = "notification")
  public void receiveNotification(String message) {
    log.info("A MENSAGEM É ::: {}", message);
  }

}
