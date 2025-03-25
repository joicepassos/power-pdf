package com.jfp.files.processor.client;

import com.jfp.files.processor.client.dto.UploadFileResponse;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Slf4j
@Component
@RequiredArgsConstructor
public class StorageClient {

  @Value("${app.storage.url}")
  private String storageUrl;

  @Value("${app.storage.auth}")
  private String storageAuth;

  private final RestClient restClient;

  public byte[] downloadFile(String filePath) {
    try {
      final String uri = "%s/download/%s/%s".formatted(storageUrl, filePath);

      var request =
          restClient
              .get()
              .uri(uri)
              .headers(
                  headers -> {
                    headers.setBasicAuth(storageAuth);
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                  })
              .retrieve()
              .toEntity(byte[].class);

      log.info("[StorageClient] - Downloading file - Path: {}", filePath);

      return request.getBody();

    } catch (Exception e) {
      throw new RestClientException("Falha ao baixar arquivo", e);
    }
  }

  public UploadFileResponse uploadFile(byte[] file, String filePath) {
    try {
      final String uri = "%s/upload?temporary=false&filePath=%s".formatted(storageUrl, filePath);

      log.info("[StorageClient] - Uploading file - Path: {}", filePath);

      UploadFileResponse request =
          restClient
              .post()
              .uri(uri)
              .body(file)
              .headers(
                  headers -> {
                    headers.setBasicAuth(storageAuth);
                    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                  })
              .retrieve()
              .toEntity(UploadFileResponse.class)
              .getBody();

      log.info("[StorageClient] - File uploaded - Path: {}", filePath);

      return request;
    } catch (Exception e) {
      throw new RestClientException("Falha ao fazer upload do arquivo", e);
    }
  }

  private String getBasicAuth() {
    return Base64.getEncoder().encodeToString(storageAuth.getBytes());
  }
}
