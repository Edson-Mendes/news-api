package br.com.emendes.newsapi.util.component;

import br.com.emendes.newsapi.dto.ConfirmationNotificationDTO;
import br.com.emendes.newsapi.model.entity.User;

/**
 * Interface component que contém as abstrações para geração de objeto notificação.
 */
public interface NotificationGenerator {

  /**
   * Gera {@link ConfirmationNotificationDTO} apartir dos dados de usuário.
   *
   * @param user objeto contendo os dados do usuário.
   * @return Objeto com as informações para gerar uma notificação de confirmação.
   */
  ConfirmationNotificationDTO generateConfirmationNotification(User user);

}
