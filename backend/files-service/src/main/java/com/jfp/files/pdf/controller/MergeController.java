package com.jfp.files.pdf.controller;

import static com.jfp.files.pdf.util.ApiUtil.V1_MERGE_API;
import static org.springframework.http.HttpStatus.CREATED;

import com.jfp.files.pdf.dto.MergeFileResponse;
import com.jfp.files.pdf.dto.request.CreateMergeRequest;
import com.jfp.files.pdf.service.MergeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(V1_MERGE_API)
public class MergeController {

  private final MergeService mergeService;

  @PostMapping
  public ResponseEntity<MergeFileResponse> createNewMerge(
      @RequestBody @Valid @NotNull CreateMergeRequest request) {

    log.info(
        "[MergeController] - Creating new merge with fileName: {} and {} files",
        request.fileName(),
        request.files().size());

    MergeFileResponse mergeFileResponse =
        mergeService.mergeFiles(request.files(), request.fileName());

    log.info("[MergeController] - Merge created successfully at {}", mergeFileResponse.link());

    return ResponseEntity.status(CREATED).body(mergeFileResponse);
  }
}
