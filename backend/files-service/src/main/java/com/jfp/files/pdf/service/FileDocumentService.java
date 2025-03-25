package com.jfp.files.pdf.service;

import com.jfp.files.pdf.entity.FileDocument;
import java.util.List;

public interface FileDocumentService {

  FileDocument saveFileDocument(FileDocument fileDocument);

  List<FileDocument> getAllWithPagination(int page, int size);
}
