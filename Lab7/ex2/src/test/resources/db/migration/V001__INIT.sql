CREATE TABLE tqs_employee
(
    id    BIGSERIAL PRIMARY KEY,
    name  varchar(25) NOT NULL,
    email varchar(50) NOT NULL,
    unique (email)
);

insert into tqs_employee(name, email)
values ('Pedro', 'pedro@something.com');
insert into tqs_employee(name, email)
values ('Jose', 'ze@something.com');
insert into tqs_employee(name, email)
values ('Diogo', 'di@something.com');
insert into tqs_employee(name, email)
values ('Armindo', 'armindo@something.com');