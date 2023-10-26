package br.com.emendes.notification.service;

import br.com.emendes.notification.dto.ConfirmationNotificationDTO;

/**
 * Interface service com as abstrações responsáveis por enviar notificações.
 */
public interface NotificationService {

  /**
   * Envia notificação de confirmação.
   *
   * @param confirmationNotificationDTO objeto contendo os dados da notificação a ser enviada.
   */
  void sendConfirmation(ConfirmationNotificationDTO confirmationNotificationDTO);

}
