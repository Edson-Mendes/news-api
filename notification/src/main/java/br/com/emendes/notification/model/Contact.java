package br.com.emendes.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Classe que representa um Contato.
 */
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Contact {

  private String email;
  private String phone;

}
