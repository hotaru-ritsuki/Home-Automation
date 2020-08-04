create table user_home
(
    home_id int8 not null,
    user_id int8 not null
);

alter table user_home
    add constraint FKo8lycc0bd4as3hocqeprxhrsw foreign key (user_id) references users;
alter table user_home
    add constraint FKecghr2udqlel8f11r5i373kqn foreign key (home_id) references home;
