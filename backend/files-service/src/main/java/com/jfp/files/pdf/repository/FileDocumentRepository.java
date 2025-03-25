package com.jfp.files.pdf.repository;

import com.jfp.files.pdf.entity.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDocumentRepository extends JpaRepository<FileDocument, Integer> {}
