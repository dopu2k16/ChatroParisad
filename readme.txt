Steps To Run The Project
step1)Within Oracle Database execute the following sql statement.
______________________________________________
drop table Student;
create table Student
(
sno int primary key,
sname varchar(30),
sadd varchar(30));

drop table User_Details;
create table User_Details
(
userName varchar(20) primary key,
password varchar(30));
insert into User_Details values('admin','adminadmin');
insert into User_Details values('tomcat','s3cret');
commit;
select * from User_Details;
------------------------------------------------------

step2)Place ojdbc14.jar under "C:\Program Files\Java\jre8\lib\ext"

Step 3) Run this GUI project for Student Database System