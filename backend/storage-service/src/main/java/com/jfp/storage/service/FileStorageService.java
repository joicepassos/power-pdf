package com.jfp.storage.service;

import com.jfp.storage.dto.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

  UploadFileResponse uploadFile(MultipartFile file, String filePath, Boolean temporary);

  byte[] downloadFile(String fileName);

  void deleteFile(String fileName);
}
