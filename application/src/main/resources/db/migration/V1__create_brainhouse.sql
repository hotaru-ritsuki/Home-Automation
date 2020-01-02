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

create table home (
  id bigint not null auto_increment,
  addressa varchar(255),
  city varchar(255),
  country varchar(255),
  primary key (id)) engine=MyISAM;

create table local_device (
  uuid varchar(32) not null,
  location_id bigint,
  supported_device_id bigint,
  primary key (uuid)) engine=MyISAM;

create table location (
  id bigint not null auto_increment,
  name varchar(255),
  home_id bigint,
  primary key (id)) engine=MyISAM;

create table user_home (
  user_id bigint not null,
  home_id bigint not null) engine=MyISAM;

create table users (
  id bigint not null auto_increment,
  email varchar(32) not null,
  password varchar(255) not null,
  primary key (id)) engine=MyISAM;

alter table users
  add constraint UK_6dotkott2kjsp8vw4d0m25fb7
  unique (email);

alter table device_features
  add constraint FKcjsjodj6lwm8tldpiaflvjkns
  foreign key (features_id) references features (id);

alter table device_features
  add constraint FK7uivhprva3mke1uvk59m42r0a
  foreign key (device_id) references device_template (id);

alter table local_device
  add constraint FK7rpxlg1mxbilymldv41omxywi
  foreign key (location_id) references location (id);

alter table local_device
  add constraint FKlnc4rmqppjpxf8lhqglwi9gjm
  foreign key (supported_device_id) references device_template (id);

alter table location
  add constraint FKqvj3evkkjc33etbi5ksb6u748
  foreign key (home_id) references home (id);

alter table user_home
  add constraint FKecghr2udqlel8f11r5i373kqn
  foreign key (home_id) references home (id);

alter table user_home
  add constraint FKo8lycc0bd4as3hocqeprxhrsw
  foreign key (user_id) references users (id);
