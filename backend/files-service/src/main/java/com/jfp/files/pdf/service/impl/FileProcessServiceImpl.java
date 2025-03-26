package com.jfp.files.pdf.service.impl;

import com.jfp.files.pdf.entity.FileProcess;
import com.jfp.files.pdf.exception.ResourceNotFoundException;
import com.jfp.files.pdf.repository.FileProcesstRepository;
import com.jfp.files.pdf.service.FileProcessService;
import com.jfp.files.pdf.util.EFileStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileProcessServiceImpl implements FileProcessService {

  private final FileProcesstRepository fileProcesstRepository;

  @Override
  @Transactional
  public FileProcess save(final FileProcess fileProcess) {
    log.info("[FileDocumentService] - Saving file document - Name: {}", fileProcess.getName());
    return fileProcesstRepository.save(fileProcess);
  }

  @Override
  public List<FileProcess> findAll() {
    log.info("[FileDocumentService] - Listing all file documents");

    return fileProcesstRepository.findAll();
  }

  public FileProcess getById(final Integer id) {
    return fileProcesstRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("File document not found - ID: " + id));
  }

  @Override
  @Transactional
  public void updateLinkAndStatus(final String link, final EFileStatus status, final Integer id) {
    log.info("[FileDocumentService] - Updating file document - ID: {}", id);

    FileProcess fileProcess = getById(id);

    fileProcess.setLink(link);
    fileProcess.setStatus(status);
    fileProcesstRepository.save(fileProcess);

    log.info("[FileDocumentService] - File document updated - ID: {}", id);
  }
}
