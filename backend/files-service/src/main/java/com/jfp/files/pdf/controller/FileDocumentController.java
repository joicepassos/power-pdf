package com.jfp.files.pdf.controller;

import static com.jfp.files.pdf.util.ApiUtil.V1_MERGE_API;

import com.jfp.files.pdf.entity.FileDocument;
import com.jfp.files.pdf.service.FileDocumentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(V1_MERGE_API)
@RequiredArgsConstructor
public class FileDocumentController {

  private final FileDocumentService fileDocumentService;

  @GetMapping
  public ResponseEntity<List<FileDocument>> listAllWithPagination(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size) {

    List<FileDocument> fileDocuments = fileDocumentService.getAllWithPagination(page, size);

    return ResponseEntity.ok(fileDocuments);
  }
}
