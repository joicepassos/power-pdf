package com.jfp.files.pdf.entity;

import com.jfp.files.pdf.util.EFileStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Builder
@Entity
@Table(name = "file_process_status_history")
public class FileProcessStatusHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "document_id", nullable = false)
  private FileProcess document;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EFileStatus status;

  @CreationTimestamp
  @Column(name = "changed_at", nullable = false)
  private LocalDateTime changedAt;

  @Tolerate
  public FileProcessStatusHistory() {}
}
