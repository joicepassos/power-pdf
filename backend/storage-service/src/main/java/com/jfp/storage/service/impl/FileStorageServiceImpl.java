package com.jfp.storage.service.impl;

import com.jfp.storage.dto.UploadFileResponse;
import com.jfp.storage.service.FileStorageService;
import com.jfp.storage.service.MinioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

  private final MinioService minioService;

  @Override
  public UploadFileResponse uploadFile(
      final MultipartFile file, final String filePath, final Boolean temporary) {
    String storedFileName = minioService.uploadFile(file, filePath, temporary);
    return new UploadFileResponse(storedFileName, file.getSize());
  }

  @Override
  public byte[] downloadFile(String fileName) {
    return new byte[0];
  }

  @Override
  public void deleteFile(String fileName) {}
}
