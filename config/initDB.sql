create table if not exists public.resume
(
    uuid      text primary key not null,
    full_name text             not null
);

create table if not exists public.contact
(
    id          serial,
    resume_uuid text not null references public.resume (uuid) on delete cascade,
    type        text not null,
    value       text not null

);

create table if not exists public.section
(
    id          serial,
    resume_uuid text not null references public.resume (uuid) on delete cascade,
    typeSection        text not null,
    valueSection       text not null

);

create unique index if not exists contact_uuid_index
    on contact (resume_uuid, type);
