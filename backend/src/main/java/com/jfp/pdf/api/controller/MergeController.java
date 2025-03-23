package com.jfp.pdf.api.controller;

import static com.jfp.pdf.api.util.ApiUtil.V1_MERGE_API;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.jfp.pdf.api.service.MergeService;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(V1_MERGE_API)
@RequiredArgsConstructor
public class MergeController {

  private final MergeService mergeService;

  @PostMapping()
  public void merge() {}

  @Observed(name = "Merge PDF")
  @PostMapping(
          produces = APPLICATION_JSON_VALUE,
          consumes = MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> mergeBathPdf(
      @RequestParam("files") final List<MultipartFile> files,
      @RequestPart("fileName") @NotNull final String fileName) {

    log.info("[API - PDFs] - Merging {} pdfs - Filename after merge: {}", files.size(), fileName);

    return ResponseEntity.ok().build();
  }
}
