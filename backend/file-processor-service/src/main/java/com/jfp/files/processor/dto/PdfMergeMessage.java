package com.jfp.files.processor.dto;

import java.util.List;

public record PdfMergeMessage(
    String messageId, String fileName, Integer fileDocumentId, List<String> files) {}
