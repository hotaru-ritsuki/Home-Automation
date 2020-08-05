create table location
(
    id      bigserial not null,
    name    varchar(255),
    home_id int8,
    primary key (id)
);

alter table location
    add constraint FKqvj3evkkjc33etbi5ksb6u748 foreign key (home_id) references home;
