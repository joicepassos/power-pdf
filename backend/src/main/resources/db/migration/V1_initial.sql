CREATE DATABASE IF NOT EXISTS powerpdf;

USE powerpdf;

CREATE TABLE pdf_document (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    link VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING'
);

CREATE INDEX idx_pdf_document_status ON pdf_document(status);