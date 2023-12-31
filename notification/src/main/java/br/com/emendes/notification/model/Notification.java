package br.com.emendes.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.URI;

/**
 * Classe para representar uma notificação.
 */
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Notification {

  private Contact contact;
  private Message message;

}
