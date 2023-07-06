DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS shareit_user;

CREATE TABLE IF NOT EXISTS shareit_user (
    user_id bigint not null generated always as identity primary key,
    user_name varchar(255) not null,
    user_email varchar not null unique
);

CREATE TABLE IF NOT EXISTS items (
    item_id bigint not null generated always as identity primary key,
    owner_id bigint not null,
    item_name varchar (255) not null,
    item_description varchar (512),
    item_available boolean not null
);

ALTER TABLE items ADD FOREIGN KEY (owner_id) REFERENCES shareit_user(user_id);
