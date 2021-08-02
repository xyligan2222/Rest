create table address
(
    id       bigint       not null,
    name     varchar(255) not null,
    owner_id integer,
    constraint ADDRESS_PK primary key (id),
    constraint ADDRESS_OWNER_FK foreign key (owner_id) references client
);

create table client
(
    id      integer      not null,
    name    varchar(255) not null,
    surname varchar(255) not null,
    constraint CLIENT_PK primary key (id)
);
