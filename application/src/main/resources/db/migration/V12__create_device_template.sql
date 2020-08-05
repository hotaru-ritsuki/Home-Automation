create table device_template
(
    id           bigserial    not null,
    brand        varchar(255) not null,
    image        varchar(255) not null,
    model        varchar(255) not null,
    power_supply varchar(255),
    release_year int4,
    type         varchar(255) not null,
    primary key (id)
);

