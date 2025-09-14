create table role (
    id serial primary key,
    name varchar(50) not null unique
);

create table users (
    id serial primary key,
    username varchar(50) not null unique,
    password varchar(100) not null,
);

create table user_roles (
    user_id int references users(id) on delete cascade,
    role_id int references role(id) on delete cascade,
    primary key (user_id, role_id)
    constraint fk_user foreign key (user_id) references users(id) on delete cascade,
    constraint fk_role foreign key (role_id) references role(id) on delete cascade
);