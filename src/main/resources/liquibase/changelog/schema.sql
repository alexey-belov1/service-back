-- liquibase formatted sql

-- changeset smart:create_table_users
create table users
(
    id       serial primary key,
    login    varchar(50)      not null,
    password varchar(500)     not null,
    created  timestamp        not null
);
-- rollback drop table users;

-- changeset smart:create_table_subject
create table subject
(
    id       serial primary key,
    name varchar(50)     not null,
    reserved_count int not null,
    provided_count int not null default 0
);
-- rollback drop table subject;

-- changeset smart:create_table_deal
create table deal
(
    id       serial primary key,
    subject_id    int             not null,
    user_id       int             not null,
    created       timestamp       not null,
    provided      timestamp,
    fio           varchar(500),
    email         varchar(500),
    constraint fk_task_user_id foreign key (user_id) references users (id),
    constraint fk_task_subject_id foreign key (subject_id) references subject (id)
);
-- rollback drop table deal;
