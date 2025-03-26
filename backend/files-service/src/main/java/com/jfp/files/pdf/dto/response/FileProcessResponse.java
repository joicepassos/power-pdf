package com.jfp.files.pdf.dto.response;

import com.jfp.files.pdf.util.EFileStatus;

public record FileProcessResponse(
    Integer id, String fileName, String link, String createdAt, EFileStatus status) {

  public static FileProcessResponse fromEntity(
      Integer id, String fileName, String link, String createdAt, EFileStatus status) {
    return new FileProcessResponse(id, fileName, link, createdAt, status);
  }
}
