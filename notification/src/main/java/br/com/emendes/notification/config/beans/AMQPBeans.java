package br.com.emendes.notification.config.beans;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe com os beans relacionados a AMQP.
 */
@Configuration
public class AMQPBeans {

  @Bean
  public Queue queue() {
    return new Queue("notification");
  }

}
