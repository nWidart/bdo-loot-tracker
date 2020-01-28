--liquibase formatted sql

--changeset admin:run-service-changelog-001-create-sessions-table logicalFilePath:run-service-changelog-001-create-sessions-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS sessions
(
    id         VARCHAR(255) PRIMARY KEY,
    user_id    INT,
    spot_id    INT,
    created_at timestamp    NOT NULL default CURRENT_TIMESTAMP,
    stopped_at timestamp    NULL     default NULL,
    config     TEXT         NULL,
    ap         INT          NOT NULL,
    dp         INT          NOT NULL,
    class      VARCHAR(128) NOT NULL
)
