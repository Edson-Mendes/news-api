package br.com.emendes.newsapi.util;

import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.Set;

/**
 * Classe utilitária para pegar as mensagens de {@link ConstraintViolation}.
 *
 * @param <T> o tipo do bean validado.
 */
public class ViolationMessageUtil<T> {

  /**
   * Retorna uma lista das mensagens de erro de cada ConstraintViolation.
   *
   * @param actualViolations {@code Set<ConstraintViolation<T>>} contendo as violations.
   */
  public List<String> getViolationMessages(Set<ConstraintViolation<T>> actualViolations) {
    return actualViolations.stream().map(ConstraintViolation::getMessage).toList();
  }

}
