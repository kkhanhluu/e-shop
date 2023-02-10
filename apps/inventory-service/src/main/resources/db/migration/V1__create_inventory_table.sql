drop table if exists product_inventory cascade;

create table product_inventory
(
    id               uuid not null,
    product_id       uuid,
    quantity_on_hand integer,
    primary key (id)
);