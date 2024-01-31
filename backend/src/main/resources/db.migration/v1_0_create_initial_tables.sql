CREATE TABLE member (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE
);
INSERT INTO member(id, name, email, password, birthday, username)
VALUES(1, 'pedro perez', 'pedroperez@test.com', 'password', '02/06/1999', 'pedrox')