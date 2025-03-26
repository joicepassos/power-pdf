package com.jfp.files.pdf.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record PdfMergeMessage(
    String messageId, String fileName, Integer fileDocumentId, List<String> files)
    implements Serializable {

  @Serial private static final long serialVersionUID = 1L;
}
