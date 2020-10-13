
drop database if exists books;
create database if not exists books;
use books;
SET GLOBAL time_zone = '+3:00';CREATE TABLE IF NOT EXISTS books(
   Id int(11) ,
  name_ varchar(30) DEFAULT NULL,
  author varchar(30) DEFAULT NULL,
  publishedData varchar(30) DEFAULT NULL,
  isAvailable boolean,
  PRIMARY KEY (Id));
  
  insert into books(id,name_,author,publishedData,isAvailable)
values  (1,"Stalpii Pamantului","Ken Follet","12.03.1999",true),
(2,"O avere periculoasa","Ken Follet","12.02.1999",true),
(3,"Vanatorii de Zmeie","Khaled Hosseini","12.03.1998",true),
(4,"Cel care ma asteapta","Parinoush Saniee","12.01.1999",true);
  
select* from books;