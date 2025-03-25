package com.jfp.storage.util;

import com.jfp.storage.config.MinioConfig;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MinioUtil {
  private final MinioClient minioClient;
  private final MinioConfig minioConfig;

  @SneakyThrows
  public boolean bucketExists(String bucketName) {
    boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    return found;
  }

  @SneakyThrows
  public boolean makeBucket(String bucketName) {
    log.info("[FileStorageService] - Making bucket: " + bucketName);

    boolean flag = bucketExists(bucketName);

    log.info("[FileStorageService] - Bucket exists: " + flag);

    if (!flag) {
      minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

      return true;
    } else {
      return false;
    }
  }
}
