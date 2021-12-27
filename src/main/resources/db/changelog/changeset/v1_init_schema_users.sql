CREATE TABLE IF NOT EXISTS users
(
    id bigint GENERATED ALWAYS AS IDENTITY,
    name character varying(255),
    email character varying(255),
    password character varying(20),
    CONSTRAINT user_pkey PRIMARY KEY (id)
)