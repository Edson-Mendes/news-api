package br.com.emendes.notification.sender;

import br.com.emendes.notification.model.Notification;

/**
 * Interface com as abstrações para o envio de notificação.
 */
public interface NotificationSender {

  /**
   * Envia uma notificação.
   *
   * @param notification objeto notificação a ser enviado.
   */
  void send(Notification notification);

}
