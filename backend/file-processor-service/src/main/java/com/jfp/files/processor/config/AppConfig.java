package com.jfp.files.processor.config;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

  @Value("${application.title}")
  private String title;

  @Value("${application.version}")
  private String version;

  @Value("${server.servlet.context-path}")
  private String context;

  @Value("${app.url}")
  private String url;

  @Value("${server.port}")
  private int localServerPort;

  @Bean
  RestClient restClient(RestClient.Builder builder) {
    final var jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory();
    jdkClientHttpRequestFactory.setReadTimeout(40_000);
    return builder
        .messageConverters(
            httpMessageConverters ->
                httpMessageConverters.add(getMappingJackson2HttpMessageConverter()))
        .requestFactory(jdkClientHttpRequestFactory)
        .build();
  }

  private MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
        new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
        Collections.singletonList(APPLICATION_FORM_URLENCODED));
    return mappingJackson2HttpMessageConverter;
  }

  @Bean
  OpenAPI openApi() {
    var server = new Server();
    if (url.contains("localhost")) {
      server.setUrl(url + ":" + localServerPort + context);
      server.setDescription("local");
    } else {
      server.setUrl(url + context);
      server.setDescription("remote");
    }
    return new OpenAPI().info(new Info().title(title).version(version)).servers(List.of(server));
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
