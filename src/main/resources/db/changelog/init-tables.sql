--liquibase formatted sql

--changeset roman.tarasov:documents_1
create table documents (
    id uuid primary key,
    document_date date
);
--rollback drop table documents;

--changeset roman.tarasov:dictionary_values_1
create table dictionary_values (
    id uuid primary key,
    name varchar(255)
);
--rollback drop table dictionary_values;

--changeset roman.tarasov:tests_1
create table tests (
    id uuid primary key,
    document_id uuid references documents (id),
    dictionary_value_id uuid references dictionary_values (id),
    sort_order varchar(20),
    test_name varchar(255)
);
--rollback drop table tests;
