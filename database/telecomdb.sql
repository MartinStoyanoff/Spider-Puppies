-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table telecomdb.admins
CREATE TABLE IF NOT EXISTS `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `first_login` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK_admins_users` (`user_id`),
  CONSTRAINT `FK_admins_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.admins: ~0 rows (approximately)
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` (`id`, `user_id`, `e_mail`, `first_login`) VALUES
	(3, 'TestUser1', 'abv@ssss.fff', 0);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;

-- Dumping structure for table telecomdb.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `username_authority` (`username`,`authority`),
  CONSTRAINT `FK__users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.authorities: ~14 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('Allianz', 'ROLE_ADMIN'),
	('DSK', 'ROLE_ADMIN'),
	('FiBank', 'ROLE_ADMIN'),
	('generic_client', 'ROLE_ADMIN'),
	('gosho', 'ROLE_CLIENT'),
	('Ivan', 'ROLE_ADMIN'),
	('Ivcho', 'ROLE_ADMIN'),
	('Kiro', 'ROLE_ADMIN'),
	('misho', 'ROLE_CLIENT'),
	('mitko', 'ROLE_ADMIN'),
	('pesho', 'ROLE_ADMIN'),
	('RBB', 'ROLE_ADMIN'),
	('TestUser1', 'ROLE_ADMIN'),
	('TestUser2', 'ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table telecomdb.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `uic` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clients_users` (`user_id`),
  CONSTRAINT `FK_clients_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.clients: ~4 rows (approximately)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `user_id`, `full_name`, `uic`) VALUES
	(8, 'Allianz', 'Allianz Bank Bulgaria', '128001319'),
	(9, 'FiBank', 'First Investment Bank', '831094393'),
	(10, 'DSK', 'DSK Bank', '121830616'),
	(12, 'RBB', 'Raiffeisen Bank Bulgaria', '831558413');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;

-- Dumping structure for table telecomdb.invoices
CREATE TABLE IF NOT EXISTS `invoices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subscriber_id` int(11) NOT NULL DEFAULT 0,
  `telecom_service` int(11) NOT NULL DEFAULT 0,
  `status` int(11) NOT NULL DEFAULT 0,
  `price` double NOT NULL DEFAULT 0,
  `currency` varchar(50) NOT NULL DEFAULT '0',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `payment_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_invoices_telecom_services` (`telecom_service`),
  KEY `FK_invoices_subscribers` (`subscriber_id`),
  CONSTRAINT `FK_invoices_subscribers` FOREIGN KEY (`subscriber_id`) REFERENCES `subscribers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_invoices_telecom_services` FOREIGN KEY (`telecom_service`) REFERENCES `telecom_services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.invoices: ~5 rows (approximately)
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` (`id`, `subscriber_id`, `telecom_service`, `status`, `price`, `currency`, `start_date`, `end_date`, `payment_date`) VALUES
	(16, 2, 13, 1, 19, 'BGN', '2018-07-16', '2018-08-16', '2018-08-21'),
	(17, 3, 10, 0, 9, 'BGN', '2018-07-16', '2018-08-16', '2018-08-21'),
	(18, 2, 13, 0, 19, 'BGN', '2018-07-16', '2018-08-16', '2018-08-21'),
	(19, 2, 13, 1, 19, 'BGN', '2018-07-16', '2018-08-16', '2018-08-21'),
	(20, 3, 10, 0, 9, 'BGN', '2018-07-16', '2018-08-16', '2018-08-21');
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;

-- Dumping structure for table telecomdb.services_subscribers
CREATE TABLE IF NOT EXISTS `services_subscribers` (
  `service_id` int(11) DEFAULT NULL,
  `subscriber_id` int(11) DEFAULT NULL,
  KEY `FK_telecom_services_subscribers_telecom_services` (`service_id`),
  KEY `FK_telecom_services_subscribers_subscribers` (`subscriber_id`),
  CONSTRAINT `FK_telecom_services_subscribers_subscribers` FOREIGN KEY (`subscriber_id`) REFERENCES `subscribers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_telecom_services_subscribers_telecom_services` FOREIGN KEY (`service_id`) REFERENCES `telecom_services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.services_subscribers: ~2 rows (approximately)
