create table device_features
(
    device_id     int8         not null,
    features_id   int8         not null,
    specification varchar(255) not null,
    primary key (device_id, features_id)
);

alter table device_features
    add constraint FK7uivhprva3mke1uvk59m42r0a foreign key (device_id) references device_template;
alter table device_features
    add constraint FKcjsjodj6lwm8tldpiaflvjkns foreign key (features_id) references features;
