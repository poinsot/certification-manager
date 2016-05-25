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
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `firstname` VARCHAR(100) NOT NULL COMMENT '',
  `id_card_number` VARCHAR(12) NOT NULL COMMENT '',
  `mail` VARCHAR(255) NOT NULL COMMENT '',
  `pwd` VARCHAR(255) NOT NULL COMMENT '',
  `birthdate` DATE NOT NULL COMMENT '',
  `inscription_validate` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `certif_manager`.`trainer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`trainer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `lastname` VARCHAR(100) NOT NULL COMMENT '',
  `firstname` VARCHAR(100) NOT NULL COMMENT '',
  `mail` VARCHAR(255) NOT NULL COMMENT '',
  `pwd` VARCHAR(255) NOT NULL COMMENT '',
  `birthdate` DATE NOT NULL COMMENT '',
  `inscription_validate` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `certif_manager`.`certification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `certif_manager`.`certification` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `percent_success` TINYINT(1) NOT NULL COMMENT '',
  `nb_question` SMALLINT(6) NOT NULL COMMENT '',
  `duration` INT(11) NOT NULL COMMENT '',
  `description` TEXT NULL DEFAULT NULL COMMENT '',
  `id_trainer` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `id_trainer_idx` (`id_trainer` ASC)  COMMENT '',
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
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `text` TEXT NOT NULL COMMENT '',
  `id_certif` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `question_fk_certif_idx` (`id_certif` ASC)  COMMENT '',
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
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `id_question` INT(11) NOT NULL COMMENT '',
  `text` TINYTEXT NOT NULL COMMENT '',
  `is_correct` TINYINT(1) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `response_fk_question_idx` (`id_question` ASC)  COMMENT '',
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
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `id_candidate` INT(11) NOT NULL COMMENT '',
  `id_certif` INT(11) NOT NULL COMMENT '',
  `validate` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '',
  `date_creation` DATE NOT NULL COMMENT '',
  `voucher` VARCHAR(255) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `voucher_UNIQUE` (`voucher` ASC)  COMMENT '',
  INDEX `voucher_fk_candidate_idx` (`id_candidate` ASC)  COMMENT '',
  INDEX `voucher_fk_certif_idx` (`id_certif` ASC)  COMMENT '',
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
