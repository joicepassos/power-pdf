package com.jfp.files.processor.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import com.jfp.files.processor.client.StorageClient;
import com.jfp.files.processor.client.dto.UploadFileResponse;
import com.jfp.files.processor.exception.PdfProcessingException;
import com.jfp.files.processor.service.PdfService;
import java.io.FileOutputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

  private final StorageClient storageClient;

  @Override
  public String mergePdfFiles(List<String> files, String resultFileName) {
    log.info("[FileProcessor] Merging PDF files into {}", resultFileName);

    try {
      Document document = new Document();
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(resultFileName));
      document.open();

      for (String file : files) {
        byte[] pdfBytes = storageClient.downloadFile(file);
        PdfReader pdfReader = new PdfReader(pdfBytes);

        for (int page = 1; page <= pdfReader.getNumberOfPages(); page++) {
          document.newPage();
          PdfContentByte content = writer.getDirectContent();
          PdfImportedPage importedPage = writer.getImportedPage(pdfReader, page);
          content.addTemplate(importedPage, 0, 0);
        }

        pdfReader.close();
      }

      document.close();
      writer.close();

      log.info("[FileProcessor] PDF files merged successfully");

      UploadFileResponse uploadFileResponse =
          storageClient.uploadFile(document.toString().getBytes(), resultFileName);
      log.info(
          "[FileProcessor] PDF file uploaded successfully - URL: {}",
          uploadFileResponse.fileName());

      return uploadFileResponse.fileName();
    } catch (Exception e) {
      log.error("[FileProcessor] Error on merge PDF files", e);
      throw new PdfProcessingException("Error on merge PDF files");
    }
  }
}
