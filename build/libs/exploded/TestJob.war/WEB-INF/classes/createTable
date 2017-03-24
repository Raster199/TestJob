/*DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS users;*/

CREATE SEQUENCE IF NOT EXISTS users_sq;

CREATE TABLE IF NOT EXISTS users(
ID INTEGER PRIMARY KEY DEFAULT nextval('users_sq'),
login varchar (25),
pass varchar(32)
);
ALTER SEQUENCE users_sq OWNED BY users.ID;

INSERT INTO USERS (login, pass) values ('petrov', 'f396c3b74762b1fee69b10abb875139b');-- petrov
INSERT INTO USERS (login, pass) values ('ivanov', '4dfe6e220d16e7b633cfdd92bcc8050b');-- ivanov

CREATE SEQUENCE IF NOT EXISTS staff_sq;

CREATE TABLE IF NOT EXISTS staff(
ID INTEGER PRIMARY KEY DEFAULT nextval('staff_sq'),
firstName varchar (20),
lastName varchar(20)
);
ALTER SEQUENCE staff_sq OWNED BY staff.ID;

INSERT INTO staff (firstName, lastName) VALUES ('Иван', 'Петров');
INSERT INTO staff (firstName, lastName) VALUES ('Алексей', 'Серов');
INSERT INTO staff (firstName, lastName) VALUES ('Максим', 'Горький');
INSERT INTO staff (firstName, lastName) VALUES ('Сергей', 'Иванов');

SELECT * FROM staff;
SELECT * FROM USERS;

