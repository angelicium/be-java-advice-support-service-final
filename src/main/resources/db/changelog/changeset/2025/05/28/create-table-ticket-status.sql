--liquibase formatted sql
--changeset Miloshevich Alexandr :2025-05-28-create-table-ticket-status

CREATE TABLE IF NOT EXISTS ticket_status (
                                             id INTEGER PRIMARY KEY,
                                             name VARCHAR(100) NOT NULL,
    description TEXT
    );

INSERT INTO ticket_status (id, name, description)
VALUES
    (1, 'NEW', 'Новый тикет, ожидает назначения'),
    (2, 'ASSIGNED', 'Тикет назначен оператору, ожидает обработки'),
    (3, 'IN_PROGRESS', 'Тикет в обработке, оператор начал работу'),
    (4, 'RESOLVED', 'Тикет решен, ожидает подтверждения от клиента'),
    (5, 'CLOSED', 'Тикет закрыт, работа завершена'),
    (6, 'ESCALATED', 'Тикет передан на более высокий уровень поддержки')
    ON CONFLICT (id) DO NOTHING;