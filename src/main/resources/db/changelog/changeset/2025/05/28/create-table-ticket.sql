--liquibase formatted sql
--changeset Angelica 2025-05-28:create-table-ticket
CREATE TABLE ticket(
                       id UUID PRIMARY KEY NOT NULL,
                       user_id UUID NOT NULL,
                       category_id INT NOT NULL,
                       priority_id INT NOT NULL,
                       status_id INT NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       description TEXT NOT NULL,
                       sla_deadline TIMESTAMP,
                       escalated_at TIMESTAMP,
                       created_at TIMESTAMP,
                       updated_at timestamp,
                       closed_at timestamp
);