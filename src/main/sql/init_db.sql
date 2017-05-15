DROP TABLE IF EXISTS ProductCategory, Product, Supplier;

CREATE TABLE ProductCategory (
  id SERIAL PRIMARY KEY,
  name VARCHAR(35),
  description VARCHAR(255),
  department VARCHAR(255)
);

CREATE TABLE Supplier (
  id SERIAL PRIMARY KEY,
  name VARCHAR(35),
  description VARCHAR(255)
);

CREATE TABLE Product(
  id SERIAL PRIMARY KEY,
  supplier_id INT,
  product_category_id INT,
  name VARCHAR(35),
  description VARCHAR(255),
  currency VARCHAR(10),
  default_price FLOAT,
  FOREIGN KEY (supplier_id) REFERENCES Supplier (id),
  FOREIGN KEY(product_category_id) REFERENCES ProductCategory (id)
);