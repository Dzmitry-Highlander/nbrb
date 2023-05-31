DROP TABLE IF EXISTS app.CURRENCY cascade;

CREATE TABLE IF NOT EXISTS app.currency
(
    cur_id bigint NOT NULL,
    cur_code bigint NOT NULL,
    cur_abbreviation text COLLATE pg_catalog."default" NOT NULL,
    cur_name text COLLATE pg_catalog."default" NOT NULL,
    cur_name_bel text COLLATE pg_catalog."default",
    cur_name_eng text COLLATE pg_catalog."default",
    cur_quotname text COLLATE pg_catalog."default",
    cur_quotname_bel text COLLATE pg_catalog."default",
    cur_quotname_eng text COLLATE pg_catalog."default",
    cur_namemulti text COLLATE pg_catalog."default",
    cur_name_belmulti text COLLATE pg_catalog."default",
    cur_name_engmulti text COLLATE pg_catalog."default",
    cur_scale bigint NOT NULL,
    CONSTRAINT currency_pkey PRIMARY KEY (cur_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.currency
    OWNER to root;

