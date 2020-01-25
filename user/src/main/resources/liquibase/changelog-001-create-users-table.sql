--liquibase formatted sql

--changeset admin:changelog-001-create-users-table logicalFilePath:changelog-001-create-users-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT AUTO_INCREMENT,
    name     VARCHAR(128) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    is_admin BOOLEAN      NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    UNIQUE KEY (email)
)
