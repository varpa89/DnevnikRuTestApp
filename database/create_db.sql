-- Database: dnevnik

-- DROP DATABASE dnevnik;

CREATE DATABASE dnevnik
  WITH OWNER = admin
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;

-- Table: account

-- DROP TABLE account;

CREATE TABLE account
(
  id serial NOT NULL,
  firstname text,
  middlename text,
  lastname text,
  login text,
  password text,
  comment text,
  birthdate date,
  gender text,
  created timestamp without time zone,
  updated timestamp without time zone,
  deleted boolean DEFAULT false
)
WITH (
  OIDS=FALSE
);
ALTER TABLE account
  OWNER TO admin;

