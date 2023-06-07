DROP TABLE IF EXISTS bookings CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS requests CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS shareit_user CASCADE;

CREATE TABLE IF NOT EXISTS shareit_user (
    id bigint not null generated always as identity primary key,
    name varchar(255) not null,
    email varchar not null unique
);

CREATE TABLE IF NOT EXISTS requests (
    id bigint not null generated always as identity primary key,
    description varchar(255),
    requestor_id bigint not null references shareit_user(id),
    created_date timestamp without time zone not null
);

CREATE TABLE IF NOT EXISTS items (
     id bigint not null generated always as identity primary key,
     name varchar (255) not null,
     description varchar (512),
     available boolean not null,
     owner_id bigint not null references shareit_user(id),
     request_id bigint references requests(id)
);

CREATE TABLE IF NOT EXISTS bookings (
    id bigint not null generated always as identity primary key,
    start_date timestamp without time zone not null,
    end_date timestamp without time zone not null,
    item_id bigint not null references items(id),
    owner_id bigint not null references shareit_user(id),
    booker_id bigint not null references shareit_user(id),
    status int
);

CREATE TABLE IF NOT EXISTS comments (
    id bigint not null generated always as identity primary key,
    text varchar(255),
    item_id bigint not null references items(id),
    author_id bigint not null references shareit_user(id),
    created_date timestamp without time zone not null
);
