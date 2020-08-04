CREATE TABLE actions
(
    id          bigserial    not null,
    description varchar(255) not null,
    type        int4         not null,
    primary key (id)
);
