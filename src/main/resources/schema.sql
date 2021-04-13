create table if not exists basket (
    id bigint not null,
    primary key (id)
);
create table if not exists basket_items (
    basket_id bigint not null,
    items_id bigint not null
);
create table if not exists item (
    name varchar(255),
    id bigint not null,
    amount bigint,
    creation_date timestamp,
    description varchar(255),
    item_type integer,
    photo_id bigint,
    price decimal(19,2),
    primary key (id)
);
create table if not exists item_photo (
    id bigint not null,
    item_id bigint not null,
    photo varbinary(max),
    name varchar(255),
    primary key (id)
);

alter table basket_items add constraint FK4q9gnl10yamqxew6rs2wdk0qq foreign key (items_id) references item;
alter table basket_items add constraint FKh8ugq8wukffh0bpp01ct2p49t foreign key (basket_id) references basket;
alter table item_photo add constraint UKh8ugq8wukffh0bpp01ct2p49t foreign key (item_id) references item;
