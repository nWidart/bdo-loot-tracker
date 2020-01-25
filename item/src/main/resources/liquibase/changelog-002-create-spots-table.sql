--liquibase formatted sql

--changeset admin:changelog-002-create-spots-table logicalFilePath:changelog-002-create-spots-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS spots
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL
)
