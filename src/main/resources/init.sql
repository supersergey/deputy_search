use deputy_search;
CREATE TABLE info_card
(
  id VARCHAR(128) PRIMARY KEY NOT NULL,
  first_name VARCHAR(64) NOT NULL,
  last_name VARCHAR(64) NOT NULL,
  patronymic VARCHAR(64),
  url VARCHAR(1024),
  column_6 INT(11),
  created_date DATETIME NOT NULL,
  parsed_date DATETIME NOT NULL
);
CREATE UNIQUE INDEX info_card_id_uindex ON info_card (guid);
CREATE TABLE company
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(1024) NOT NULL,
  status VARCHAR(16),
  url VARCHAR(1024),
  info_card VARCHAR(128),
  CONSTRAINT fk_info_card FOREIGN KEY (info_card) REFERENCES info_card (guid)
);
CREATE INDEX fk_info_card ON company (info_card);
