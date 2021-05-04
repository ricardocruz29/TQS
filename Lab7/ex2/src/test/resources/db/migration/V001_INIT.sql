CREATE TABLE tqs_employee (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(25) NOT NULL,
    email varchar(25) NOT NULL,
    unique (email),
    PRIMARY KEY (id),
);

insert into tqs_employee(id, name, email) values (1, 'Pedro', 'pedro@something.com');
insert into tqs_employee(id, name, email) values (2, 'Jose', 'ze@something.com');
insert into tqs_employee(id, name, email) values (3, 'Diogo', 'di@something.com');
insert into tqs_employee(id, name, email) values (4, 'Armindo', 'armindo@something.com');