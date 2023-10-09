CREATE TABLE t_authority (
    id serial NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT t_authority__f_id__pk PRIMARY KEY (id),
    CONSTRAINT t_authority__f_name__unique UNIQUE (name)
);