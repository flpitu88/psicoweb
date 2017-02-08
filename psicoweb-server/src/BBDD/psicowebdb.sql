-- MySQL Script generated by MySQL Workbench
-- mié 08 feb 2017 00:57:21 ART
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema PsicoWebDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema PsicoWebDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `PsicoWebDB` DEFAULT CHARACTER SET utf8 ;
USE `PsicoWebDB` ;

-- -----------------------------------------------------
-- Table `PsicoWebDB`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PsicoWebDB`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `PsicoWebDB`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(9) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(60) NOT NULL,
  `mail` VARCHAR(100) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `administrador` BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO root;
 DROP USER root;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'root' IDENTIFIED BY 'psicoweb201701';

GRANT ALL ON `PsicoWebDB`.* TO 'root';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO psicowebserver;
 DROP USER psicowebserver;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'psicowebserver' IDENTIFIED BY 'pwserver2017';

GRANT SELECT, INSERT, TRIGGER ON TABLE `PsicoWebDB`.* TO 'psicowebserver';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `PsicoWebDB`.* TO 'psicowebserver';
GRANT SELECT ON TABLE `PsicoWebDB`.* TO 'psicowebserver';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
