--liquibase formatted sql

--changeset admin:changelog-001-create-items-table logicalFilePath:changelog-001-create-items-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS items
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(128) NOT NULL,
    is_taxable BOOLEAN      NOT NULL DEFAULT FALSE
)
