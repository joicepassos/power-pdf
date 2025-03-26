package com.jfp.files.processor.dto;

import java.util.List;

public record MergeBatchRequest(String fileName, List<String> files) {}
