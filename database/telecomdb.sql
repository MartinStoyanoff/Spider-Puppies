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


-- Dumping database structure for telecomdb
CREATE DATABASE IF NOT EXISTS `telecomdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `telecomdb`;

-- Dumping structure for table telecomdb.admins
CREATE TABLE IF NOT EXISTS `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `first_login` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK_admins_users` (`user_id`),
  CONSTRAINT `FK_admins_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.admins: ~2 rows (approximately)
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` (`id`, `user_id`, `e_mail`, `first_login`) VALUES
	(23, 40, 'veselin_georgiev@yahoo.com', 0),
	(24, 41, 'ivanIvanoc@gmail.com', 1);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;

-- Dumping structure for table telecomdb.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `username_authority` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.authorities: ~9 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('Allianzaaaa', 'ROLE_CLIENT'),
	('banka', 'ROLE_CLIENT'),
	('Dragan', 'ROLE_CLIENT'),
	('DSK', 'ROLE_CLIENT'),
	('gosho', 'ROLE_CLIENT'),
	('ivanIvanov', 'ROLE_ADMIN'),
	('misho', 'ROLE_CLIENT'),
	('Societe', 'ROLE_CLIENT'),
	('veselin', 'ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table telecomdb.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `uic` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clients_users` (`user_id`),
  CONSTRAINT `FK_clients_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.clients: ~6 rows (approximately)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `user_id`, `full_name`, `uic`) VALUES
	(8, 3, 'Allianz Bank Bulgariaaaaa', '14332222'),
	(9, 4, 'First Investment Bank', '831094393'),
	(12, 13, 'Dragan', '444'),
	(14, 22, 'Sociate', '8888888888'),
	(15, 43, 'DSK', '1222333333'),
	(17, 45, 'Banka', '11222');
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.invoices: ~16 rows (approximately)
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` (`id`, `subscriber_id`, `telecom_service`, `status`, `price`, `currency`, `start_date`, `end_date`, `payment_date`) VALUES
	(63, 2, 13, 1, 19, 'BGN', '2018-07-16', '2018-08-16', '2018-09-08'),
	(64, 3, 10, 0, 9, 'BGN', '2018-07-16', '2018-08-16', NULL),
	(65, 2, 13, 1, 19, 'BGN', '2018-08-16', '2018-09-16', '2018-09-08'),
	(66, 3, 10, 1, 9, 'BGN', '2018-08-16', '2018-09-16', '2018-09-08'),
	(67, 2, 13, 1, 19, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(68, 3, 10, 0, 9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(69, 2, 13, 0, 19, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(70, 3, 10, 0, 9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(71, 2, 13, 1, 19, 'BGN', '2018-11-16', '2018-12-16', '2018-09-08'),
	(72, 3, 10, 0, 9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(73, 2, 13, 0, 19, 'BGN', '2018-12-16', '2019-01-16', NULL),
	(74, 3, 10, 0, 9, 'BGN', '2018-12-16', '2019-01-16', NULL),
	(75, 2, 13, 0, 19, 'BGN', '2019-01-16', '2019-02-16', NULL),
	(76, 3, 10, 0, 9, 'BGN', '2019-01-16', '2019-02-16', NULL),
	(77, 2, 13, 1, 19, 'BGN', '2019-02-16', '2019-03-16', '2018-09-08'),
	(78, 2, 13, 0, 19, 'BGN', '2019-03-16', '2019-04-16', NULL);
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
	(2, '0897902770', 'Martin', 'Ivanov', '9933881133', 'gore na chereshata', NULL, '2018-07-16', '2019-04-16', 15, 95),
	(3, '0897220145', 'Veselin', 'Georgiev', '2114124490', 'dolu pod chereshata', NULL, '2018-07-16', '2019-02-16', 15, 9);
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.users: ~10 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `enabled`) VALUES
	(3, 'Allianzaaaa', '{noop}ClientPasswod1', 1),
	(4, 'FiBank', '{noop}first', 1),
	(13, 'Dragan', '{noop}pass', 1),
	(14, 'TestUser2', '{noop}TestPassword2', 1),
	(16, 'TestUser1', '{noop}TestPassword1', 1),
	(22, 'Societe', '{noop}pass', 1),
	(40, 'veselin', '$2a$10$luJRBb2pYBvFtiWTXrYalODTnm6MjhN8jzgzkYAp9ZIJNa1NlLwmK', 1),
	(41, 'ivanIvanov', '$2a$10$UntEHhmyYeO6piF5r5qmtOr.AOxV9sgxgCwcOePVY8QHWTHmXuvoG', 1),
	(43, 'DSK', '$2a$10$D.q.eik3/2b9JAMZLJUyrugU7LxRzzM.y2axD4V0Utx6L7.DPw2S6', 1),
	(45, 'banka', '$2a$10$iSYcUf26Xb2kn31gk87YG.iNUYkqLgKimxVVYAsPV36UozhGU8ltm', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
