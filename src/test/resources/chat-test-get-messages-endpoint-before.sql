drop schema if exists chat_test;
create schema chat_test;

create table user
(
    user_name varchar(255) not null,
    join_time datetime default null,
    primary key (user_name)
);

create table users_messages
(
    id      bigint(20) not null,
    content varchar(255) default null,
    sender  varchar(255) default null,
    time    datetime     default null,
    type    varchar(32)  default 'CHAT',
    primary key (id)
);

insert into user (user_name, join_time)
values ('yarik', '2020-11-06 18:59:17.826000');
insert into user (user_name, join_time)
values ('oleg', '2020-11-06 18:59:39.888000');

insert into users_messages (id, content, sender, time, type)
values (1, 'hi', 'yarik', '2020-11-06 18:59:23.802000', 'CHAT');
insert into users_messages (id, content, sender, time, type)
values (2, 'ok', 'yarik', '2020-11-06 18:59:28.274000', 'CHAT');
insert into users_messages (id, content, sender, time, type)
values (3, 'hi', 'oleg', '2020-11-06 18:59:45.425000', 'CHAT');
insert into users_messages (id, content, sender, time, type)
values (4, 'ok', 'oleg', '2020-11-06 18:59:46.951000', 'CHAT');
insert into users_messages (id, content, sender, time, type)
values (5, 'ad', 'oleg', '2020-11-06 18:59:48.769000', 'CHAT');