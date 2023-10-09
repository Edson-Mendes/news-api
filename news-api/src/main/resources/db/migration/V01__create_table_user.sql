CREATE TABLE t_user (
    id bigserial NOT NULL,
    name varchar(50) NOT NULL,
    email varchar(320) NOT NULL,
    password varchar(255) NOT NULL,
    enabled boolean NOT NULL,
    created_at timestamp NOT NULL,
    CONSTRAINT t_user__f_id__pk PRIMARY KEY (id),
    CONSTRAINT t_user__f_email__unique UNIQUE (email)
);