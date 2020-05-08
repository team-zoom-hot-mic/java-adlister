

create table categories
(
    id int unsigned auto_increment
        primary key,
    title varchar(240) not null
);

create table users
(
    id int unsigned auto_increment
        primary key,
    username varchar(240) not null,
    email varchar(240) not null,
    password char(60) not null
);

create table ads
(
    id int unsigned auto_increment
        primary key,
    user_id int unsigned not null,
    title varchar(240) not null,
    description varchar(1000) not null,
    category_id int unsigned null,
    multiCategories longtext null,
    constraint ads_ibfk_1
        foreign key (user_id) references users (id)
            on delete cascade,
    constraint ads_ibfk_2
        foreign key (category_id) references categories (id)
            on delete cascade
);

create index category_id
    on ads (category_id);

create index user_id
    on ads (user_id);

