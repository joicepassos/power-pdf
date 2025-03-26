package com.jfp.files.processor.exception;

public class PdfProcessingException extends RuntimeException {
  public PdfProcessingException(String message, Exception e) {
    super(message);
  }
}
