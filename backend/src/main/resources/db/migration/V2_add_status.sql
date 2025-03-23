USE powerpdf;

ALTER TABLE pdf_document
ADD COLUMN status_history TEXT;

CREATE TABLE pdf_status_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    pdf_document_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    changed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pdf_document_id) REFERENCES pdf_document(id)
);

CREATE INDEX idx_status_history ON pdf_status_history(pdf_document_id);