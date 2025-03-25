package com.jfp.files.pdf.mapper;

import com.jfp.files.pdf.dto.response.MergeFileResponse;
import com.jfp.files.pdf.entity.FileDocument;
import org.springframework.stereotype.Component;

@Component
public class FileDocumentMapper {

  public MergeFileResponse toMergeFileResponse(FileDocument document) {
    if (document == null) {
      return null;
    }

    return new MergeFileResponse(
        document.getId(),
        document.getName(),
        document.getLink(),
        document.getCreatedAt().toString());
  }
}
