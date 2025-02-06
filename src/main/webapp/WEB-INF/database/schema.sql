create table users
(
    code     int auto_increment
        primary key,
    username varchar(20)                         not null,
    password varchar(255)                        not null,
    reg_date timestamp default CURRENT_TIMESTAMP null,
    mod_date timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    nickname varchar(20)                         null,
    constraint nickname
        unique (nickname),
    constraint username
        unique (username)
);

create table boards
(
    code     int auto_increment
        primary key,
    author   int                                 not null,
    title    varchar(100)                        not null,
    content  varchar(4000)                       not null,
    reg_date timestamp default CURRENT_TIMESTAMP null,
    mod_date timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint boards_ibfk_1
        foreign key (author) references users (code)
            on delete cascade
);

