package com.jfp.files.client;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import com.jfp.files.client.dto.UploadFileResponse;
import com.jfp.files.pdf.exception.RequestException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class StorageClient {

  @Value("${app.storage.url}")
  private String storageUrl;

  @Value("${app.storage.auth}")
  private String storageAuth;

  private final RestClient restClient;

  public UploadFileResponse uploadFile(
      final ByteArrayResource byteArray, final String filePath, final Boolean temporary) {

      final String uri =
          "%s/files/upload?temporary=%b&filePath=%s".formatted(storageUrl, temporary, filePath);

      log.info("[StorageClient] - Uploading file - Path: {}", filePath);

      var body = new LinkedMultiValueMap<>();
      body.add("file", byteArray);

      UploadFileResponse response =
          restClient
              .post()
              .uri(uri)
              .body(body)
              .headers(getHeaders())
              .retrieve()
              .onStatus(HttpStatusCode::isError, this::handleException)
              .toEntity(UploadFileResponse.class)
              .getBody();

      log.info("[StorageClient] - File uploaded - Path: {}", filePath);

      return response;

  }

  public void handleException(final HttpRequest request, final ClientHttpResponse response)
      throws IOException {
    if (response.getBody().available() > 0) {
      final byte[] bytes = response.getBody().readAllBytes();
      final String body = new String(bytes, StandardCharsets.UTF_8);
      throw new RequestException(response.getStatusCode().value(), response.getStatusText(), body);
    } else {
      throw new RequestException(response.getStatusCode().value(), response.getStatusText());
    }
  }

  private Consumer<HttpHeaders> getHeaders() {
    return headers -> {
      headers.setContentType(MULTIPART_FORM_DATA);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
      headers.add(AUTHORIZATION, "Basic " + getBasicAuth());
    };
  }

  private String getBasicAuth() {
    return Base64.getEncoder().encodeToString(storageAuth.getBytes());
  }
}
