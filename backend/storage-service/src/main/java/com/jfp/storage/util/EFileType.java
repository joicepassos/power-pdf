package com.jfp.storage.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EFileType {
  PDF("pdf");

  private final String value;
}
