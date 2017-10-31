mysql -u root -p
123456

drop database bdb;
create database bdb;
use bdb;

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30),
  `area` varchar(30),
  `pin` varchar(30),
  `email` varchar(30),
  `mob` varchar(30),
  `age` varchar(30),
  `bloodtype` varchar(30) NOT NULL,
  `sex` varchar(30),  
  PRIMARY KEY  (`username`)
) ;


CREATE TABLE IF NOT EXISTS `donations` (
  `did` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `donatedDate` datetime, 
  PRIMARY KEY  (`did`)
) ;

CREATE TABLE IF NOT EXISTS `feeds` (
  `fid` int unsigned NOT NULL AUTO_INCREMENT,
  `comment` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `commentDate` datetime, 
  PRIMARY KEY  (`fid`)
) ;

INSERT INTO users (`username`,`password`,`name`,`area`,`pin`,`email`,`mob`,`age`,`bloodtype`,`sex`) VALUES('u1','1234','u1','areaA','520011','u1@u.com','9999999991','25','a+','male');

INSERT INTO users (`username`,`password`,`name`,`area`,`pin`,`email`,`mob`,`age`,`bloodtype`,`sex`) VALUES ('u2','1234','u2','areaB','520008','u2@u.com','9999999992','30','a+','female');

INSERT INTO users (`username`,`password`,`name`,`area`,`pin`,`email`,`mob`,`age`,`bloodtype`,`sex`) VALUES ('u3','1234','u3','areaA','520011','u3@u.com','9999999993','45','a+','male');

INSERT INTO users (`username`,`password`,`name`,`area`,`pin`,`email`,`mob`,`age`,`bloodtype`,`sex`) VALUES ('u4','1234','u4','areaA','520011','u4@u.com','9999999994','35','a+','female');

