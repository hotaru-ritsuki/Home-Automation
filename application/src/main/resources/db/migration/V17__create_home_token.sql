create table home_token
(
    id          bigserial not null,
    expiry_date timestamp,
    token       varchar(255),
    home_id     int8,
    user_id     int8,
    primary key (id)
);

alter table home_token
    add constraint FK69ssaq1c140st4vs23nohwn81 foreign key (home_id) references home;
alter table home_token
    add constraint FKov4eyvvgmo7scmoy6y79mkkjw foreign key (user_id) references users;