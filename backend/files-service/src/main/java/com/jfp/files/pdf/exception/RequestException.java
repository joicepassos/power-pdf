package com.jfp.files.pdf.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public class RequestException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;
  private final int statusCode;
  private final String body;

  public RequestException(int statusCode, String msg) {
    super(msg);
    this.statusCode = statusCode;
    this.body = null;
  }

  public RequestException(final int statusCode, final String msg, final String body) {
    super(msg);
    this.statusCode = statusCode;
    this.body = body;
  }
}
