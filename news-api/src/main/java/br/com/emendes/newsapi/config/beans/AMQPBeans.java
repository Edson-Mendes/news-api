package br.com.emendes.newsapi.config.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe com os beans relacionados a AMQP.
 */
@Slf4j
@Configuration
public class AMQPBeans {

  @Bean
  public Queue queue() {
    return new Queue("notification");
  }

  @Bean
  public Exchange exchange() {
    return ExchangeBuilder.directExchange("notification.ex").build();
  }

  @Bean
  public Binding bindingExchange(Exchange exchange, Queue queue) {
    log.info("Exchange: {}", exchange.getName());
    log.info("Queue: {}", queue.getName());
    return BindingBuilder.bind(queue).to(exchange).with("notification.route").noargs();
  }

}
