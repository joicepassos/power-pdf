package com.jfp.storage.controller;

import static com.jfp.storage.util.ApiUtil.V1_FILES;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.jfp.storage.dto.UploadFileResponse;
import com.jfp.storage.service.FileStorageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(V1_FILES)
@RequiredArgsConstructor
public class FileStorageController {

  private final FileStorageService fileStorageService;

  @PostMapping(
      value = "/upload",
      produces = APPLICATION_JSON_VALUE,
      consumes = MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<UploadFileResponse> uploadFile(
      @RequestParam("file") final MultipartFile file,
      @RequestParam("temporary") final Boolean temporary,
      @RequestParam("filePath") @Valid @NotNull final String filePath) {

    log.info(
        "[FileStorageController] - Uploading file - Name: {}, Path: {}, Type: {}",
        file.getOriginalFilename(),
        file.getContentType(),
        file.getSize());

    UploadFileResponse fileUrl = fileStorageService.uploadFile(file, filePath, temporary);

    return ResponseEntity.ok(fileUrl);
  }
}
