--liquibase formatted sql
--changeset rahim:002

INSERT INTO roles (name) VALUES 
    ('Admin'),
    ('Member')
;
