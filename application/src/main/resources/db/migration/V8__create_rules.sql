create table rules
(
    id          bigserial not null,
    active      boolean,
    conditions  varchar(2000),
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
