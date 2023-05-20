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
