CREATE TABLE IF NOT EXISTS masterdata.kombinasi_coa (
    coa_glm_code varchar(10) NOT NULL,
    branch_code varchar(5) NOT NULL,
    rcc varchar(5) NOT NULL,
    product_code varchar(5) NOT NULL,
    valid_from date NOT NULL,
    valid_to date NOT NULL,
    "version" int4 NULL DEFAULT 0,
    created_at timestamp NULL,
    created_by varchar(30) NULL,
    modified_at timestamp NULL,
    modified_by varchar(30) NULL,
    CONSTRAINT kombinasi_coa_pk PRIMARY KEY (coa_glm_code, branch_code, rcc, product_code)
);