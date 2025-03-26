package com.jfp.files.pdf.service;

import com.jfp.files.pdf.dto.MergeFileResponse;
import java.util.List;

public interface MergeService {

  MergeFileResponse mergeFiles(List<String> files, String resultFileName);
}
