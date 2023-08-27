DROP TABLE IF EXISTS requests;
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
    owner_id bigint not null REFERENCES shareit_user(user_id),
    item_name varchar (255) not null,
    item_description varchar (512),
    item_available boolean not null
);

CREATE TABLE IF NOT EXISTS bookings (
    booking_id bigint not null generated always as identity primary key,
    booker_id bigint not null REFERENCES shareit_user(user_id),
    item_id bigint not null REFERENCES items(item_id),
    start_date TIMESTAMP WITHOUT TIME ZONE,
    end_date TIMESTAMP WITHOUT TIME ZONE,
    status int
);

CREATE TABLE IF NOT EXISTS comments (
    comment_id bigint not null generated always as identity primary key,
    booker_id bigint not null REFERENCES shareit_user(user_id),
    item_id bigint not null REFERENCES items(item_id),
    created_date TIMESTAMP WITHOUT TIME ZONE,
    comment_text VARCHAR(255) not null
);

CREATE TABLE IF NOT EXISTS requests (
    request_id bigint not null generated always as identity primary key,
    user_id bigint not null REFERENCES shareit_user(user_id),
    created_date TIMESTAMP WITHOUT TIME ZONE not null,
    description VARCHAR(255) not null
);
