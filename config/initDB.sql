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
    id           serial,
    resume_uuid  text not null references public.resume (uuid) on delete cascade,
    typeSection  text not null,
    valueSection text not null

);

create unique index if not exists contact_uuid_index
    on contact (resume_uuid, type);

insert into resume (uuid, full_name)
values ('uuid1', 'Garry'),
       ('uuid2', 'Tom'),
       ('uuid3', 'Tim');

insert into contact (resume_uuid, type, value)
values ('uuid1', 'PHONE', '89874561252'),
       ('uuid1', 'SKYPE', 'skype'),
       ('uuid1', 'MAIL', '123@gmail.com'),
       ('uuid1', 'LINKEDIN', 'LINKEDIN'),
       ('uuid1', 'GITHUB', 'GITHUB'),
       ('uuid1', 'STACKOVERFLOW', 'STACKOVERFLOW'),
       ('uuid1', 'HOMEPAGE', 'www.myPage.com');

INSERT INTO section (resume_uuid, typeSection, valueSection)
VALUES ('uuid1', 'OBJECTIVE', 'position'),
       ('uuid1', 'PERSONAL', 'personal'),
       ('uuid1', 'ACHIEVEMENT', CONCAT('One', CHR(10), 'Two', CHR(10), 'Three')),
       ('uuid1', 'QUALIFICATIONS', CONCAT('Three', CHR(10), 'Four', CHR(10), 'Five'));