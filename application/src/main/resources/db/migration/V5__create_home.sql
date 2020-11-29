create table home
(
    id       bigserial not null,
    address varchar(255),
    city     varchar(255),
    country  varchar(255),
    name     varchar(255),
    primary key (id)
);
