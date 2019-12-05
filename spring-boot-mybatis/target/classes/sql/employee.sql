use demo;
drop table employee;
create table employee(
	id int(11) auto_increment primary key,
    lastName varchar(25),
    email varchar(25),
    gender varchar(4),
    d_id int(11),
    constraint d_id foreign key(d_id) references department(id)
)engine=InnoDB char set=utf8mb4;