package com.jfp.storage.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

  String uploadFile(MultipartFile file, String filePath, boolean temporary);

  String getFileUrl(String fileName, String filePath);
}
