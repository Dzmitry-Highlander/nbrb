

CREATE TABLE IF NOT EXISTS app.currency
(
    cur_id bigint NOT NULL,
    cur_code bigint NOT NULL,
    cur_abbreviation text COLLATE pg_catalog."default" NOT NULL,
    cur_name text COLLATE pg_catalog."default" NOT NULL,
    cur_scale bigint NOT NULL,
    CONSTRAINT currency_pkey PRIMARY KEY (cur_id)
)

    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS app.cur_lang
(
    cur_lang_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    cur_id bigint NOT NULL,
    cur_name_bel text COLLATE pg_catalog."default",
    cur_name_eng text COLLATE pg_catalog."default",
    cur_quotname text COLLATE pg_catalog."default",
    cur_quotname_bel text COLLATE pg_catalog."default",
    cur_quotname_eng text COLLATE pg_catalog."default",
    cur_namemulti text COLLATE pg_catalog."default",
    cur_name_belmulti text COLLATE pg_catalog."default",
    cur_name_engmulti text COLLATE pg_catalog."default",
    CONSTRAINT cur_lang_pkey PRIMARY KEY (cur_lang_id),
    CONSTRAINT cur_lang_cur_id_fkey FOREIGN KEY (cur_id)
        REFERENCES app.currency (cur_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS app.cur_expdate
(
    cur_expdate_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    cur_id bigint NOT NULL,
    cur_date_start timestamp without time zone,
    cur_date_end timestamp without time zone,
    CONSTRAINT cur_expdate_pkey PRIMARY KEY (cur_expdate_id),
    CONSTRAINT cur_expdate_cur_id_fkey FOREIGN KEY (cur_id)
        REFERENCES app.currency (cur_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS app.rate
(
    rate_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    cur_id bigint NOT NULL,
    cur_date timestamp(6) without time zone,
    cur_official_rate numeric(10, 4),
    PRIMARY KEY (rate_id),
    FOREIGN KEY (cur_id)
        REFERENCES app.currency (cur_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

DROP TABLE IF EXISTS app.WEEKENDS cascade;

CREATE TABLE app.WEEKENDS (
                              CALENDAR_DATE DATE NULL,
                              IS_DAY_OFF INTEGER  NULL,
                              CONSTRAINT PK_WEEKEND12345687 PRIMARY KEY (CALENDAR_DATE)
);