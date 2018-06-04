CREATE TABLE company
(
  uuid           VARCHAR(64)  NOT NULL
    PRIMARY KEY,
  name           VARCHAR(512) NULL,
  status         VARCHAR(16)  NULL,
  url_time_stamp DATETIME     NULL
)
  ENGINE = InnoDB;

CREATE INDEX company_uuid_index
  ON company (uuid);

CREATE TABLE info_card
(
  guid         VARCHAR(128)  NOT NULL
    PRIMARY KEY,
  first_name   VARCHAR(64)   NOT NULL,
  last_name    VARCHAR(64)   NOT NULL,
  patronymic   VARCHAR(64)   NULL,
  url          VARCHAR(1024) NULL,
  created_date DATETIME      NOT NULL,
  parsed_date  DATETIME      NULL,
  CONSTRAINT info_card_id_uindex
  UNIQUE (guid)
)
  ENGINE = InnoDB;

CREATE TABLE infocard_company
(
  infocard_guid VARCHAR(128) NULL,
  company_uuid  VARCHAR(64)  NULL,
  CONSTRAINT infocard_company_info_card_guid_fk
  FOREIGN KEY (infocard_guid) REFERENCES info_card (guid),
  CONSTRAINT infocard_company_company_uuid_fk
  FOREIGN KEY (company_uuid) REFERENCES company (uuid)
)
  ENGINE = InnoDB;

CREATE INDEX infocard_company_info_card_guid_fk
  ON infocard_company (infocard_guid);

CREATE INDEX infocard_company_company_uuid_fk
  ON infocard_company (company_uuid);

CREATE TABLE search_results
(
  id                INT AUTO_INCREMENT
    PRIMARY KEY,
  recepient_name    VARCHAR(128)  NULL,
  recepient_address VARCHAR(128)  NULL,
  sender_name       VARCHAR(128)  NULL,
  sender_address    VARCHAR(128)  NULL,
  freight_desc      TEXT          NULL,
  url               VARCHAR(1024) NULL,
  parse_time_stamp  DATETIME      NULL,
  status            VARCHAR(16)   NULL,
  error_message     TEXT          NULL,
  company_uuid      VARCHAR(64)   NULL,
  CONSTRAINT search_results_id_uindex
  UNIQUE (id),
  CONSTRAINT search_results_company_uuid_fk
  FOREIGN KEY (company_uuid) REFERENCES company (uuid)
)
  ENGINE = InnoDB;

CREATE INDEX search_results_company_uuid_fk
  ON search_results (company_uuid);

