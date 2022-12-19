DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS category;

CREATE TABLE user (
  id INT AUTO_INCREMENT,
  creation_time DATETIME,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_name (name)
);

CREATE TABLE listing (
  id INT AUTO_INCREMENT,
  creation_time DATETIME,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(1000),
  price DECIMAL(20, 2) NOT NULL,
  username VARCHAR(50) NOT NULL,
  category VARCHAR(100) NOT NULL,
  is_delete TINYINT NOT NULL default 0,
  PRIMARY KEY (id)
);