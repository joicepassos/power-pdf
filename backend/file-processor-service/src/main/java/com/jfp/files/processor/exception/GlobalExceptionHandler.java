package com.jfp.files.processor.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<ErrorResponse> handleInternalServerError(Exception ex, WebRequest request) {
    log.error("InternalServerError", ex);
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
      Exception ex, WebRequest request) {
    return ResponseEntity.status(BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(BAD_REQUEST.value(), ex.getMessage()));
  }

  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler({UnauthorizedException.class})
  public ResponseEntity<ErrorResponse> handleUnauthorizedException(
      Exception ex, WebRequest request) {
    return ResponseEntity.status(UNAUTHORIZED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(UNAUTHORIZED.value(), ex.getMessage()));
  }

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
      Exception ex, WebRequest request) {
    return ResponseEntity.status(NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(NOT_FOUND.value(), ex.getMessage()));
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      Exception ex, WebRequest request) {
    return ResponseEntity.status(BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(BAD_REQUEST.value(), ex.getMessage()));
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler({PdfProcessingException.class})
  public ResponseEntity<ErrorResponse> handlePdfProcessingException(
      Exception ex, WebRequest request) {
    return ResponseEntity.status(BAD_REQUEST)
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ErrorResponse(BAD_REQUEST.value(), ex.getMessage()));
  }
}
