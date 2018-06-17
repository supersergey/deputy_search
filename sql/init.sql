create table company
(
  uuid varchar(64) not null
    primary key,
  name varchar(512) not null,
  status varchar(16) null,
  url_time_stamp datetime null,
  constraint company_name_uindex
  unique (name)
)
  engine=InnoDB
;

create index company_uuid_index
  on company (uuid)
;

create table info_card
(
  guid varchar(128) not null
    primary key,
  first_name varchar(64) not null,
  last_name varchar(64) not null,
  patronymic varchar(64) not null,
  url varchar(1024) null,
  created_date datetime not null,
  parsed_date datetime null,
  constraint info_card_id_uindex
  unique (guid),
  constraint uq_owner
  unique (first_name, patronymic, last_name)
)
  engine=InnoDB
;

create table infocard_company
(
  infocard_guid varchar(128) null,
  company_uuid varchar(64) null,
  constraint infocard_company_info_card_guid_fk
  foreign key (infocard_guid) references info_card (guid),
  constraint infocard_company_company_uuid_fk
  foreign key (company_uuid) references company (uuid)
)
  engine=InnoDB
;

create index infocard_company_info_card_guid_fk
  on infocard_company (infocard_guid)
;

create index infocard_company_company_uuid_fk
  on infocard_company (company_uuid)
;

create table search_results
(
  id int auto_increment
    primary key,
  recepient_name varchar(128) null,
  recepient_address varchar(128) null,
  sender_name varchar(128) null,
  sender_address varchar(128) null,
  freight_desc text null,
  url varchar(1024) null,
  parse_time_stamp datetime null,
  status varchar(16) null,
  error_message text null,
  company_uuid varchar(64) null,
  constraint search_results_id_uindex
  unique (id),
  constraint search_results_company_uuid_fk
  foreign key (company_uuid) references company (uuid)
)
  engine=InnoDB
;

create index search_results_company_uuid_fk
  on search_results (company_uuid)
;

