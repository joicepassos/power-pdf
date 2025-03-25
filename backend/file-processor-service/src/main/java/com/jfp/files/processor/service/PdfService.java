package com.jfp.files.processor.service;

import java.util.List;

public interface PdfService {

  String mergePdfFiles(List<String> files, String resultFileName);
}
