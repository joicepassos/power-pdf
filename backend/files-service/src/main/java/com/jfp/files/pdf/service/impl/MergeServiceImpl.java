package com.jfp.files.pdf.service.impl;

import com.jfp.files.pdf.dto.MergeFileResponse;
import com.jfp.files.pdf.dto.response.PdfMergeMessage;
import com.jfp.files.pdf.entity.FileProcess;
import com.jfp.files.pdf.mapper.FileDocumentMapper;
import com.jfp.files.pdf.service.FileProcessService;
import com.jfp.files.pdf.service.MergeService;
import com.jfp.files.pdf.util.EFileStatus;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MergeServiceImpl implements MergeService {

  private final FileProcessService fileProcessService;
  private final FileDocumentMapper fileDocumentMapper;
  private final RabbitTemplate rabbitTemplate;

  private static final String MERGE_PATH = "/merges/pdfs";

  @Override
  @Transactional
  public MergeFileResponse mergeFiles(final List<String> files, final String fileName) {
    FileProcess document = createFileProcess(fileName);

    FileProcess documentSaved = fileProcessService.save(document);

    String messageId = UUID.randomUUID().toString();
    String fileNamePath = MERGE_PATH + "/" + fileName;

    sendMergeMessage(messageId, fileNamePath, documentSaved.getId(), files);

    log.info("[MergeService] - Merging files - Name: {}, Path: {}", fileNamePath, files.size());
    return fileDocumentMapper.toMergeFileResponse(documentSaved);
  }

  private FileProcess createFileProcess(String fileName) {
    FileProcess document = new FileProcess();
    document.setName(fileName);
    document.setStatus(EFileStatus.PENDING);
    document.setTypeFile("application/pdf");
    document.setTypeProcess("MERGE");
    return document;
  }

  private void sendMergeMessage(
      String messageId, String fileName, Integer documentId, List<String> files) {
    PdfMergeMessage message = new PdfMergeMessage(messageId, fileName, documentId, files);
    rabbitTemplate.convertAndSend("pdf_merge_exchange", "pdf.merge", message);
  }
}
