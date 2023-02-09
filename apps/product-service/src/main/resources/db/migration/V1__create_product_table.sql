drop table if exists product cascade;
create table product
(
    id          uuid         not null,
    description varchar(255),
    name        varchar(255) not null,
    price       numeric(38, 2),
    primary key (id)
);