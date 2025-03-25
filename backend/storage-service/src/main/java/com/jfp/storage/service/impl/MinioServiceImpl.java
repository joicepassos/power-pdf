package com.jfp.storage.service.impl;

import com.jfp.storage.dto.MinioProperties;
import com.jfp.storage.service.MinioService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

  private final MinioClient minioClient;
  private final MinioProperties minioProperties;

  public String uploadFile(
      final MultipartFile file, final String filePath, final boolean temporary) {
    try {
      String basePath = temporary ? "temporary/" : "uploads/";

      String fullFilePath =
          basePath
              + filePath
              + "/"
              + UUID.randomUUID()
              + "."
              + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];

      InputStream inputStream = file.getInputStream();

      minioClient.putObject(
          PutObjectArgs.builder().bucket(minioProperties.getBucket()).object(fullFilePath).stream(
                  inputStream, file.getSize(), -1)
              .contentType(file.getContentType())
              .build());

      return fullFilePath;
    } catch (Exception e) {
      log.error("[MinioService] Error on upload file", e);
      throw new RuntimeException("Error on upload file", e);
    }
  }

  public String getFileUrl(final String fileName, final String filePath) {
    try {
      return minioClient.getPresignedObjectUrl(
          GetPresignedObjectUrlArgs.builder()
              .method(Method.GET)
              .bucket(minioProperties.getBucket())
              .object(filePath + fileName)
              .build());
    } catch (Exception e) {
      log.error("[MinioService] Error on get file URL", e);
      throw new RuntimeException("Error on get file URL", e);
    }
  }
}
