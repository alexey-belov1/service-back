-- liquibase formatted sql

-- changeset smart:insert_into_users
insert into users(login, password, created)
values ('admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', '2022-06-13 00:00:00'),
       ('user', '$2a$10$rMFTxSBhjif2x/EsX7XmbeXyd.eDIvyNI4B9mByftYRlSUtjUkIeK', '2022-06-13 00:00:00');

-- changeset smart:insert_into_subject
insert into subject(name, reserved_count)
values ('Запись к врачу', 10),
       ('Получить справку', 20),
       ('Замена паспорта', 5),
       ('Оплатить штраф', 6),
       ('Регистрация недвижимости', 10);

-- changeset smart:insert_into_deal
insert into deal(subject_id, user_id, fio, email, created, provided)
values (1, 1, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:01:00', null),
       (1, 1, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:02:00', null),
       (2, 1, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:03:00', null),
       (2, 1, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:04:00', null),
       (3, 1, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:05:00', null),
       (3, 2, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:06:00', null),
       (4, 2, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:07:00', null),
       (4, 2, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:08:00', null),
       (5, 2, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:09:00', null),
       (5, 2, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:10:00', null),
       (5, 2, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:11:00', null);

