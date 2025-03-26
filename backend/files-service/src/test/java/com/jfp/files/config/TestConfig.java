package com.jfp.files.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(
    properties = {
      "spring.security.user.name=test",
      "spring.security.user.password=$2a$10$irvpvBMOAoNRPXa5e7HIXet/1o637/Uvc6QENucEbQCuY32T5/ZcS",
      "app.token=token"
    },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainerConfig.class)
@ContextConfiguration(classes = {TestRabbitMQConfig.class})
public abstract class TestConfig {
  protected static final String TOKEN = "token";
  protected static final String BASIC_AUTH = "Basic dGVzdDp0ZXN0";

  @Autowired protected MockMvc mockMvc;
  @Autowired protected ObjectMapper objectMapper;
}
