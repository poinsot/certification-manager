-- MySQL Script generated by MySQL Workbench
-- 05/25/16 10:43:50
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema certif_manager
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `certif_manager` ;

-- -----------------------------------------------------
-- Schema certif_manager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `certif_manager` DEFAULT CHARACTER SET latin1 ;
USE `certif_manager` ;

-- -----------------------------------------------------
-- Table `certif_manager`.`candidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`candidate` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `lastname` VARCHAR(100) NOT NULL ,
  `firstname` VARCHAR(100) NOT NULL ,
  `id_card_number` VARCHAR(12) NOT NULL UNIQUE,
  `mail` VARCHAR(255) NOT NULL UNIQUE,
  `pwd` VARCHAR(255) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `inscription_validate` TINYINT(1) NOT NULL DEFAULT '0' ,
  `validation_code` VARCHAR(65) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `certif_manager`.`trainer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`trainer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `lastname` VARCHAR(100) NOT NULL ,
  `firstname` VARCHAR(100) NOT NULL ,
  `mail` VARCHAR(255) NOT NULL ,
  `pwd` VARCHAR(255) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `inscription_validate` TINYINT(1) NOT NULL DEFAULT '0' ,
  `validation_code` VARCHAR(65) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC)  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `certif_manager`.`certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`certification` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(65) NOT NULL,
  `percent_success` TINYINT(1) NOT NULL ,
  `nb_question` SMALLINT(6) NOT NULL ,
  `duration` INT(11) NOT NULL ,
  `description` TEXT NULL DEFAULT NULL ,
  `id_trainer` INT(11) NOT NULL ,
  PRIMARY KEY (`id`)  ,
  INDEX `id_trainer_idx` (`id_trainer` ASC)  ,
  CONSTRAINT `certif_fk_trainer`
    FOREIGN KEY (`id_trainer`)
    REFERENCES `certif_manager`.`trainer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `certif_manager`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`question` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `text` TEXT NOT NULL ,
  `id_certif` INT(11) NOT NULL ,
  PRIMARY KEY (`id`)  ,
  INDEX `question_fk_certif_idx` (`id_certif` ASC)  ,
  CONSTRAINT `question_fk_certif`
    FOREIGN KEY (`id_certif`)
    REFERENCES `certif_manager`.`certification` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `certif_manager`.`responses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`responses` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_question` INT(11) NOT NULL ,
  `text` TINYTEXT NOT NULL ,
  `is_correct` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`)  ,
  INDEX `response_fk_question_idx` (`id_question` ASC)  ,
  CONSTRAINT `response_fk_question`
    FOREIGN KEY (`id_question`)
    REFERENCES `certif_manager`.`question` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `certif_manager`.`vouchers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`vouchers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `id_candidate` INT(11) NOT NULL ,
  `id_certif` INT(11) NOT NULL ,
  `validate` TINYINT(1) NOT NULL DEFAULT '1' ,
  `date_creation` DATE NOT NULL ,
  `voucher` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`)  ,
  UNIQUE INDEX `voucher_UNIQUE` (`voucher` ASC)  ,
  INDEX `voucher_fk_candidate_idx` (`id_candidate` ASC)  ,
  INDEX `voucher_fk_certif_idx` (`id_certif` ASC)  ,
  CONSTRAINT `voucher_fk_candidate`
    FOREIGN KEY (`id_candidate`)
    REFERENCES `certif_manager`.`candidate` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `voucher_fk_certif`
    FOREIGN KEY (`id_certif`)
    REFERENCES `certif_manager`.`certification` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE USER 'cm_app' IDENTIFIED BY 'cm_app';

GRANT SELECT, INSERT, TRIGGER ON TABLE `mydb`.* TO 'cm_app';
GRANT SELECT, INSERT, TRIGGER ON TABLE `certif_manager`.* TO 'cm_app';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `mydb`.* TO 'cm_app';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `certif_manager`.* TO 'cm_app';
GRANT SELECT ON TABLE `mydb`.* TO 'cm_app';
GRANT SELECT ON TABLE `certif_manager`.* TO 'cm_app';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
