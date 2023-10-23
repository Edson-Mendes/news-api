package br.com.emendes.newsapi.service;

/**
 * Interface service com as abstrações responsáveis por enviar notificações.
 */
public interface NotificationSenderService {

  /**
   * Envia uma mensagem para o broker rabbitmq.
   *
   * @param message objeto mensagem a ser enviado
   */
  void send(String message);

}
