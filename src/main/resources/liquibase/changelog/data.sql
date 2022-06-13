-- liquibase formatted sql

-- changeset smart:insert_into_users
insert into users(id, login, email, password, created)
values (1, 'admin', 'admin@admin.ru', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', '2022-06-13 00:00:00'),
       (2, 'user', 'user@user.ru', '$2a$10$rMFTxSBhjif2x/EsX7XmbeXyd.eDIvyNI4B9mByftYRlSUtjUkIeK', '2022-06-13 00:00:00');

-- changeset smart:insert_into_subject
insert into subject(id, name, reserved_count)
values (1, 'Запись к врачу', 10),
       (2, 'Получить справку', 20),
       (3, 'Замена паспорта', 5),
       (4, 'Оплатить штраф', 6),
       (5, 'Регистрация недвижимости', 10);

-- changeset smart:insert_into_deal
insert into deal(subject_id, user_id, fio, email, created, provided)
values (1, 1, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:00:00', null),
       (1, 1, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:00:00', null),
       (2, 1, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:00:00', null),
       (2, 1, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:00:00', null),
       (3, 1, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:00:00', null),
       (3, 2, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:00:00', null),
       (4, 2, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:00:00', null),
       (4, 2, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:00:00', null),
       (5, 2, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:00:00', null),
       (5, 2, 'Иванов Иван Иванович', '1@1.ru', '2022-06-13 00:00:00', null),
       (5, 2, 'Петров Петр Петрович', '1@1.ru', '2022-06-13 00:00:00', null);

