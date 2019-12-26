-- create sequence hibernate-sequence start 1 increment 1;

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
