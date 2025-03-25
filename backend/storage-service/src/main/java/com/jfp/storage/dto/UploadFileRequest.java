package com.jfp.storage.dto;

import com.jfp.storage.util.EFileType;

public record UploadFileRequest(String fileName, EFileType fileType, String filePath) {}
