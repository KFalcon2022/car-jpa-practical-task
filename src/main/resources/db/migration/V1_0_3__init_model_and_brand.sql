create table brand (
    id 				                bigserial 		primary key,
    name      		                varchar(100)    not null unique,
    created                         timestamp       not null,
    updated                         timestamp       not null
);

create table model (
    id 				                bigserial 		primary key,
    name      		                varchar(100)    not null,
    fk_brand                        bigint          not null references brand(id),
    created                         timestamp       not null,
    updated                         timestamp       not null,
    unique (name, fk_brand)
);

-- Реальный проект нам такого не простит - не получится добавить not null-колонку, если в таблице уже есть записи.
-- Но в рамках практической задачи - проще удалить данные в car, чем придумывать, какие значения вписать в новую колонку
alter table car add column fk_model bigint not null references model(id);
