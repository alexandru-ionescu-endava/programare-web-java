CREATE SCHEMA test_schema;

create table book
(
    id     serial primary key,
    title  varchar(30),
    author varchar(30),
    pages  int
);

insert into book (title, author, pages)
values ('title_4', 'author_4', 40);

select *
from book;

select author, count(*)
from book
group by author;