create database student_manager;

use student_manager;

create table student
(
    id    int primary key auto_increment,
    name  varchar(50),
    score double
);

insert into student (name, score) VALUES ('Nguyễn Văn A', 9.6);
insert into student (name, score) VALUES ('Nguyễn Văn B', 9.0);
insert into student (name, score) VALUES ('Nguyễn Văn C', 5.6);

select id, name, score from student where id = ?;
insert into student (name, score) VALUES (?, ?);