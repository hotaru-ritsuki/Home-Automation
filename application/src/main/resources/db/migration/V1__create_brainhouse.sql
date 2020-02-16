
create table actions (id bigint not null auto_increment, description varchar(255) not null, type integer not null, primary key (id)) engine=MyISAM;
create table actions_rule (action_id bigint not null, rule_id bigint not null, action_specification varchar(255) not null, primary key (action_id, rule_id)) engine=MyISAM;
 create table device_features (device_id bigint not null, features_id bigint not null, specification varchar(255) not null, primary key (device_id, features_id)) engine=MyISAM;
 create table device_template (id bigint not null auto_increment, brand varchar(255) not null, image varchar(255) not null, model varchar(255) not null, power_supply varchar(255), release_year integer, type varchar(255) not null, primary key (id)) engine=MyISAM;
 create table features (id bigint not null auto_increment, description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=MyISAM;
 create table home (id bigint not null auto_increment, addressa varchar(255), city varchar(255), country varchar(255), name varchar(255), primary key (id)) engine=MyISAM;
 create table local_device (uuid varchar(255) not null, description varchar(255) not null, supported_device_id bigint not null, location_id bigint not null, primary key (uuid)) engine=MyISAM;
 create table location (id bigint not null auto_increment, name varchar(255), home_id bigint, primary key (id)) engine=MyISAM;
 create table rules (id bigint not null auto_increment, active bit, conditions varchar(2000), description varchar(255), name varchar(255), primary key (id)) engine=MyISAM;
 create table telegram_activation (id bigint not null auto_increment, expiry_date datetime, token varchar(255), telegram_user_id bigint, primary key (id)) engine=MyISAM;
 create table telegram_user (id bigint not null auto_increment, chat_id varchar(255), enabled bit, username varchar(255), primary key (id)) engine=MyISAM;
 create table user_home (home_id bigint not null, user_id bigint not null) engine=MyISAM;
 create table users (id bigint not null auto_increment, email varchar(255) not null, enabled boolean default false, first_name varchar(255) not null, last_name varchar(255) not null, password varchar(255) not null, secret varchar(255) not null, telegram_user_id bigint, primary key (id)) engine=MyISAM;
 create table verification_token (id bigint not null auto_increment, expiry_date datetime, token varchar(255), user_id bigint, primary key (id)) engine=MyISAM;
 alter table telegram_activation add constraint UK_lvrvf63g1jf9rifcy5dvmeam4 unique (telegram_user_id);
 alter table telegram_user add constraint UK_8fjrx8y7kifvm13xif72sy5bf unique (chat_id);
 alter table telegram_user add constraint UK_c7f0a9nq5qaaquhoyswh9pn5 unique (username);
 alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
 alter table actions_rule add constraint FKdrnem74m95bpp2s23adqb7u0a foreign key (rule_id) references rules (id);
 alter table actions_rule add constraint FKkbdxmu5bsutxkvor7stne0wbb foreign key (action_id) references actions (id);
 alter table device_features add constraint FK7uivhprva3mke1uvk59m42r0a foreign key (device_id) references device_template (id);
 alter table device_features add constraint FKcjsjodj6lwm8tldpiaflvjkns foreign key (features_id) references features (id);
 alter table local_device add constraint FKlnc4rmqppjpxf8lhqglwi9gjm foreign key (supported_device_id) references device_template (id);
 alter table local_device add constraint FK7rpxlg1mxbilymldv41omxywi foreign key (location_id) references location (id);
 alter table location add constraint FKqvj3evkkjc33etbi5ksb6u748 foreign key (home_id) references home (id);
 alter table telegram_activation add constraint FK99uknxmxl16atbsfvs1ql7s42 foreign key (telegram_user_id) references telegram_user (id);
 alter table user_home add constraint FKo8lycc0bd4as3hocqeprxhrsw foreign key (user_id) references users (id);
 alter table user_home add constraint FKecghr2udqlel8f11r5i373kqn foreign key (home_id) references home (id);
 alter table users add constraint FK6qmcs8aibbq2gq6k7dr86o543 foreign key (telegram_user_id) references telegram_user (id);
 alter table verification_token add constraint FK3asw9wnv76uxu3kr1ekq4i1ld foreign key (user_id) references users (id);
