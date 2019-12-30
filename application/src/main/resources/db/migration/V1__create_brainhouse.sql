<<<<<<< HEAD
-- create sequence hibernate-sequence start 1 increment 1;
=======
create table device_features (
  device_id bigint not null,
  features_id bigint not null) engine=MyISAM;

create table device_template (
  id bigint not null auto_increment,
  brand varchar(255) not null,
  model varchar(255) not null,
  power_supply varchar(255),
  release_year integer,
  type varchar(255) not null,
  primary key (id)) engine=MyISAM;

create table features (
  id bigint not null auto_increment,
  description varchar(255) not null,
  name varchar(255) not null,
  primary key (id)) engine=MyISAM;
>>>>>>> 93c09957... fixing

create table home (
  id bigint not null auto_increment,
  addressa text,
  city text,
  country text,
  primary key (id)) engine=MyISAM;

create table location (
  id bigint not null auto_increment,
  name text,
  home_id bigint,
  primary key (id)) engine=MyISAM;

alter table location
  add constraint FKqvj3evkkjc33etbi5ksb6u748
  foreign key (home_id)
  references home (id);