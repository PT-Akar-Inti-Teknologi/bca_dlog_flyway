CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-----------------
-- START FLYWAY --
-----------------
CREATE TABLE IF NOT EXISTS flyway_schema_history (
	installed_rank int4 NOT NULL,
	"version" varchar(50) NULL,
	description varchar(200) NOT NULL,
	"type" varchar(20) NOT NULL,
	script varchar(1000) NOT NULL,
	checksum int4 NULL,
	installed_by varchar(100) NOT NULL,
	installed_on timestamp NOT NULL DEFAULT now(),
	execution_time int4 NOT NULL,
	success bool NOT NULL,
	CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);

-----------------
-- CACHE TABLE --
-----------------
CREATE TABLE IF NOT EXISTS public.idm_application (
	application_id varchar(50) NOT NULL,
	name varchar(255) NULL,
	description varchar(255) NULL,
	enabled bool NOT NULL DEFAULT 'true',
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT application_pkey PRIMARY KEY (application_id)
);

CREATE TABLE IF NOT EXISTS idm_menu (
	menu_id varchar(10) NOT NULL,
	application_id varchar(255) NOT NULL,
	sub_category varchar(255) NULL,
	name varchar(255) NULL,
	desktop bool NULL,
	mobile bool NULL,
	type_intra bool NULL,
	type_internet bool NULL,
	parent bool NULL,
	menu_parent_id varchar(10) NULL,
	endpoint varchar(200) NULL,
	can_create bool NULL,
	can_read bool NULL,
	can_update bool NULL,
	can_delete bool NULL,
	private bool NOT NULL DEFAULT 'false',
	enabled bool NOT NULL DEFAULT 'true',
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT idm_menu_pkey PRIMARY KEY (menu_id)
);

CREATE TABLE IF NOT EXISTS idm_application_role (
	application_role_id varchar(50) NOT NULL,
	application_id varchar(50) NOT NULL,
	name varchar(255) NULL,
	description varchar(255) NULL,
	role_sap bool NULL,
	enabled bool NOT NULL DEFAULT 'true',
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT idm_application_role_pkey PRIMARY KEY (application_role_id)
);

CREATE TABLE IF NOT EXISTS public.idm_composite_role (
	composite_role_id varchar(50) NOT NULL,
	name varchar(255) NULL,
	description varchar(255) NULL,
	enabled bool NOT NULL DEFAULT 'true',
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT composite_role_pkey PRIMARY KEY (composite_role_id)
);

CREATE TABLE IF NOT EXISTS public.idm_assosiate_role (
	composite_role_id varchar(50) NOT NULL,
	composite_role_name varchar(255) NOT NULL,
	application_role_id varchar(50) NOT NULL,
	application_role_name varchar(255) NOT NULL,
	enabled bool NOT NULL DEFAULT 'true',
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT assosiate_pkey PRIMARY KEY (composite_role_id, application_role_id),
    CONSTRAINT fk_assosiate_composite_role FOREIGN KEY (composite_role_id) REFERENCES public.idm_composite_role(composite_role_id),
    CONSTRAINT fk_assosiate_application_role FOREIGN KEY (application_role_id) REFERENCES public.idm_application_role(application_role_id)
);

CREATE TABLE IF NOT EXISTS idm_access_role_mapping (
	access_id varchar(50) NOT NULL,
	menu_id varchar(10) NOT NULL,
	application_role_id varchar(50) NOT NULL,
	application_role_name varchar(255) NOT NULL,
	application_id varchar(50) NOT NULL,
	can_create bool NULL,
	can_read bool NULL,
	can_update bool NULL,
	can_delete bool NULL,
	version int4 NULL DEFAULT 0,
	enabled bool NOT NULL DEFAULT 'true',
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT idm_menu_role_mapping_pkey PRIMARY KEY (access_id),
    CONSTRAINT fk_idm_menu_mapping FOREIGN KEY (menu_id) REFERENCES idm_menu(menu_id),
    CONSTRAINT fk_idm_role_mapping FOREIGN KEY (application_role_id) REFERENCES idm_application_role(application_role_id)
);

CREATE TABLE IF NOT EXISTS idm_users (
	user_id varchar(50) NOT NULL,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	internal bool NULL,
	"external" bool NULL,
	enabled bool NOT NULL DEFAULT 'true',
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT idm_users_pkey PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS public.idm_user_role_mapping (
	user_id varchar(50) NOT NULL,
	composite_role_id varchar(50) NOT NULL,
	start_date date NULL,
	end_date date NULL,
	enabled bool NOT NULL DEFAULT 'true',
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT user_mapping_role_pkey PRIMARY KEY (composite_role_id, user_id),
    CONSTRAINT fk_user_mapping_user FOREIGN KEY (user_id) REFERENCES public.idm_users(user_id),
    CONSTRAINT fk_user_mapping_composite_role FOREIGN KEY (composite_role_id) REFERENCES public.idm_composite_role(composite_role_id)
);

CREATE TABLE IF NOT EXISTS idm_users_internal (
	user_id varchar(50) NOT NULL,
	pin varchar(100) NULL,
	sub_department_code varchar(50) NULL,
	sub_department_name varchar(50) NULL,
	phone_number varchar(20) NULL,
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT idm_users_internal_pkey PRIMARY KEY (user_id),
    CONSTRAINT fk_idm_user_internal FOREIGN KEY (user_id) REFERENCES idm_users(user_id)
);

CREATE TABLE IF NOT EXISTS idm_users_external (
	user_id varchar(50) NOT NULL,
	is_employees bool NOT NULL,
	application_id varchar(255) NOT NULL,
	birth_date date NOT NULL,
	phone_number varchar(20) NULL,
	company_code varchar(50) NULL,
	company_name varchar(50) NULL,
	version int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT idm_users_external_pkey PRIMARY KEY (user_id),
    CONSTRAINT fk_idm_user_external FOREIGN KEY (user_id) REFERENCES idm_users(user_id)
);

-----------------
-- KAFKA TABLE --
-----------------
CREATE TABLE IF NOT EXISTS kafka_log (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	key varchar(255) NOT NULL,
	topic varchar(100) NOT NULL,
	"event" varchar(7) NOT NULL,
	"action" varchar(50) NULL,
	class_consumer varchar(50) NULL,
	description varchar(100) NULL,
	is_resolved bool NOT NULL,
	payload text NOT NULL,
	log text NULL,
	CONSTRAINT kafka_log_pkey PRIMARY KEY (id)
);
