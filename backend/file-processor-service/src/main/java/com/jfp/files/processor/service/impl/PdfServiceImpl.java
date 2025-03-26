package com.jfp.files.processor.service.impl;

import com.jfp.files.processor.client.StorageClient;
import com.jfp.files.processor.client.dto.UploadFileResponse;
import com.jfp.files.processor.exception.PdfProcessingException;
import com.jfp.files.processor.service.PdfService;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

  private final StorageClient storageClient;

  @Override
  public String mergePdfFiles(List<String> files, String resultFileName) {
    log.info("[FileProcessor] Merging PDF files into {}", resultFileName);

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      PDFMergerUtility pdfMerger = new PDFMergerUtility();
      pdfMerger.setDestinationStream(outputStream);

      for (String fileUrl : files) {
        try (InputStream inputStream = downloadFromUrl(fileUrl);
            PDDocument document = PDDocument.load(inputStream)) {
          pdfMerger.addSource(document.toString());
        }
      }

      pdfMerger.mergeDocuments(null);
      byte[] mergedPdfBytes = outputStream.toByteArray();
      log.info("[FileProcessor] PDF files merged successfully");

      UploadFileResponse uploadFileResponse =
          storageClient.uploadFile(mergedPdfBytes, resultFileName);
      log.info(
          "[FileProcessor] PDF file uploaded successfully - URL: {}",
          uploadFileResponse.fileName());

      return uploadFileResponse.fileName();
    } catch (Exception e) {
      log.error("[FileProcessor] Error merging PDF files", e);
      throw new PdfProcessingException("Error merging PDF files", e);
    }
  }

  private InputStream downloadFromUrl(String fileUrl) {
    try {
      URI uri = URI.create(fileUrl);
      URL url = uri.toURL();
      URLConnection connection = url.openConnection();
      return connection.getInputStream();
    } catch (Exception e) {
      log.error("Error downloading file from {}", fileUrl, e);
      throw new RuntimeException("Failed to download file: " + fileUrl, e);
    }
  }
}
