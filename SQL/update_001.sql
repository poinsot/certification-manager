use certif_manager;
ALTER TABLE candidate CHANGE COLUMN name lastname varchar(100) NOT NULL;
ALTER TABLE candidate CHANGE COLUMN mail mail VARCHAR(255) NOT NULL UNIQUE;
ALTER TABLE candidate CHANGE COLUMN id_card_number id_card_number VARCHAR(12) NOT NULL UNIQUE;
ALTER TABLE candidate CHANGE COLUMN validation_code validation_code VARCHAR(65) NOT NULL UNIQUE;

ALTER TABLE certification ADD title VARCHAR(255) NOT NULL;

ALTER TABLE trainer CHANGE COLUMN validation_code validation_code VARCHAR(65) NOT NULL UNIQUE;




