CREATE TABLE IF NOT EXISTS public."user"
(
    userid integer NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (userid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."user"
    OWNER to postgres;

GRANT ALL ON TABLE public."user" TO postgres WITH GRANT OPTION;	


CREATE SEQUENCE user_id_seq
INCREMENT 1
START 1;

ALTER SEQUENCE public.user_id_seq OWNED BY public.user.userid;

CREATE TABLE IF NOT EXISTS public.subscription
(
    subscriptionid integer NOT NULL,
    userid integer NOT NULL,
    subscription character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT subscription_pkey PRIMARY KEY (subscriptionid),
    CONSTRAINT subscription_userid_fkey FOREIGN KEY (userid)
        REFERENCES public."user" (userid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.subscription
    OWNER to postgres;

GRANT ALL ON TABLE public.subscription TO postgres WITH GRANT OPTION;

CREATE SEQUENCE subscription_id_seq
INCREMENT 1
START 1;

ALTER SEQUENCE public.subscription_id_seq OWNED BY public.subscription.subscriptionid;
