DROP TABLE IF EXISTS ProductCategory;

CREATE TABLE ProductCategory (
  id INT PRIMARY KEY,
  name VARCHAR(15),
  description VARCHAR(50),
  department VARCHAR(15)
);