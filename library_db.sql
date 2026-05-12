CREATE DATABASE library_db;

USE library_db;

CREATE TABLE author_tb (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

CREATE TABLE book_tb (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    edition VARCHAR(50),
    price DOUBLE,
    author_id INT,
    
    FOREIGN KEY (author_id)
    REFERENCES author_tb(id)
);