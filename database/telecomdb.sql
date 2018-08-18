-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for telecomdb
CREATE DATABASE IF NOT EXISTS `telecomdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `telecomdb`;

-- Dumping structure for table telecomdb.admins
CREATE TABLE IF NOT EXISTS `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `first_login` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK_admins_users` (`user_id`),
  CONSTRAINT `FK_admins_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.admins: ~1 rows (approximately)
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` (`id`, `user_id`, `e_mail`, `first_login`) VALUES
	(2, 'administrator', 'gosho@ot.pochivkata', 0);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;

-- Dumping structure for table telecomdb.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `username_authority` (`username`,`authority`),
  CONSTRAINT `FK__users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.authorities: ~10 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('admin', 'ROLE_ADMIN'),
	('administrator', 'ROLE_ADMIN'),
	('generic_client', 'ROLE_ADMIN'),
	('gosho', 'ROLE_CLIENT'),
	('Ivan', 'ROLE_ADMIN'),
	('Ivcho', 'ROLE_ADMIN'),
	('Kiro', 'ROLE_ADMIN'),
	('misho', 'ROLE_CLIENT'),
	('mitko', 'ROLE_ADMIN'),
	('pesho', 'ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table telecomdb.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `uic` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clients_users` (`user_id`),
  CONSTRAINT `FK_clients_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.clients: ~6 rows (approximately)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `user_id`, `full_name`, `uic`) VALUES
	(1, 'pesho', 'pesho', '122423534'),
	(3, 'mitko', 'MitkoBombata', '5434534534'),
	(4, 'Ivan', 'Test', '44444'),
	(5, 'Kiro', 'Test', '44444'),
	(6, 'Ivcho', 'Test', '44444'),
	(7, 'generic_client', 'Test', '44444');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;

-- Dumping structure for table telecomdb.telecom_services
CREATE TABLE IF NOT EXISTS `telecom_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL DEFAULT '0',
  `subscription_plan` varchar(255) NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT 0,
  `paid` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.telecom_services: ~14 rows (approximately)
/*!40000 ALTER TABLE `telecom_services` DISABLE KEYS */;
INSERT INTO `telecom_services` (`id`, `type`, `subscription_plan`, `price`, `paid`) VALUES
	(9, 'TV', 'max', 9, 0),
	(10, 'TV', 'max', 9, 0),
	(11, 'TV', 'max', 9, 0),
	(12, 'TV', 'max', 9, 0),
	(13, 'TV', 'max', 19, 0),
	(14, 'TV', 'max', 20, 0),
	(15, 'TV', 'max', 20, 0),
	(16, 'TV', 'max', 20, 0),
	(17, 'TV', 'max', 20, 0),
	(18, 'TV', 'max', 20, 0),
	(19, 'TV', 'max', 20, 0),
	(20, 'TV', 'max', 20, 0),
	(21, 'TV', 'max', 20, 0),
	(22, 'TV', 'max', 20, 0),
	(23, 'TV', 'max', 19.55555555555555, 0),
	(24, 'TV', 'max', 19.55, 0),
	(25, 'TV', 'max', 2.53, 0),
	(26, 'TV', 'max', 2.54, 0),
	(27, 'TV', 'max', 2.5399999999999, 0),
	(28, 'TV', 'max', 2.539999999999999, 0),
	(29, 'TV', 'max', 2.54, 0);
/*!40000 ALTER TABLE `telecom_services` ENABLE KEYS */;

-- Dumping structure for table telecomdb.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.users: ~11 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('admin', '{noop}admin', 1),
	('administrator', '{noop}admins', 1),
	('generic_client', 'Test', 1),
	('gosho', '{noop}pass2', 1),
	('Ivan', 'Test', 1),
	('Ivcho', 'Test', 1),
	('Kiro', 'Test', 1),
	('misho', '{noop}pass3', 1),
	('mitko', '{noop}neepi4', 1),
	('pesho', '{noop}pass1', 1),
	('vesko', '{noop}veskoepi4', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
