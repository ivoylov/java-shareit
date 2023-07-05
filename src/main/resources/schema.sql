DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS shareit_user;

CREATE TABLE IF NOT EXISTS shareit_user (
    shareit_user_id bigint not null generated always as identity primary key,
    name varchar(255) not null,
    email varchar not null unique
);

CREATE TABLE IF NOT EXISTS items (
    items_id bigint not null generated always as identity primary key,
    owner_id bigint not null,
    name varchar (255) not null,
    description varchar (512),
    available boolean not null
);

ALTER TABLE items ADD FOREIGN KEY (owner_id) REFERENCES shareit_user(shareit_user_id);
