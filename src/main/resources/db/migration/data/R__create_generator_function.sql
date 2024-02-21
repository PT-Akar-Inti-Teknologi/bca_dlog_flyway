-- FUNCTION EXAMPLE
--CREATE OR REPLACE FUNCTION generate_application(application_id varchar,
--                                                name varchar,
--                                                deskripsi varchar,
--                                                enabled bool)
--RETURNS VOID AS $$
--	#variable_conflict use_variable
--	BEGIN
--	INSERT INTO application(application_id,
--                            name,
--                            deskripsi,
--                            enabled,
--                            created_at,
--                            created_by,
--                            modified_at,
--                            modified_by)
--		SELECT
--			application_id, name, deskripsi, enabled,  now(), 'system', now(), 'system'
--				WHERE NOT EXISTS (SELECT x.application_id FROM application x WHERE x.application_id = application_id);
--	END;
--$$ LANGUAGE plpgsql;

-- PRODUCE STATUS ASSET
CREATE OR REPLACE PROCEDURE public.sp_upsert_status_asset(param_code varchar,
                                                            param_description varchar)

LANGUAGE plpgsql
AS $procedure$
    #variable_conflict use_variable
BEGIN
    INSERT INTO status_asset(kode,
                               deskripsi,
                               version,
                               created_at,
                               created_by,
                               modified_at,
                               modified_by)
    values (param_code,
            param_description,
            '0',
            now(),
            'system',
            now(),
            'system')
    ON CONFLICT (kode)
        DO UPDATE SET
                      deskripsi = param_description,
                      "version" = (SELECT "version" FROM status_asset WHERE kode = param_code) + 1,
                      modified_at = now();
END;
$procedure$;

CREATE OR REPLACE PROCEDURE public.sp_upsert_dati(param_code varchar,
                                                  param_description varchar)

LANGUAGE plpgsql
AS $procedure$
BEGIN
    INSERT INTO kode_dati(kode,
                     deskripsi,
                     version,
                     created_at,
                     created_by,
                     modified_at,
                     modified_by)
    VALUES (param_code,
            param_description,
            '0',
            now(),
            'system',
            now(),
            'system')
    ON CONFLICT (kode)
        DO UPDATE SET
                      deskripsi = param_description,
                      "version" = (SELECT "version" FROM kode_dati WHERE kode = param_code) + 1,
                      modified_at = now();
END;
$procedure$;

CREATE OR REPLACE PROCEDURE public.sp_upsert_quality_code(param_code varchar,
                                                  param_description varchar)

LANGUAGE plpgsql
AS $procedure$
BEGIN
    INSERT INTO kode_kualitas(kode,
                     deskripsi,
                     version,
                     created_at,
                     created_by,
                     modified_at,
                     modified_by)
    VALUES (param_code,
            param_description,
            '0',
            now(),
            'system',
            now(),
            'system')
    ON CONFLICT (kode)
        DO UPDATE SET
                      deskripsi = param_description,
                      "version" = (SELECT "version" FROM kode_kualitas WHERE kode = param_code) + 1,
                      modified_at = now();
END;
$procedure$;

CREATE OR REPLACE PROCEDURE public.sp_upsert_type_software(param_code varchar,
                                                          param_description varchar)

LANGUAGE plpgsql
AS $procedure$
BEGIN
    INSERT INTO tipe_software(kode,
                             deskripsi,
                             version,
                             created_at,
                             created_by,
                             modified_at,
                             modified_by)
    VALUES (param_code,
            param_description,
            '0',
            now(),
            'system',
            now(),
            'system')
    ON CONFLICT (kode)
        DO UPDATE SET
                      deskripsi = param_description,
                      "version" = (SELECT "version" FROM tipe_software WHERE kode = param_code) + 1,
                      modified_at = now();
END;
$procedure$;

CREATE OR REPLACE PROCEDURE public.sp_upsert_type_at(param_code varchar,
                                                           param_description varchar)

    LANGUAGE plpgsql
AS $procedure$
BEGIN
    INSERT INTO tipe_at(kode,
                              deskripsi,
                              version,
                              created_at,
                              created_by,
                              modified_at,
                              modified_by)
    VALUES (param_code,
            param_description,
            '0',
            now(),
            'system',
            now(),
            'system')
    ON CONFLICT (kode)
        DO UPDATE SET
                      deskripsi = param_description,
                      "version" = (SELECT "version" FROM tipe_at WHERE kode = param_code) + 1,
                      modified_at = now();
END;
$procedure$;

