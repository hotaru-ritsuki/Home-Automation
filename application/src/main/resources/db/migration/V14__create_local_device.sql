create table local_device
(
    uuid                varchar(255) not null,
    description         varchar(255) not null,
    supported_device_id int8         not null,
    location_id         int8         not null,
    primary key (uuid)
);

alter table local_device
    add constraint FKlnc4rmqppjpxf8lhqglwi9gjm foreign key (supported_device_id) references device_template;
alter table local_device
    add constraint FK7rpxlg1mxbilymldv41omxywi foreign key (location_id) references location;
