package com.jfp.files.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.RabbitMQContainer;

@Configuration
public class TestRabbitMQConfig {

  @Bean
  public RabbitMQContainer rabbitMQContainer() {
    RabbitMQContainer container = new RabbitMQContainer("rabbitmq:3.8-management");
    container.start();
    return container;
  }

  @Bean
  public CachingConnectionFactory connectionFactory(RabbitMQContainer rabbitMQContainer) {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setHost(rabbitMQContainer.getHost());
    connectionFactory.setPort(rabbitMQContainer.getAmqpPort());
    connectionFactory.setUsername(rabbitMQContainer.getAdminUsername());
    connectionFactory.setPassword(rabbitMQContainer.getAdminPassword());
    return connectionFactory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }
}
