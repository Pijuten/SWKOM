CREATE SCHEMA IF NOT EXISTS paperless_schema;
SET search_path TO paperless_schema;

-- Create the documents table
CREATE TABLE IF NOT EXISTS documents (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    description TEXT,
    uploaded_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
