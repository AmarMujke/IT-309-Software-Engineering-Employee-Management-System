-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema employee
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema employee
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `employee` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `employee` ;

-- -----------------------------------------------------
-- Table `employee`.`Departments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee`.`Departments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `manager` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `employee`.`Employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee`.`Employees` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `job_title` VARCHAR(255) NOT NULL,
  `department_id` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `hire_date` DATE NOT NULL,
  `salary` DECIMAL(10,2) NOT NULL,
  `admin` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_department_id` (`department_id` ASC) VISIBLE,
  CONSTRAINT `employees_ibfk_1`
    FOREIGN KEY (`department_id`)
    REFERENCES `employee`.`Departments` (`id`),
  CONSTRAINT `fk_department_id`
    FOREIGN KEY (`department_id`)
    REFERENCES `employee`.`Departments` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `employee`.`Attendance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee`.`Attendance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `time_in` TIME NOT NULL,
  `time_out` TIME NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT 'green',
  PRIMARY KEY (`id`),
  INDEX `employee_id` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `attendance_ibfk_1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee`.`Employees` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `employee`.`Payroll`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee`.`Payroll` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `month` INT NOT NULL,
  `year` INT NOT NULL,
  `salary` DECIMAL(10,2) NOT NULL,
  `taxes` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `employee_id` (`employee_id` ASC) VISIBLE,
  CONSTRAINT `payroll_ibfk_1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee`.`Employees` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `employee`.`employee_passwords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee`.`employee_passwords` (
  `employee_id` INT NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `fk_employee_id`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee`.`Employees` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
