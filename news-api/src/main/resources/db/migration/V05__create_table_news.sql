CREATE TABLE t_news (
    id bigserial NOT NULL,
    title varchar(150) NOT NULL,
    content text NOT NULL,
    tag varchar(100) NOT NULL,
    verified boolean NOT NULL,
    source varchar NULL,
    created_at timestamp NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT t_news__f_id__pk PRIMARY KEY (id),
    CONSTRAINT t_news__f_user_id__fk FOREIGN KEY (user_id) REFERENCES t_user(id)
);