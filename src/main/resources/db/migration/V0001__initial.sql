create table users
(
    id text not null
        constraint users_pk
            primary key,
    name text default 'user'::text not null,
    avatar_uri text not null,
    password text default '0'::text not null,
    email text default 'test@gmail.com'::text not null
);


create unique index users_id_uindex
	on users (id);

create unique index users_email_uindex
	on users (email);


create table likes
(
    "from" text not null
        constraint likes_users_fk
            references users,
    "to" text not null
        constraint likes_users_fk2
            references users,
    "like" boolean not null
);

create table messages
(
    "from" text not null
        constraint messages_users_id_fk
            references users,
    "to" text not null
        constraint messages_users_id_fk_2
            references users,
    text text,
    date timestamp not null
);







