CREATE TABLE IF NOT EXISTS kode_dati (
    kode varchar(4) not null,
    deskripsi varchar(255) not null,
    version int4 NULL DEFAULT 0,
    created_at timestamp NULL,
    created_by varchar(30) NULL,
    modified_at timestamp NULL,
    modified_by varchar(30) NULL,
    CONSTRAINT dati_code_pkey PRIMARY KEY (kode)

);