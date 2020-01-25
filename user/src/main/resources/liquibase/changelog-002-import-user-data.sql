--liquibase formatted sql

--changeset admin:changelog-002-import-user-data logicalFilePath:changelog-002-import-user-data.sql failOnError:true

INSERT INTO users (id, name, password, email, is_admin)
VALUES (1, 'nwidart', '{bcrypt}$2a$10$yiIGwxNPWwJ3CZ0SGAq3i.atLYrQNhzTyep1ALi6dbax1b1R2Y.cG', 'nwidart@example.com',
        TRUE);
INSERT INTO users (id, name, password, email, is_admin)
VALUES (2, 'sakuma', '{bcrypt}$2a$10$9jo/FSVljst5xJjuw9eyoumx2iVCUA.uBkUKeBo748bUIaPjypbte', 'rsakuma@example.com',
        FALSE);
INSERT INTO users (id, name, password, email, is_admin)
VALUES (3, 'yukinaga', '{bcrypt}$2a$10$1OXUbgiuuIi3SOO3t.jyZOEY66ELL03dRcGpAKWql8HBXOag4YZ8q', 'tyukinaga@example.com',
        FALSE);
INSERT INTO users (id, name, password, email, is_admin)
VALUES (4, 'sawatari', '{pbkdf2}0963ebe5b7508e9f0de55e7480ee7b87c623754ea18a94f25a20cc213a6341695d6ad38d18ff8f25',
        'zsawatari@example.com', TRUE);
INSERT INTO users (id, name, password, email, is_admin)
VALUES (5, 'hiyama', '{pbkdf2}998f1e8af4662f9c7e44bad5af69e916f0ab6cf6af1a1a38b0e667f5f7b9f5bb0d3700e2eacfcf72',
        'ehiyama@example.com', FALSE);
