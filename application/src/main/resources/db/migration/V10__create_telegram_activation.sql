create table telegram_activation
(
    id               bigserial not null,
    expiry_date      timestamp,
    token            varchar(255),
    telegram_user_id int8,
    primary key (id)
);

alter table telegram_activation
    add constraint UK_lvrvf63g1jf9rifcy5dvmeam4 unique (telegram_user_id);
alter table telegram_activation
    add constraint FK99uknxmxl16atbsfvs1ql7s42 foreign key (telegram_user_id) references telegram_user;
