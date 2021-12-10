CREATE TABLE user
(
    id        INTEGER PRIMARY KEY NOT NULL,
    username  VARCHAR(25),
    firstName VARCHAR(25),
    lastName  VARCHAR(25),
    password  VARCHAR
);

CREATE TABLE schoolYear
(
    id   INTEGER PRIMARY KEY NOT NULL,
    year varchar(25) not null
);

CREATE TABLE gradeType
(
    id        INTEGER PRIMARY KEY NOT NULL,
    gradeType VARCHAR(25)
);

CREATE TABLE subject
(
    id      INTEGER PRIMARY KEY NOT NULL,
    subject VARCHAR(25)
);

CREATE TABLE grade
(
    id             INTEGER PRIMARY KEY NOT NULL,
    gradTypeId     INTEGER             NOT NULL,
    subjectId      INTEGER             NOT NULL,
    grade          INTEGER,
    prioritisation INTEGER
);

alter table grade
    add foreign key (gradTypeId) references gradeType (id);

alter table grade
    add foreign key (subjectId) references subject (id);

CREATE TABLE userSchoolYear
(
    id     INTEGER PRIMARY KEY NOT NULL,
    userId INTEGER             NOT NULL,
    yearId INTEGER             not null
);

alter table userSchoolYear
    add foreign key (userId) references user (id);
alter table userSchoolYear
    add foreign key (yearId) references schoolYear (id);

CREATE TABLE userGrade
(
    id               INTEGER PRIMARY KEY NOT NULL,
    userSchoolYearId INTEGER             NOT NULL,
    gradeId          INTEGER             not null
);

alter table userGrade
    add foreign key (userSchoolYearId) references userSchoolYear (id);
alter table userGrade
    add foreign key (gradeId) references grade (id);

--Insert
INSERT INTO user
VALUES (1, 'user', 'user', 'user', 'user');
INSERT INTO user
VALUES (2, 'admin', 'admin', 'admin', 'admin');

INSERT into schoolYear
values (1, '1. Klasse');
INSERT into schoolYear
values (2, '2. Klasse');
INSERT into schoolYear
values (3, '3. Klasse');
INSERT into schoolYear
values (4, '4. Klasse');
INSERT into schoolYear
values (5, '5. Klasse');
INSERT into schoolYear
values (6, '6. Klasse');
INSERT into schoolYear
values (7, '7. Klasse');
INSERT into schoolYear
values (8, '8. Klasse');
INSERT into schoolYear
values (9, '9. Klasse');
INSERT into schoolYear
values (10, '10. Klasse');

insert into subject
values (1, 'Volkswirtschaftslehre');
insert into subject
values (2, 'Betriebswirtschaftslehre');
insert into subject
values (3, 'Informationsverarbeitung');
insert into subject
values (4, 'Mathematik');
insert into subject
values (5, 'Biologie');
insert into subject
values (6, 'Chemie');
insert into subject
values (7, 'Physik');
insert into subject
values (8, 'Englisch');
insert into subject
values (9, 'Ethik');
insert into subject
values (10, 'Religion');
insert into subject
values (11, 'Deutsch');
insert into subject
values (12, 'Spanisch');
insert into subject
values (13, 'Franzoesisch');
insert into subject
values (14, 'Kunst');
insert into subject
values (15, 'Sport');

insert into gradeType
values (1, 'Test');
insert into gradeType
values (2, 'Projekt');
insert into gradeType
values (3, 'Klausur');
insert into gradeType
values (4, 'Epochalnote');

insert into userSchoolYear
values (1, 1, 8);
insert into userSchoolYear
values (2, 1, 9);
insert into userSchoolYear
values (3, 2, 8);

insert into grade
values (1, 1, 5, 5, 0.25);
insert into grade
values (2, 1, 5, 5, 0.25);
insert into grade
values (3, 3, 5, 5, 0.5);
insert into grade
values (4, 4, 5, 4, 0.25);

insert into userGrade
values (1, 1, 1);
insert into userGrade
values (2, 1, 2);
insert into userGrade
values (3, 1, 3);
insert into userGrade
values (4, 2, 1);
insert into userGrade
values (5, 2, 2);
insert into userGrade
values (6, 2, 3);


