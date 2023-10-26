package br.com.emendes.notification.service.impl;

import br.com.emendes.notification.dto.ConfirmationNotificationDTO;
import br.com.emendes.notification.sender.NotificationSender;
import br.com.emendes.notification.service.NotificationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementação de {@link NotificationService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

  private final Validator validator;
  private final Set<NotificationSender> senders;

  @Override
  public void sendConfirmation(ConfirmationNotificationDTO confirmationNotificationDTO) {
    Set<ConstraintViolation<ConfirmationNotificationDTO>> constraintViolations = validator.validate(confirmationNotificationDTO);

    if (!constraintViolations.isEmpty()) {
      String message = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
      log.info("confirmationNotificationDTO has invalid field(s). Error messages ::: {}", message);
      return;
    }

    // TODO: Implementar um mapper para converter ConfirmationNotificationDTO para Notification.

    for (NotificationSender sender : senders) {
//      sender.send(notification);
    }
  }

}
