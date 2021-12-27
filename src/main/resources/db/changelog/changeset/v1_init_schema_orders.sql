CREATE TABLE IF NOT EXISTS orders
(
    id bigint GENERATED ALWAYS AS IDENTITY,
    number character varying(255),
    name character varying(255),
    amount bigint,
    status character varying(255),
    user_id bigint,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT order_owner FOREIGN KEY (user_id) REFERENCES users (id)
)