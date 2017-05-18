DROP TABLE IF EXISTS ProductCategory, Product, Supplier;

CREATE TABLE ProductCategory (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(35),
  description VARCHAR(255),
  department  VARCHAR(255)
);

CREATE TABLE Supplier (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(35),
  description VARCHAR(255)
);

CREATE TABLE Product (
  id                  SERIAL PRIMARY KEY,
  supplier_id         INT,
  product_category_id INT,
  name                VARCHAR(35),
  description         VARCHAR(255),
  currency            VARCHAR(10),
  default_price       FLOAT,
  FOREIGN KEY (supplier_id) REFERENCES Supplier (id),
  FOREIGN KEY (product_category_id) REFERENCES ProductCategory (id)
);

INSERT INTO productcategory (name, description, department) VALUES ('Tablet', 'Hardware',
                                                                    'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO productcategory (name, description, department)
VALUES ('Phone', 'Hardware', 'Moblie phones. Your mother can ask you what you eaten for lunch through these devices.');
INSERT INTO productcategory (name, description, department)
VALUES ('Notebook', 'Hardware', 'Like a tablet but with keyboard');

INSERT INTO Supplier (name, description) VALUES ('Amazon', 'Digital content and services');
INSERT INTO Supplier (name, description) VALUES ('Lenovo', 'Computers');

INSERT INTO Product (name, description, currency, default_price, supplier_id, product_category_id) VALUES
  ('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 'USD',
   49.9, (SELECT id
          FROM Supplier
          WHERE name = 'Amazon'), (SELECT id
                                   FROM ProductCategory
                                   WHERE name = 'Tablet'));
INSERT INTO Product (name, description, currency, default_price, supplier_id, product_category_id) VALUES
  ('Lenovo IdeaPad Miix 700',
   'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 'USD', 479,
   (SELECT id
    FROM Supplier
    WHERE name = 'Lenovo'), (SELECT id
                             FROM ProductCategory
                             WHERE name = 'Tablet'));
INSERT INTO Product (name, description, currency, default_price, supplier_id, product_category_id) VALUES
  ('Super Telephone Lenovo 3000',
   'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 'USD', 90,
   (SELECT id
    FROM Supplier
    WHERE name = 'Lenovo'), (SELECT id
                             FROM ProductCategory
                             WHERE name = 'Phone'));
