ALTER TABLE IF EXISTS idm_users_internal
    ADD COLUMN IF NOT EXISTS private_email varchar(255) NULL;

ALTER TABLE IF EXISTS idm_users_internal_aud
    ADD COLUMN IF NOT EXISTS private_email varchar(255) NULL;