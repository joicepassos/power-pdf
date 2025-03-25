package com.jfp.files.processor.controller;

import static com.jfp.files.processor.util.ApiUtil.V1_PDF;

import com.jfp.files.processor.dto.MergeBatchRequest;
import com.jfp.files.processor.service.PdfService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(V1_PDF)
@RequiredArgsConstructor
public class PdfController {

  private final PdfService pdfService;

  @PostMapping(value = "/merge")
  public ResponseEntity<String> mergeBath(
      @RequestBody final @Valid  MergeBatchRequest mergeBatchRequest) {

    log.info("[PdfController] - Merging mergeBatchRequest - Name: {}", mergeBatchRequest.fileName());

    String fileUrl = pdfService.mergePdfFiles(mergeBatchRequest.files(), mergeBatchRequest.fileName());

    return ResponseEntity.ok(fileUrl);
  }
}
