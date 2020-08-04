create table verification_token
(
    id          bigserial not null,
    expiry_date timestamp,
    token       varchar(255),
    user_id     int8,
    primary key (id)
);

alter table verification_token
    add constraint FK3asw9wnv76uxu3kr1ekq4i1ld foreign key (user_id) references users;
