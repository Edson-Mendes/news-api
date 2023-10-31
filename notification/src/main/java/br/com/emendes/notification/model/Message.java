package br.com.emendes.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.URI;

/**
 * Classe que representa a mensagem de uma notificação.
 */
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Message {

  private String title;
  private String content;
  private URI uri;

}
