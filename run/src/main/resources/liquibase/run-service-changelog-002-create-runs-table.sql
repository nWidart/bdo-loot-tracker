--liquibase formatted sql

--changeset admin:run-service-changelog-002-create-runs-table logicalFilePath:run-service-changelog-002-create-runs-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS runs
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    session_id INT,
    created_at timestamp NOT NULL default CURRENT_TIMESTAMP,
    comment    TEXT      NULL,
    CONSTRAINT loot_tables_fk_session_id FOREIGN KEY (session_id) REFERENCES sessions (id)
);

CREATE TABLE IF NOT EXISTS run_drops
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    run_id        INT,
    item_id       INT,
    total         INT,
    current_price DECIMAL(12, 2),
    created_at    timestamp NOT NULL default CURRENT_TIMESTAMP,
    comment       TEXT      NULL,
    CONSTRAINT loot_tables_fk_run_id FOREIGN KEY (run_id) REFERENCES runs (id)
);
