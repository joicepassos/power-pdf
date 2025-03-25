USE power_files;

CREATE TABLE IF NOT EXISTS document_status_history (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  file_document_id BIGINT NOT NULL,
  status VARCHAR(50) NOT NULL,
  changed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (file_document_id) REFERENCES file_document(id)
);

CREATE INDEX IF NOT EXISTS idx_status_history ON document_status_history(file_document_id);