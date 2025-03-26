USE power_files;

CREATE TABLE IF NOT EXISTS file_process_status_history (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  file_process_id BIGINT NOT NULL,
  status VARCHAR(50) NOT NULL,
  changed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (file_process_id) REFERENCES file_process(id)
);

CREATE INDEX IF NOT EXISTS idx_status_history ON file_process_status_history(file_process_id);