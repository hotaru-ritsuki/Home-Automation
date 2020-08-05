create table users
(
    id               bigserial    not null,
    email            varchar(255) not null,
    enabled          boolean default false,
    first_name       varchar(255) not null,
    last_name        varchar(255) not null,
    password         varchar(255) not null,
    secret           varchar(255) not null,
    telegram_user_id int8,
    primary key (id)
);

alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
