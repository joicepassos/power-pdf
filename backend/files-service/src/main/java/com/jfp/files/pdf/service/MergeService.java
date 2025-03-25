package com.jfp.files.pdf.service;

import com.jfp.files.pdf.dto.response.MergeFileResponse;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface MergeService {

  MergeFileResponse mergeFiles(List<MultipartFile> files, String resultFileName);
}
