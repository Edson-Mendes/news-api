CREATE TABLE t_user_authorities (
	user_id int8 NOT NULL,
	authority_id int4 NOT NULL,
	CONSTRAINT t_user_authorities__user_id__fk FOREIGN KEY (user_id) REFERENCES t_user(id),
	CONSTRAINT t_user_authorities__authority_id__fk FOREIGN KEY (authority_id) REFERENCES t_authority(id)
)