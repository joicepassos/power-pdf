package com.jfp.files.pdf.service;

import com.jfp.files.pdf.entity.FileProcess;
import com.jfp.files.pdf.util.EFileStatus;
import java.util.List;

public interface FileProcessService {

  FileProcess save(FileProcess fileProcess);

  List<FileProcess> findAll();

  void updateLinkAndStatus(String link, EFileStatus status, Integer id);
}
