create table "user" (
    id 				                bigserial 		primary key,
    username   		                varchar(100)    not null unique,
    first_name 		                varchar(100)    not null,
    last_name		                varchar(100)    not null,
    password   		                varchar(100)    not null,
    created                         timestamp       not null,
    updated                         timestamp       not null
);

create table user_car (
    fk_car                          bigint          not null references car(id) on delete cascade,
    fk_user                         bigint          not null references "user"(id),
    primary key (fk_car, fk_user)
)
