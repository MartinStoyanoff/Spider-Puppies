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
  PRIMARY KEY (`id`),
  KEY `FK_invoices_telecom_services` (`telecom_service`),
  KEY `FK_invoices_subscribers` (`subscriber_id`),
  CONSTRAINT `FK_invoices_subscribers` FOREIGN KEY (`subscriber_id`) REFERENCES `subscribers` (`id`),
  CONSTRAINT `FK_invoices_telecom_services` FOREIGN KEY (`telecom_service`) REFERENCES `telecom_services` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.invoices: ~0 rows (approximately)
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;

-- Dumping structure for table telecomdb.services_subscribers
CREATE TABLE IF NOT EXISTS `services_subscribers` (
  `service_id` int(11) DEFAULT NULL,
  `subscriber_id` int(11) DEFAULT NULL,
  KEY `FK_telecom_services_subscribers_telecom_services` (`service_id`),
  KEY `FK_telecom_services_subscribers_subscribers` (`subscriber_id`),
  CONSTRAINT `FK_telecom_services_subscribers_subscribers` FOREIGN KEY (`subscriber_id`) REFERENCES `subscribers` (`id`),
  CONSTRAINT `FK_telecom_services_subscribers_telecom_services` FOREIGN KEY (`service_id`) REFERENCES `telecom_services` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.services_subscribers: ~1 rows (approximately)
/*!40000 ALTER TABLE `services_subscribers` DISABLE KEYS */;
INSERT INTO `services_subscribers` (`service_id`, `subscriber_id`) VALUES
	(23, 1);
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
  `first_activation_date` datetime DEFAULT NULL,
  `billing_date` datetime DEFAULT NULL,
  `client` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subscribers_clients` (`client`),
  KEY `FK_subscribers_invoices` (`invoice`),
  CONSTRAINT `FK_subscribers_clients` FOREIGN KEY (`client`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK_subscribers_invoices` FOREIGN KEY (`invoice`) REFERENCES `invoices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.subscribers: ~1 rows (approximately)
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
INSERT INTO `subscribers` (`id`, `phone`, `first_name`, `last_name`, `pin`, `address`, `invoice`, `first_activation_date`, `billing_date`, `client`) VALUES
	(1, '088833838383', 'Goshko', 'Ubaveca', NULL, 'Mladost Beibeee', NULL, NULL, NULL, NULL);
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
