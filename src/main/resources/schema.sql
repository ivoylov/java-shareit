CREATE TABLE IF NOT EXISTS shareit_user (
    id bigint not null generated always as identity primary key,
    name varchar(255) not null,
    email varchar not null unique
);

CREATE TABLE IF NOT EXISTS items (
    id bigint not null generated always as identity primary key,
    name varchar (255) not null,
    description varchar (512),
    available boolean not null,
    owner_id bigint not null references shareit_user(id)
);

CREATE TABLE IF NOT EXISTS bookings (
    id bigint not null generated always as identity primary key ,
    start_date date not null,
    end_date date not null,
    item_id bigint not null ,
    booker_id bigint not null,
    status int
);

CREATE TABLE IF NOT EXISTS bookings (
    id bigint not null generated always as identity primary key ,
    description varchar(255),
    requestor_id bigint
);

CREATE TABLE IF NOT EXISTS bookings (
    id bigint not null generated always as identity primary key ,
    text varchar(255),
    item_id bigint,
    author_id bigint
);

