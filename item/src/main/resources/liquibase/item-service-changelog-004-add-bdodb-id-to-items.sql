--liquibase formatted sql

--changeset admin:item-service-changelog-004-add-bdodb-id-to-items logicalFilePath:item-service-changelog-004-add-bdodb-id-to-items.sql failOnError:true

ALTER TABLE items
    ADD bdodatabase_id INT
