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

CREATE UNIQUE INDEX contact_uuid_type_index
    ON contact (resume_uuid, type);

CREATE UNIQUE INDEX section_idx
    ON section (resume_uuid, typeSection);

INSERT INTO resume (uuid, full_name)
VALUES ('7de882da-02f2-4d16-8daa-60660aaf4071', 'Name1'),
       ('a97b3ac3-3817-4c3f-8a5f-178497311f1d', 'Name2'),
       ('dd0a70d1-5ed3-479a-b452-d5e04f21ca73', 'Name3');

insert into contact (resume_uuid, type, value)
values ('7de882da-02f2-4d16-8daa-60660aaf4071', 'PHONE', '89874561252'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'SKYPE', 'skype'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'MAIL', '123@gmail.com'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'LINKEDIN', 'LINKEDIN'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'GITHUB', 'GITHUB'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'STACKOVERFLOW', 'STACKOVERFLOW'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'HOMEPAGE', 'www.myPage.com');

INSERT INTO section (resume_uuid, typeSection, valueSection)
VALUES ('7de882da-02f2-4d16-8daa-60660aaf4071', 'OBJECTIVE', 'position'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'PERSONAL', 'personal'),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'ACHIEVEMENT', CONCAT('One', CHR(10), 'Two', CHR(10), 'Three')),
       ('7de882da-02f2-4d16-8daa-60660aaf4071', 'QUALIFICATIONS', CONCAT('Three', CHR(10), 'Four', CHR(10), 'Five'));