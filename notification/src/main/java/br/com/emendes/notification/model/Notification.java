package br.com.emendes.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.URI;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Notification {

  private String address;
  private String content;
  private URI uri;

}
