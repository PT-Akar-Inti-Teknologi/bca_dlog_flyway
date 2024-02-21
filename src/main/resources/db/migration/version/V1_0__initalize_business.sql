CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-----------------
-- DATA TABLE --
-----------------
-- implementation query

-----------------
-- AUDIT TABLE --
-----------------
-- implementation query

--START REVISION INFO
CREATE TABLE IF NOT EXISTS revision_info (
	revision_id int4 NULL,
	username varchar NULL,
	"timestamp" int8 NULL,
	CONSTRAINT revision_pkey PRIMARY KEY (revision_id)
);

CREATE TABLE IF NOT EXISTS sync_log(
    id bigserial NOT NULL,
    started_at timestamp NULL,
    finished_at timestamp NULL,
    file_name varchar(255) NULL,
    sync_class varchar(255) NULL,
    target_entity varchar(255) NULL,
    failed_count int default 0,
    CONSTRAINT sync_log_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS sync_error_log(
    id bigserial NOT NULL,
    created_at timestamp NULL,
    sync_log_id bigserial NOT NULL,
    target_entity varchar(100) NULL,
    file_line int NULL,
    file_data text NULL,
    log text NULL,
    CONSTRAINT sync_error_log_pkey PRIMARY KEY (id),
    CONSTRAINT fk_sync_error_log FOREIGN KEY (sync_log_id) REFERENCES sync_log(id)
);

-- `Workflow History ` --
CREATE TABLE IF NOT EXISTS workflow_history(
    id                      BIGSERIAL NOT NULL,
    transaction_type        VARCHAR(100) NULL,
    transaction_id          VARCHAR(100) NULL,
    status                  VARCHAR(50) NULL,
    note                    TEXT NOT NULL,
    version                 int4 NULL DEFAULT 0,
    created_at              TIMESTAMP NULL,
    created_by              VARCHAR(30) NULL,
    modified_at             TIMESTAMP NULL,
    modified_by             VARCHAR(30) NULL,
    CONSTRAINT workflow_history_pkey PRIMARY KEY (id)
);