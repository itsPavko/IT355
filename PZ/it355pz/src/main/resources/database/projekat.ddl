create table company
(
    company_id          int auto_increment
        primary key,
    company_name         varchar(128)                             not null,
    director_name          varchar(128)                             not null,
    established                int                                      not null,
    country            varchar(64)                              not null,
    created_date       timestamp    default current_timestamp() null,
    last_modified_date timestamp    default current_timestamp() null,
    last_modified_by   varchar(128) default 'system'            null,
    record_status      int          default 1                   null
);

create table watch
(
    watch_id            int auto_increment
        primary key,
    company_fk          int                                                       not null,
    name               varchar(254) collate utf8_bin                             not null,
    price              double                                                    not null,
    image              varchar(1024)                                             null,
    type              varchar(128) collate utf8_bin                             not null,
    amount             int                                                       not null,
    description        varchar(1024) collate utf8_bin                            not null,
    created_date       timestamp                     default current_timestamp() null,
    last_modified_date timestamp                     default current_timestamp() null,
    last_modified_by   varchar(128) collate utf8_bin default 'system'            null,
    record_status      int                           default 1                   null,
    constraint fk_company_watch
        foreign key (company_fk) references company (company_id)
            on update cascade on delete cascade
);

create table wallet
(
    wallet_id     int auto_increment
        primary key,
    card_number        varchar(12)  default ''                  not null,
    bank               varchar(128)                             not null,
    money            double                                   not null,
    created_date       timestamp    default current_timestamp() null,
    last_modified_date timestamp    default current_timestamp() null,
    last_modified_by   varchar(128) default 'system'            null,
    record_status      int          default 1                   null,
    constraint card_number
        unique (card_number)
);

create table role
(
    role_id            int auto_increment
        primary key,
    name               varchar(128)                             not null,
    created_date       timestamp    default current_timestamp() null,
    last_modified_date timestamp    default current_timestamp() null,
    last_modified_by   varchar(128) default 'system'            null,
    record_status      int          default 1                   null
);

create table userr
(
    user_id            int auto_increment
        primary key,
    wallet_fk     int                                      null,
    role_fk            int                                      not null,
    username           varchar(254)                             not null,
    password           varchar(254)                             not null,
    created_date       timestamp    default current_timestamp() null,
    last_modified_date timestamp    default current_timestamp() null,
    last_modified_by   varchar(128) default 'system'            null,
    record_status      int          default 1                   null,
    constraint fk_user_wallet
        foreign key (wallet_fk) references wallet (wallet_id)
            on update cascade,
    constraint fk_user_role
        foreign key (role_fk) references role (role_id)
);

create table member
(
    member_id          int auto_increment
        primary key,
    user_fk            int                                      not null,
    member_number      int                                      not null,
    discount           double                                   not null,
    type               varchar(64)                              not null,
    created_date       timestamp    default current_timestamp() null,
    last_modified_date timestamp    default current_timestamp() null,
    last_modified_by   varchar(128) default 'system'            null,
    record_status      int          default 1                   null,
    constraint fk_member_user
        foreign key (user_fk) references userr (user_id)
            on update cascade on delete cascade
);

create table shopping
(
    shopping_id        int auto_increment
        primary key,
    member_fk          int                                      not null,
    watch_fk            int                                      not null,
    date               date                                     not null,
    price              double                                   not null,
    created_date       timestamp    default current_timestamp() null,
    last_modified_date timestamp    default current_timestamp() null,
    last_modified_by   varchar(128) default 'system'            null,
    record_status      int          default 1                   null,
    constraint fk_shopping_watch
        foreign key (watch_fk) references watch (watch_id)
            on update cascade on delete cascade,
    constraint fk_shopping_member
        foreign key (member_fk) references member (member_id)
            on update cascade on delete cascade
);

