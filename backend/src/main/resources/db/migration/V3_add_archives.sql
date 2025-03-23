USE powerpdf;

CREATE TABLE pdf_file (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    pdf_document_id BIGINT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(100) NOT NULL,
    size BIGINT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pdf_document_id) REFERENCES pdf_document(id)
);

CREATE INDEX idx_pdf_file_document_id ON pdf_file(pdf_document_id);