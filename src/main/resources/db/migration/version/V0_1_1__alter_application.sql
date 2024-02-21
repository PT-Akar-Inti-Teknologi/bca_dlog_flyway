ALTER TABLE IF EXISTS idm_application
    ADD COLUMN IF NOT EXISTS desktop bool NULL,
    ADD COLUMN IF NOT EXISTS mobile bool NULL,
    ADD COLUMN IF NOT EXISTS type_intra bool NULL,
    ADD COLUMN IF NOT EXISTS type_internet bool NULL;