--liquibase formatted sql

--changeset admin:item-service-changelog-005-add-price-items logicalFilePath:item-service-changelog-005-add-price-items.sql failOnError:true

ALTER TABLE items
    ADD price INT NULL
