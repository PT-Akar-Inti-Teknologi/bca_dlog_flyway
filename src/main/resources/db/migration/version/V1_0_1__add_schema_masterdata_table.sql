CREATE SCHEMA IF NOT EXISTS masterdata;
--cost_center
CREATE TABLE IF NOT EXISTS masterdata.cost_center (
	id VARCHAR(100) NOT NULL,
	cost_center_id varchar(100) NOT NULL,
	description varchar(100) NOT NULL,
	cost_center_parent varchar(100) NULL,
	business_number varchar(100) NOT NULL,
	branch_type varchar(10) NULL,
	valid_from date NOT NULL,
	valid_to date NOT NULL,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	"version" int4 NULL DEFAULT 0,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT cost_center_pkey PRIMARY KEY (cost_center_id)
);

-- create table coa
CREATE TABLE IF NOT EXISTS masterdata.coa (
	coa_code varchar(20) NOT NULL,
	description varchar(100) NOT NULL,
	is_slid bool NOT NULL,
	coa_type varchar(100) NOT NULL,
	valid_from date NOT NULL,
	valid_to date NOT NULL,
	is_valas bool NOT NULL,
	"version" int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT coa_pk PRIMARY KEY (coa_code)
);

-- create table sub_coa
CREATE TABLE IF NOT EXISTS masterdata.sub_coa (
	id bigserial NOT NULL,
	coa_code varchar(20) NOT NULL,
	sub_coa_code varchar(4) NOT NULL,
	description varchar(100) NOT NULL,
	is_active bool NULL,
	"version" int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT sub_coa_pk PRIMARY KEY (id),
	CONSTRAINT unique_sub_coa UNIQUE (coa_code, sub_coa_code),
	CONSTRAINT fk_coa FOREIGN KEY (coa_code) REFERENCES masterdata.coa(coa_code)
);

-- create table kode_produk
CREATE TABLE IF NOT EXISTS masterdata.kode_produk (
	product_code varchar(3) NOT NULL,
	description varchar(100) NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	"version" int4 NULL DEFAULT 0,
	created_at timestamp NULL,
	created_by varchar(30) NULL,
	modified_at timestamp NULL,
	modified_by varchar(30) NULL,
	CONSTRAINT kode_produk_pkey PRIMARY KEY (product_code)
);


-- create table asset_class
CREATE TABLE IF NOT EXISTS masterdata.asset_class
(
    asset_class        varchar(4)   not null,
    description        varchar(255) not null,
    sub_asset_wajib_satu_costcenter BOOLEAN not null DEFAULT FALSE,
--    DETAIL LVA
    class_lva          integer       DEFAULT NULL,
    coa_biaya          varchar(20)   DEFAULT NULL,
    sub_coa_biaya      varchar(20)   DEFAULT NULL,
    batas_nilai_lva    numeric(20,2) DEFAULT NULL,
--    DETAIL COA
    coa_asset          varchar(20)  not null,
    sub_coa_asset      varchar(4)   not null,
    coa_akumulasi      varchar(20)  not null,
    sub_coa_akumulasi  varchar(4)   not null,
    coa_laba           varchar(20)  not null,
    sub_coa_laba       varchar(4)   not null,
    coa_rugi           varchar(20)  not null,
    sub_coa_rugi       varchar(4)   not null,
--    Detail Depresiasi
    metode_depresiasi_fiscal        varchar(50) DEFAULT NULL,
    metode_depresiasi_commercial    varchar(50) DEFAULT NULL,
    coa_biaya_depresiasi            varchar(20) DEFAULT NULL,
    sub_coa_biaya_depresiasi        varchar(4)  DEFAULT NULL,
    fiscal_life                     integer     DEFAULT NULL,
    commercial_life                 integer     DEFAULT NULL,
    version int4 NULL DEFAULT 0,
    created_at timestamp NULL,
    created_by varchar(30) NULL,
    modified_at timestamp NULL,
    modified_by varchar(30) NULL,
    CONSTRAINT asset_class_pk PRIMARY KEY (asset_class),
    CONSTRAINT fk_sub_coa_asset FOREIGN KEY (coa_asset, sub_coa_asset) REFERENCES masterdata.sub_coa (coa_code, sub_coa_code),
    CONSTRAINT fk_sub_coa_akumulasi FOREIGN KEY (coa_akumulasi, sub_coa_akumulasi) REFERENCES masterdata.sub_coa (coa_code, sub_coa_code),
    CONSTRAINT fk_sub_coa_laba FOREIGN KEY (coa_laba, sub_coa_laba) REFERENCES masterdata.sub_coa (coa_code, sub_coa_code),
    CONSTRAINT fk_sub_coa_rugi FOREIGN KEY (coa_rugi, sub_coa_rugi) REFERENCES masterdata.sub_coa (coa_code, sub_coa_code)
);
