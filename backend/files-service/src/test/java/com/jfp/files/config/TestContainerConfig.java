package com.jfp.files.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfig {

  private static final MySQLContainer<?> MYSQL_CONTAINER =
      new MySQLContainer<>(DockerImageName.parse("mysql:8.0.34"));

  @Bean
  @ServiceConnection(name = "mysql")
  public static MySQLContainer<?> mySQLContainer(final DynamicPropertyRegistry registry) {
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    registry.add("spring.jpa.show-sql", () -> "true");
    return MYSQL_CONTAINER;
  }
}
