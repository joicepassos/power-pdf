package com.jfp.files.pdf.dto.request;

import java.util.List;

public record CreateMergeRequest(String fileName, List<String> files) {}
