USE power_files;

-- Insert data into file_process table
INSERT INTO file_process (name, link, type_process, type_file, status)
VALUES
  ('file1.pdf', 'merges/pdfs/file1.pdf', 'MERGE', 'PDF', 'PENDING'),
  ('file2.pdf', 'merges/pdfs/file2.pdf', 'MERGE', 'PDF', 'COMPLETED'),
  ('file3.pdf', 'merges/pdfs/file3.pdf', 'MERGE', 'PDF', 'FAILED'),
  ('file4.pdf', 'merges/pdfs/file4.pdf', 'MERGE', 'PDF', 'COMPLETED'),
  ('file5.pdf', 'merges/pdfs/file5.pdf', 'MERGE', 'PDF', 'COMPLETED'),
  ('file6.pdf', 'merges/pdfs/file6.pdf', 'MERGE', 'PDF', 'COMPLETED'),
  ('file7.pdf', 'merges/pdfs/file7.pdf', 'MERGE', 'PDF', 'COMPLETED'),
  ('file8.pdf', 'merges/pdfs/file8.pdf', 'MERGE', 'PDF', 'COMPLETED'),
  ('file9.pdf', 'merges/pdfs/file9.pdf', 'MERGE', 'PDF', 'COMPLETED'),
  ('file10.pdf', 'merges/pdfs/file10.pdf', 'MERGE', 'PDF', 'COMPLETED');

-- Insert data into file_process_status_history table
INSERT INTO file_process_status_history (file_process_id, status, changed_at)
VALUES
  (1, 'PENDING', NOW()),
  (2, 'COMPLETED', NOW()),
  (3, 'FAILED', NOW()),
  (4, 'COMPLETED', NOW()),
  (5, 'COMPLETED', NOW()),
  (6, 'COMPLETED', NOW()),
  (7, 'COMPLETED', NOW()),
  (8, 'COMPLETED', NOW()),
  (9, 'COMPLETED', NOW()),
  (10, 'COMPLETED', NOW());