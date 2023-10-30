package br.com.emendes.notification.mapper;

import br.com.emendes.notification.dto.ConfirmationNotificationDTO;
import br.com.emendes.notification.model.Notification;

/**
 * Interface com as abstrações para mapear uma Notification.
 */
public interface NotificationMapper {

  /**
   * Mapeia um objeto {@link ConfirmationNotificationDTO} para {@link Notification}.
   *
   * @param confirmationNotificationDTO objeto a ser mapeado para Notification.
   * @return Notification com as informações que estavam em confirmationNotificationDTO.
   * @throws IllegalArgumentException caso confirmationNotificationDTO for null.
   */
  Notification toNotification(ConfirmationNotificationDTO confirmationNotificationDTO);

}
