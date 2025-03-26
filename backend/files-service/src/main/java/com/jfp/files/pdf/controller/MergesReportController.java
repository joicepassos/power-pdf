package com.jfp.files.pdf.controller;

import static com.jfp.files.pdf.util.ApiUtil.V1_MERGES_REPORT;

import com.jfp.files.pdf.dto.response.FileProcessResponse;
import com.jfp.files.pdf.entity.FileProcess;
import com.jfp.files.pdf.service.FileProcessService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(V1_MERGES_REPORT)
@RequiredArgsConstructor
public class MergesReportController {

  private final FileProcessService fileProcessService;

  @GetMapping
  public ResponseEntity<List<FileProcessResponse>> listAllWithPagination() {
    log.info("[MergesReportController] - Listing all file processes");

    List<FileProcess> fileProcesses = fileProcessService.findAll();

    List<FileProcessResponse> fileProcessResponses =
        fileProcesses.stream()
            .map(
                fileProcess ->
                    new FileProcessResponse(
                        fileProcess.getId(),
                        fileProcess.getName(),
                        fileProcess.getLink(),
                        fileProcess.getCreatedAt().toString(),
                        fileProcess.getStatus()))
            .toList();

    log.info(
        "[MergesReportController] - File processes mapped to response - Count: {}",
        fileProcessResponses.size());

    return ResponseEntity.ok(fileProcessResponses);
  }
}
