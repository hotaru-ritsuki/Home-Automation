create table telegram_user
(
    id       bigserial not null,
    chat_id  varchar(255),
    enabled  boolean,
    username varchar(255),
    primary key (id)
);


alter table telegram_user
    add constraint UK_8fjrx8y7kifvm13xif72sy5bf unique (chat_id);
alter table telegram_user
    add constraint UK_c7f0a9nq5qaaquhoyswh9pn5 unique (username);
alter table users
    add constraint FK6qmcs8aibbq2gq6k7dr86o543 foreign key (telegram_user_id) references telegram_user;
