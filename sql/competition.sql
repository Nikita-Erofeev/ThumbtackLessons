DROP DATABASE IF EXISTS competition;
CREATE DATABASE `competition`;
USE `competition`;
SET SQL_SAFE_UPDATES = 0;

CREATE TABLE user (
    id INT(11) NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    companyname VARCHAR(50) NULL,
    login VARCHAR(50) NOT NULL,
    pass VARCHAR(50) NOT NULL,
    UNIQUE(login),
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8
COLLATE utf8_general_ci;

CREATE TABLE subject (
	id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    KEY name (name),
    UNIQUE (name),
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8
COLLATE utf8_general_ci;

CREATE TABLE application (
	id INT(11) NOT NULL AUTO_INCREMENT,
    id_member INT(11) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(256) NOT NULL,
    amountrequested INT(11) NOT NULL,
    KEY name (name),
    KEY id_member (id_member),
    UNIQUE (id_member, name),
    PRIMARY KEY (id),
    FOREIGN KEY (id_member) REFERENCES user (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8
COLLATE utf8_general_ci;

CREATE TABLE expert_subject (
	id INT(11) NOT NULL AUTO_INCREMENT,
    id_expert INT(11) NOT NULL,
    id_subject INT(11) NOT NULL,
    KEY id_subject (id_subject),
    KEY id_expert (id_expert),
    UNIQUE (id_expert, id_subject),
    PRIMARY KEY (id),
    FOREIGN KEY (id_subject) REFERENCES subject (id) ON DELETE CASCADE,
    FOREIGN KEY (id_expert) REFERENCES user (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8
COLLATE utf8_general_ci;

CREATE TABLE rating (
	id INT(11) NOT NULL AUTO_INCREMENT,
    id_expert INT(11) NOT NULL,
    id_application INT(11) NOT NULL,
    rating INT(11) NOT NULL,
    KEY id_expert (id_expert),
    KEY id_application (id_application),
    UNIQUE (id_expert,id_application),
    FOREIGN KEY (id_application) REFERENCES application (id) ON DELETE CASCADE,
    FOREIGN KEY (id_expert) REFERENCES user (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8
COLLATE utf8_general_ci;

CREATE TABLE application_subject (
	id INT(11) NOT NULL AUTO_INCREMENT,
    id_application INT(11) NOT NULL,
    id_subject INT(11) NOT NULL,
    PRIMARY KEY (id),
    KEY id_application (id_application),
    KEY id_subject (id_subject),
	FOREIGN KEY (id_subject) REFERENCES subject (id) ON DELETE CASCADE,
    FOREIGN KEY (id_application) REFERENCES application (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8
COLLATE utf8_general_ci;