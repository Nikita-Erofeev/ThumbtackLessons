DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`;
USE `ttschool`;

CREATE TABLE trainee (
    id INT(11) NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    rating int(1) DEFAULT NULL,
    KEY firstname (firstname),
    KEY lastname (lastname),
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE subject (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    KEY name (name),
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE groups (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    room VARCHAR(50) DEFAULT NULL,
    KEY name (name),
    KEY room (room),
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE school (
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    year INT(4) NOT NULL,
    KEY name (name),
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE trainee_group(
	id INT(11) NOT NULL AUTO_INCREMENT,
    groupid INT(11) NOT NULL,
    traineeid INT(11) NOT NULL,
    UNIQUE (groupid, traineeid),
    PRIMARY KEY (id),
    FOREIGN KEY (traineeid) REFERENCES trainee (id) ON DELETE CASCADE,
    FOREIGN KEY (groupid) REFERENCES groups (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE subject_group(
	id INT(11) NOT NULL AUTO_INCREMENT,
    subjectid INT(11) NOT NULL,
    groupid INT(11) NOT NULL,
    UNIQUE (subjectid, groupid),
    PRIMARY KEY (id),
    FOREIGN KEY (subjectid) REFERENCES subject (id) ON DELETE CASCADE,
    FOREIGN KEY (groupid) REFERENCES groups (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE school_group(
	id INT(11) NOT NULL AUTO_INCREMENT,
    schoolid INT(11) NOT NULL,
    groupid INT(11) NOT NULL,
    UNIQUE (schoolid, groupid),
    PRIMARY KEY (id),
    FOREIGN KEY (schoolid) REFERENCES school (id) ON DELETE CASCADE,
    FOREIGN KEY (groupid) REFERENCES groups (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;