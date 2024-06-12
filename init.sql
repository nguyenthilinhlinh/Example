-- create students db
CREATE TABLE students (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(255) NOT NULL,
    birthday DATE NOT NULL,
    address NVARCHAR(255),
    gender TINYINT 
);