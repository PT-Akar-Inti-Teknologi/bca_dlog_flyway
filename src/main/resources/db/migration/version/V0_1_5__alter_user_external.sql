ALTER TABLE IF EXISTS idm_users_external
    ADD COLUMN IF NOT EXISTS nik varchar(16) NULL,
    ADD COLUMN IF NOT EXISTS vendor_code varchar(20) NULL;

ALTER TABLE IF EXISTS idm_users_external_aud
    ADD COLUMN IF NOT EXISTS nik varchar(16) NULL,
    ADD COLUMN IF NOT EXISTS vendor_code varchar(20) NULL;
