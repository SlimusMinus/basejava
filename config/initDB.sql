create table public.resume
(
    uuid      text primary key not null,
    full_name text             not null
);

create table public.contact
(
    id          serial,
    resume_uuid text not null references public.resume (uuid) on delete cascade,
    type        text not null,
    value       text not null

);

create unique index if not exists contact_uuid_index
    on contact (resume_uuid, type);
