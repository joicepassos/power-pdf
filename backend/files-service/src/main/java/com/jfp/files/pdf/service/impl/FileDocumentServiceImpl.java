package com.jfp.files.pdf.service.impl;

import com.jfp.files.pdf.entity.FileDocument;
import com.jfp.files.pdf.repository.FileDocumentRepository;
import com.jfp.files.pdf.service.FileDocumentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileDocumentServiceImpl implements FileDocumentService {

  private final FileDocumentRepository fileDocumentRepository;

  @Override
  @Transactional
  public FileDocument saveFileDocument(FileDocument fileDocument) {
    log.info("[FileDocumentService] - Saving file document - Name: {}", fileDocument.getName());
    return fileDocumentRepository.save(fileDocument);
  }

  @Override
  public List<FileDocument> getAllWithPagination(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
    Page<FileDocument> fileDocumentsPage = fileDocumentRepository.findAll(pageable);
    return fileDocumentsPage.getContent();
  }
}
