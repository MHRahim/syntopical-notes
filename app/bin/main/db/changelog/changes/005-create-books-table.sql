--liquibase formatted sql
--changeset rahim:005

CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    date_published DATE NOT NULL,
    isbn VARCHAR(50) UNIQUE NOT NULL,
    author_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
