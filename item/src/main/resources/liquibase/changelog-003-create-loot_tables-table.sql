--liquibase formatted sql

--changeset admin:changelog-003-create-loot_tables-table logicalFilePath:changelog-003-create-loot_tables-table.sql failOnError:true

CREATE TABLE IF NOT EXISTS loot_tables
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    item_id INT,
    spot_id INT,
    CONSTRAINT loot_tables_fk_item_id FOREIGN KEY (item_id) REFERENCES items (id),
    CONSTRAINT loot_tables_fk_spot_id FOREIGN KEY (spot_id) REFERENCES spots (id)
)
