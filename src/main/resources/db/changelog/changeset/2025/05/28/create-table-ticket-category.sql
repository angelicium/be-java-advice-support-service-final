--liquibase formatted sql
--changeset Burilov-Aleksey 2025-05-28:create-table-ticket-category
CREATE TABLE IF NOT EXISTS ticket_category
(
    id          INTEGER PRIMARY KEY NOT NULL,
    name        VARCHAR(100)        NOT NULL,
    description TEXT
);

INSERT INTO ticket_category (id, name, description)
VALUES (1, 'Техническая поддержка', 'Вопросы, связанные с технической помощью'),
       (2, 'Финансовые вопросы', 'Вопросы, касающиеся оплаты и счетов'),
       (3, 'Юридические вопросы', 'Консультации по юридическим аспектам'),
       (4, 'Прочее', 'Общие или неклассифицированные вопросы')
ON CONFLICT (id) DO NOTHING;