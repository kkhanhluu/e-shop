drop table if exists order_line cascade;
create table order_line
(
    id            uuid not null,
    product_id    uuid,
    product_price numeric(38, 2),
    quantity      integer,
    order_id      uuid,
    primary key (id)
);

drop table if exists orders cascade;
create table orders
(
    id                 uuid not null,
    created_date       timestamp(6),
    last_modified_date timestamp(6),
    status             smallint,
    user_id            uuid,
    primary key (id)
);