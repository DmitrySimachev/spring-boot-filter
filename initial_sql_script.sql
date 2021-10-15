create database filterdb;

\c postrgres;

CREATE TABLE public.tag
(
    id integer NOT NULL,
    tag text COLLATE pg_catalog."default",
    CONSTRAINT tag_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.tag
    OWNER to postgres;