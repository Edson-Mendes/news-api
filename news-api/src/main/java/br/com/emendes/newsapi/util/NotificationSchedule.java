package br.com.emendes.newsapi.util;

import br.com.emendes.newsapi.dto.ConfirmationNotificationDTO;
import br.com.emendes.newsapi.service.NotificationSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Responsável por armazenar em memória as notificações que não puderem ser enviadas
 * para serem enviadas futuramente. E reenviar as notificações depois de um certo período de tempo.
 * <p>(SOLUÇÂO TEMPORÁRIA)</p>
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationSchedule {

  public static final Deque<ConfirmationNotificationDTO> NOTIFICATION_STORAGE = new LinkedList<>();
  private final NotificationSenderService notificationSenderService;

  @Scheduled(fixedDelay = 10_000)
  public void sendStoredNotification() {
    if (!NOTIFICATION_STORAGE.isEmpty()) {
      log.info("resubmitting notification");
      notificationSenderService.send(NOTIFICATION_STORAGE.pop());
    } else {
      log.info("notification storage is empty!");
    }
  }

}
