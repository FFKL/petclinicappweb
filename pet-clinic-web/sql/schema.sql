/*
-- roles
create table roles (
		uid serial primary key,
		name varchar(200)
);

-- users
create table users (
		uid serial primary key,
		login varchar(200),
		email varchar(200),
		role_id int not null references roles(uid)
);

-- messages
create table messages (
		uid serial primary key,
		text  character varying,
		user_id int not null references users(uid)
);*/
create table clients (
	uid serial primary key,
	name varchar(200)
);
create table pets (
	uid serial primary key,
	name character varying(100),
	type character varying(100),
	client_id int not null references clients(uid)
);
CREATE TABLE pet_type (
	type character varying(100) NOT NULL,
	CONSTRAINT pet_type_pkey PRIMARY KEY (type)
);

ALTER TABLE pets
  ADD CONSTRAINT pet_type_fkey FOREIGN KEY (type)
      REFERENCES pet_type (type) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE pets
  ADD CONSTRAINT pets_client_id_fkey FOREIGN KEY (client_id)
      REFERENCES clients (uid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;