/*!40000 ALTER TABLE `services_subscribers` DISABLE KEYS */;
INSERT INTO `services_subscribers` (`service_id`, `subscriber_id`) VALUES
	(10, 3),
	(13, 2);
/*!40000 ALTER TABLE `services_subscribers` ENABLE KEYS */;

-- Dumping structure for table telecomdb.subscribers
CREATE TABLE IF NOT EXISTS `subscribers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `pin` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `invoice` int(11) DEFAULT NULL,
  `first_activation_date` date DEFAULT NULL,
  `billing_date` date DEFAULT NULL,
  `client` int(11) DEFAULT NULL,
  `all_time_turnover` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `FK_subscribers_clients` (`client`),
  KEY `FK_subscribers_invoices` (`invoice`),
  CONSTRAINT `FK_subscribers_clients` FOREIGN KEY (`client`) REFERENCES `clients` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_subscribers_invoices` FOREIGN KEY (`invoice`) REFERENCES `invoices` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.subscribers: ~2 rows (approximately)
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
INSERT INTO `subscribers` (`id`, `phone`, `first_name`, `last_name`, `pin`, `address`, `invoice`, `first_activation_date`, `billing_date`, `client`, `all_time_turnover`) VALUES
	(2, '0897902770', 'Martin', 'Ivanov', '9933881133', 'gore na chereshata', NULL, '2015-07-16', '2018-07-16', 9, 0),
	(3, '0897220145', 'Veselin', 'Georgiev', '2114124490', 'dolu pod chereshata', NULL, '2015-07-16', '2018-07-16', 12, 0);
/*!40000 ALTER TABLE `subscribers` ENABLE KEYS */;

-- Dumping structure for table telecomdb.telecom_services
CREATE TABLE IF NOT EXISTS `telecom_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL DEFAULT '0',
  `subscription_plan` varchar(255) NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.telecom_services: ~21 rows (approximately)
/*!40000 ALTER TABLE `telecom_services` DISABLE KEYS */;
INSERT INTO `telecom_services` (`id`, `type`, `subscription_plan`, `price`) VALUES
	(9, 'TV', 'max', 9),
	(10, 'TV', 'max', 9),
	(11, 'TV', 'max', 9),
	(12, 'TV', 'max', 9),
	(13, 'TV', 'max', 19),
	(14, 'TV', 'max', 20),
	(15, 'TV', 'max', 20),
	(16, 'TV', 'max', 20),
	(17, 'TV', 'max', 20),
	(18, 'TV', 'max', 20),
	(19, 'TV', 'max', 20),
	(20, 'TV', 'max', 20),
	(21, 'TV', 'max', 20),
	(22, 'TV', 'max', 20),
	(23, 'TV', 'max', 19.55555555555555),
	(24, 'TV', 'max', 19.55),
	(25, 'TV', 'max', 2.53),
	(26, 'TV', 'max', 2.54),
	(27, 'TV', 'max', 2.5399999999999),
	(28, 'TV', 'max', 2.539999999999999),
	(29, 'TV', 'max', 2.54);
/*!40000 ALTER TABLE `telecom_services` ENABLE KEYS */;

-- Dumping structure for table telecomdb.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.users: ~17 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('admin', '{noop}admin', 1),
	('administrator', '{noop}admins', 1),
	('Allianz', '{noop}ClientPasswod1', 1),
	('DSK', '{noop}dsk', 1),
	('FiBank', '{noop}first', 1),
	('generic_client', 'Test', 1),
	('gosho', '{noop}pass2', 1),
	('Ivan', 'Test', 1),
	('Ivcho', 'Test', 1),
	('Kiro', 'Test', 1),
	('misho', '{noop}pass3', 1),
	('mitko', '{noop}neepi4', 1),
	('pesho', '{noop}pass1', 1),
	('RBB', '{noop}rbb', 1),
	('TestUser1', '{noop}TestPassword1', 1),
	('TestUser2', '{noop}TestPassword2', 1),
	('vesko', '{noop}veskoepi4', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
