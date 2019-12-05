use demo;
drop table department;
create table department(
	id int(11) auto_increment primary key,
    dname varchar(25)
)engine=InnoDB char set=utf8mb4;