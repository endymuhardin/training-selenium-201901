create table customer (
    id varchar(36),
    fullname varchar(255) not null,
    email varchar(100) not null,
    mobile_phone varchar(20) not null,
    birthdate date not null,
    sex varchar(10) not null,
    education varchar(10),
    primary key (id),
    unique (email),
    unique (mobile_phone)
) Engine=InnoDB;
