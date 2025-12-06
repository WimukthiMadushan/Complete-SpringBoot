create table carts
(
    id           binary(16) default (UUID_TO_BIN(UUID())) not null
        primary key,
    date_created date       default (CURDATE())           not null
);

