drop database if exists senla_kononovich_bookshop;

CREATE DATABASE IF NOT EXISTS senla_kononovich_bookshop;

USE senla_kononovich_bookshop;

CREATE TABLE IF NOT EXISTS book(
	id_book INT NOT NULL auto_increment,
    name NVARCHAR(30) NOT NULL,
    cost INT NOT NULL,
    bookCount INT,
    publicationDate DATE NOT NULL,
    receiptDate DATE,
    PRIMARY KEY (id_book)
);

CREATE TABLE IF NOT EXISTS _order(
	id_order INT NOT NULL PRIMARY KEY auto_increment,
    id_book INT NOT NULL,
    client NVARCHAR(50),
    executionDate DATE,
    orderStatus CHAR(1),
    FOREIGN KEY (id_book) REFERENCES book(id_book)
);

CREATE TABLE IF NOT EXISTS claim(
	id_claim INT NOT NULL PRIMARY KEY auto_increment,
    book NVARCHAR(30) NOT NULL,
    claimStatus CHAR(1)
);