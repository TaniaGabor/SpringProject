/*tutorial 1,2,3,4,5*/
show databases;
create database tutorial1;
use tutorial1;
create table users(username text);
desc users;
insert into users(username) values ("Bob");
insert into users(username) values ("Vicky");
select * from users;
/*tutorial 6*/
drop table users;
create table users(id int,username text);
insert into users(id,username) values (1,"Bob");
insert into users(id,username) values (2,"Vicky");
insert into users(id,username) values (3,null);
insert into users(id,username) values (null,null);
select* from users LIMIT 0,1000;
insert into users (id) values (4);
create table users1 (id int not null,username text not null);
insert into users(username) values ("Someone");
/*tutorial 7*/
show engines;
show table status;
create table test(id int) engine=MYISAM;
show table status;
drop table test;
set default_storage_engine=MYISAM;
show table status;
show engines;
set default_storage_engine=INNODB;

/*tutorial 8 ,9*/
select @GLOBAL.SQL_MODE;
drop table users;
create table users(id int,username text);
insert into users(id,username) values (1,"Bob");
insert into users(id,username) values (2,"Vicky");
select *from users;
set sql_safe_updates=0;
delete from users;
/*tutorial 10*/  
drop table users;
create table users(id int primary key ,name text,email text);
insert into users(id,name,email) values (1,"Bob","ahhags");
/*tutorial 11 */
drop table users;
create table users(id int primary key auto_increment ,name text);
insert into users(name) values  ('Bob');
insert into users(name) values  ('Tania');



