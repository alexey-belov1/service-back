-- liquibase formatted sql

-- changeset smart:create_table_users
create table users
(
    id       serial primary key,
    login    varchar(30)      not null,
    password varchar(200)      not null,
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
    fio           varchar(100),
    email         varchar(50),
    constraint fk_task_user_id foreign key (user_id) references users (id),
    constraint fk_task_subject_id foreign key (subject_id) references subject (id)
);
-- rollback drop table deal;

-- changeset smart:create_table_email_task
create table email_task
(
    id            serial primary key,
    recipient     varchar(50) not null,
    theme         varchar(100) not null,
    text          varchar(500) not null
);
-- rollback drop table email_task;

-- changeset smart:index_deal_user_id
create index index_deal_user_id ON public.deal(user_id);
-- rollback drop index index_deal_user_id;
