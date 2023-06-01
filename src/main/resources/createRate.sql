
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

ALTER TABLE IF EXISTS app.rate
    OWNER to nbrb;

