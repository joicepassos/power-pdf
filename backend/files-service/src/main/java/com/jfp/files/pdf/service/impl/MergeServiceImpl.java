package com.jfp.files.pdf.service.impl;

import com.jfp.files.client.StorageClient;
import com.jfp.files.client.dto.UploadFileResponse;
import com.jfp.files.pdf.dto.response.MergeFileResponse;
import com.jfp.files.pdf.entity.FileDocument;
import com.jfp.files.pdf.mapper.FileDocumentMapper;
import com.jfp.files.pdf.service.FileDocumentService;
import com.jfp.files.pdf.service.MergeService;
import com.jfp.files.pdf.util.EFileStatus;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MergeServiceImpl implements MergeService {

  private final FileDocumentService fileDocumentService;
  private final FileDocumentMapper fileDocumentMapper;
  private final StorageClient storageClient;
  public final String MERGE_PATH = "/merges/pdfs";

  @Override
  @Transactional
  public MergeFileResponse mergeFiles(final List<MultipartFile> files, final String fileName) {
    FileDocument document = new FileDocument();
    document.setName(fileName);
    document.setStatus(EFileStatus.PENDING);

    FileDocument documentSaved = fileDocumentService.saveFileDocument(document);

    final ArrayList<String> filePaths = new ArrayList<>();

    files.forEach(
        file -> {
          log.info(
              "[MergeService] - Uploading file - Name: {}, Size: {}",
              file.getOriginalFilename(),
              file.getSize());

          ByteArrayResource byteArrayResource = null;
          try {
            byteArrayResource = getByteArrayResource(file);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }

          UploadFileResponse uploadFileResponse =
              storageClient.uploadFile(
                  byteArrayResource, MERGE_PATH + "/" + documentSaved.getId(), true);

          filePaths.add(uploadFileResponse.fileName());
          log.info(
              "[MergeService] - File uploaded - Name: {}, Path: {}",
              file.getOriginalFilename(),
              uploadFileResponse.fileName());
        });

    log.info("[MergeService] - Merging files - Name: {}, Path: {}", fileName, files.size());
    return fileDocumentMapper.toMergeFileResponse(documentSaved);
  }

  private static ByteArrayResource getByteArrayResource(MultipartFile file) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try (InputStream inputStream = file.getInputStream()) {
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }
    }

    return new ByteArrayResource(outputStream.toByteArray()) {
      @Override
      public String getFilename() {
        return file.getOriginalFilename();
      }
    };
  }
}
