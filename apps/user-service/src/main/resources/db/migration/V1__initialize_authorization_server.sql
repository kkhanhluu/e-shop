create table clients
(
    id           uuid not null,
    auth_method  varchar(255),
    client_id    varchar(255),
    grant_types  text[],
    redirect_uri varchar(255),
    scopes       text[],
    secret       varchar(255),
    primary key (id)
);

create table users
(
    id          uuid not null,
    created_at  timestamp(6),
    email       varchar(255),
    modified_at timestamp(6),
    password    varchar(255),
    username    varchar(255),
    primary key (id)
)
