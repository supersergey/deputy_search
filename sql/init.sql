CREATE TABLE company
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(1024) NOT NULL,
    status VARCHAR(16),
    url VARCHAR(1024),
    info_card VARCHAR(128),
    error_message VARCHAR(2048),
    url_time_stamp DATETIME,
    parse_time_stamp DATETIME
);
CREATE TABLE info_card
(
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    patronymic VARCHAR(64),
    url VARCHAR(1024),
    column_6 INT(11),
    created_date DATETIME NOT NULL,
    parsed_date DATETIME,
    guid VARCHAR(128) PRIMARY KEY NOT NULL
);

CREATE TABLE search_results
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    recepient_name VARCHAR(128),
    recepient_address VARCHAR(128),
    sender_name VARCHAR(128),
    sender_address VARCHAR(128),
    freight_desc VARCHAR(2048),
    company_id INT(11)
);
ALTER TABLE company ADD FOREIGN KEY (info_card) REFERENCES info_card (guid);
CREATE INDEX fk_info_card ON company (info_card);
CREATE UNIQUE INDEX company_id_uindex ON company (id);
CREATE UNIQUE INDEX info_card_id_uindex ON info_card (guid);
ALTER TABLE search_results ADD FOREIGN KEY (company_id) REFERENCES company (id);
CREATE INDEX search_results_company_id_fk ON search_results (company_id);
CREATE UNIQUE INDEX search_results_id_uindex ON search_results (id);