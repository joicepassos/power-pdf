package com.jfp.files.pdf.controller;

import static com.jfp.files.pdf.util.ApiUtil.V1_MERGE_API;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.jfp.files.pdf.dto.response.MergeFileResponse;
import com.jfp.files.pdf.service.MergeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(V1_MERGE_API)
public class MergeController {

  private final MergeService mergeService;

  @PostMapping(
      value = "/merge",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<MergeFileResponse> mergeBath(
      @RequestParam("files") final List<MultipartFile> files,
      @RequestParam("fileName") @Valid @NotNull final String fileName) {

    log.info("[MergeController] - Merging files - Name: {}, Path: {}", fileName, files.size());

    MergeFileResponse fileUrl = mergeService.mergeFiles(files, fileName);

    return ResponseEntity.status(CREATED).body(fileUrl);
  }
}
