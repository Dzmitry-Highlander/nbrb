
CREATE TABLE IF NOT EXISTS app.rate
(
    rate_id INT NOT NULL,
    cur_id bigint NOT NULL,
    date timestamp without time zone NOT NULL,
    cur_official_rate numeric NOT NULL,
    CONSTRAINT rate_pkey PRIMARY KEY (rate_id),
    CONSTRAINT rate_cur_id_fkey FOREIGN KEY (cur_id)
    REFERENCES app.currency (cur_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS app.rate
    OWNER to nbrb;

