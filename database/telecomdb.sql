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
  `user_id` int(11) NOT NULL,
  `e_mail` varchar(50) NOT NULL,
  `first_login` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK_admins_users` (`user_id`),
  CONSTRAINT `FK_admins_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.admins: ~3 rows (approximately)
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` (`id`, `user_id`, `e_mail`, `first_login`) VALUES
	(23, 40, 'veselin_georgiev@yahoo.com', 0),
	(26, 46, 'martin@gmail.com', 0),
	(27, 47, 'martin-adm@gmail.com', 1);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;

-- Dumping structure for table telecomdb.authorities
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `username_authority` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.authorities: ~12 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`username`, `authority`) VALUES
	('allianz', 'ROLE_CLIENT'),
	('ccb', 'ROLE_CLIENT'),
	('dsk', 'ROLE_CLIENT'),
	('fibank', 'ROLE_CLIENT'),
	('martin-adm-demo', 'ROLE_ADMIN'),
	('martin-admin', 'ROLE_ADMIN'),
	('procredit', 'ROLE_CLIENT'),
	('raiffeisen', 'ROLE_CLIENT'),
	('societe-generale', 'ROLE_CLIENT'),
	('ubb', 'ROLE_CLIENT'),
	('unicredit', 'ROLE_CLIENT'),
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.clients: ~9 rows (approximately)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `user_id`, `full_name`, `uic`) VALUES
	(18, 48, 'DSK Bank', '121830616'),
	(19, 49, 'Unicredit Bulbank', '1222017056'),
	(20, 50, 'Societe Generale Bank Bulgaria', '813071350'),
	(21, 51, 'Procredit Bank Bulgaria', '130598160'),
	(22, 52, 'Allianz Bank Bulgaria', '128001319'),
	(23, 53, 'Raiffeisen Bank Bulgaria', '131206120'),
	(24, 54, 'United Bulgarian Bank', '000694959'),
	(25, 55, 'Central Cooperative Bank', '831447150'),
	(26, 56, 'First Investment Bank', '831094393');
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
  CONSTRAINT `FK_invoices_subscribers` FOREIGN KEY (`subscriber_id`) REFERENCES `subscribers` (`id`),
  CONSTRAINT `FK_invoices_telecom_services` FOREIGN KEY (`telecom_service`) REFERENCES `telecom_services` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.invoices: ~32 rows (approximately)
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` (`id`, `subscriber_id`, `telecom_service`, `status`, `price`, `currency`, `start_date`, `end_date`, `payment_date`) VALUES
	(79, 7, 32, 1, 19.9, 'BGN', '2018-10-08', '2018-11-08', '2018-09-08'),
	(80, 7, 35, 1, 9.9, 'BGN', '2018-10-08', '2018-11-08', '2018-09-08'),
	(81, 8, 37, 1, 29.9, 'BGN', '2018-07-16', '2018-08-16', '2018-09-08'),
	(82, 8, 34, 1, 29.9, 'BGN', '2018-07-16', '2018-08-16', '2018-09-08'),
	(83, 8, 31, 1, 9.9, 'BGN', '2018-07-16', '2018-08-16', '2018-09-08'),
	(84, 8, 37, 1, 29.9, 'BGN', '2018-08-16', '2018-09-16', '2018-09-08'),
	(85, 8, 34, 1, 29.9, 'BGN', '2018-08-16', '2018-09-16', '2018-09-08'),
	(86, 8, 31, 1, 9.9, 'BGN', '2018-08-16', '2018-09-16', '2018-09-08'),
	(87, 8, 37, 1, 29.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(88, 8, 34, 1, 29.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(89, 8, 31, 1, 9.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(90, 8, 37, 1, 29.9, 'BGN', '2018-10-16', '2018-11-16', '2018-09-08'),
	(91, 8, 34, 1, 29.9, 'BGN', '2018-10-16', '2018-11-16', '2018-09-08'),
	(92, 8, 31, 1, 9.9, 'BGN', '2018-10-16', '2018-11-16', '2018-09-08'),
	(93, 7, 32, 1, 19.9, 'BGN', '2018-11-08', '2018-12-08', '2018-09-08'),
	(94, 7, 35, 1, 9.9, 'BGN', '2018-11-08', '2018-12-08', '2018-09-08'),
	(95, 8, 37, 1, 29.9, 'BGN', '2018-11-16', '2018-12-16', '2018-09-08'),
	(96, 8, 34, 1, 29.9, 'BGN', '2018-11-16', '2018-12-16', '2018-09-08'),
	(97, 8, 31, 1, 9.9, 'BGN', '2018-11-16', '2018-12-16', '2018-09-08'),
	(98, 7, 32, 1, 19.9, 'BGN', '2018-12-08', '2019-01-08', '2018-09-08'),
	(99, 7, 35, 1, 9.9, 'BGN', '2018-12-08', '2019-01-08', '2018-09-08'),
	(100, 8, 37, 1, 29.9, 'BGN', '2018-12-16', '2019-01-16', '2018-09-08'),
	(101, 8, 34, 1, 29.9, 'BGN', '2018-12-16', '2019-01-16', '2018-09-08'),
	(102, 8, 31, 1, 9.9, 'BGN', '2018-12-16', '2019-01-16', '2018-09-08'),
	(103, 7, 32, 1, 19.9, 'BGN', '2019-01-08', '2019-02-08', '2018-09-08'),
	(104, 7, 35, 1, 9.9, 'BGN', '2019-01-08', '2019-02-08', '2018-09-08'),
	(105, 8, 37, 1, 29.9, 'BGN', '2019-01-16', '2019-02-16', '2018-09-08'),
	(106, 8, 34, 1, 29.9, 'BGN', '2019-01-16', '2019-02-16', '2018-09-08'),
	(107, 8, 31, 1, 9.9, 'BGN', '2019-01-16', '2019-02-16', '2018-09-08'),
	(108, 8, 37, 1, 29.9, 'BGN', '2019-02-16', '2019-03-16', '2018-09-08'),
	(109, 8, 34, 1, 29.9, 'BGN', '2019-02-16', '2019-03-16', '2018-09-08'),
	(110, 8, 31, 1, 9.9, 'BGN', '2019-02-16', '2019-03-16', '2018-09-08'),
	(111, 11, 32, 0, 19.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(112, 12, 35, 0, 9.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(113, 14, 31, 0, 9.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(114, 16, 31, 0, 9.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(115, 16, 32, 0, 19.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(116, 7, 32, 1, 19.9, 'BGN', '2019-02-08', '2019-03-08', '2018-09-08'),
	(117, 7, 35, 1, 9.9, 'BGN', '2019-02-08', '2019-03-08', '2018-09-08'),
	(118, 8, 37, 1, 29.9, 'BGN', '2019-03-16', '2019-04-16', '2018-09-08'),
	(119, 8, 34, 1, 29.9, 'BGN', '2019-03-16', '2019-04-16', '2018-09-08'),
	(120, 8, 31, 1, 9.9, 'BGN', '2019-03-16', '2019-04-16', '2018-09-08'),
	(121, 19, 33, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(122, 19, 34, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(123, 19, 37, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(124, 9, 31, 0, 9.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(125, 9, 38, 0, 19.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(126, 9, 35, 0, 9.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(127, 20, 33, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(128, 10, 35, 0, 9.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(129, 10, 31, 0, 9.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(130, 21, 37, 1, 29.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(131, 21, 33, 1, 29.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(132, 21, 34, 1, 29.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(133, 11, 32, 0, 19.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(134, 12, 35, 0, 9.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(135, 14, 31, 0, 9.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(136, 16, 31, 0, 9.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(137, 16, 32, 0, 19.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(138, 7, 32, 1, 19.9, 'BGN', '2019-03-08', '2019-04-08', '2018-09-08'),
	(139, 7, 35, 1, 9.9, 'BGN', '2019-03-08', '2019-04-08', '2018-09-08'),
	(140, 8, 37, 0, 29.9, 'BGN', '2019-04-16', '2019-05-16', NULL),
	(141, 8, 34, 0, 29.9, 'BGN', '2019-04-16', '2019-05-16', NULL),
	(142, 8, 31, 0, 9.9, 'BGN', '2019-04-16', '2019-05-16', NULL),
	(143, 19, 33, 0, 29.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(144, 19, 34, 0, 29.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(145, 19, 37, 0, 29.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(146, 9, 31, 0, 9.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(147, 9, 38, 0, 19.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(148, 9, 35, 0, 9.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(149, 20, 33, 0, 29.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(150, 10, 35, 0, 9.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(151, 10, 31, 0, 9.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(152, 21, 37, 0, 29.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(153, 21, 33, 0, 29.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(154, 21, 34, 0, 29.9, 'BGN', '2018-10-16', '2018-11-16', NULL),
	(155, 11, 32, 0, 19.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(156, 33, 34, 1, 29.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(157, 12, 35, 0, 9.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(158, 34, 31, 1, 9.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(159, 35, 34, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(160, 14, 31, 0, 9.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(161, 25, 34, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(162, 26, 31, 1, 9.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(163, 16, 31, 0, 9.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(164, 16, 32, 0, 19.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(165, 27, 34, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(166, 29, 37, 0, 29.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(167, 19, 33, 0, 29.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(168, 19, 37, 0, 29.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(169, 19, 34, 0, 29.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(170, 7, 32, 1, 19.9, 'BGN', '2019-04-08', '2019-05-08', '2018-09-08'),
	(171, 7, 35, 1, 9.9, 'BGN', '2019-04-08', '2019-05-08', '2018-09-08'),
	(172, 8, 37, 0, 29.9, 'BGN', '2019-05-16', '2019-06-16', NULL),
	(173, 8, 31, 0, 9.9, 'BGN', '2019-05-16', '2019-06-16', NULL),
	(174, 8, 34, 0, 29.9, 'BGN', '2019-05-16', '2019-06-16', NULL),
	(175, 9, 31, 0, 9.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(176, 9, 35, 0, 9.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(177, 9, 38, 0, 19.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(178, 20, 33, 0, 29.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(179, 31, 38, 0, 19.9, 'BGN', '2018-09-16', '2018-10-16', NULL),
	(180, 10, 35, 0, 9.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(181, 10, 31, 0, 9.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(182, 21, 37, 0, 29.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(183, 21, 33, 0, 29.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(184, 21, 34, 0, 29.9, 'BGN', '2018-11-16', '2018-12-16', NULL),
	(185, 32, 35, 1, 9.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(186, 36, 37, 1, 29.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(187, 36, 32, 1, 19.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08'),
	(188, 36, 35, 1, 9.9, 'BGN', '2018-09-16', '2018-10-16', '2018-09-08');
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

-- Dumping data for table telecomdb.services_subscribers: ~5 rows (approximately)
/*!40000 ALTER TABLE `services_subscribers` DISABLE KEYS */;
INSERT INTO `services_subscribers` (`service_id`, `subscriber_id`) VALUES
	(37, 36),
	(34, 35),
	(31, 26),
	(32, 7),
	(35, 7),
	(31, 9),
	(38, 31),
	(35, 10),
	(35, 9),
	(31, 10),
	(31, 16),
	(37, 21),
	(32, 36),
	(33, 21),
	(34, 21),
	(33, 19),
	(34, 27),
	(37, 19),
	(33, 20),
	(31, 34),
	(32, 11),
	(35, 32),
	(34, 25),
	(31, 8),
	(34, 33),
	(37, 29),
	(38, 9),
	(35, 12),
	(34, 19),
	(31, 14),
	(34, 8),
	(35, 36),
	(37, 8),
	(32, 16);
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
  UNIQUE KEY `pin` (`pin`),
  KEY `FK_subscribers_clients` (`client`),
  KEY `FK_subscribers_invoices` (`invoice`),
  CONSTRAINT `FK_subscribers_clients` FOREIGN KEY (`client`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK_subscribers_invoices` FOREIGN KEY (`invoice`) REFERENCES `invoices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.subscribers: ~6 rows (approximately)
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
INSERT INTO `subscribers` (`id`, `phone`, `first_name`, `last_name`, `pin`, `address`, `invoice`, `first_activation_date`, `billing_date`, `client`, `all_time_turnover`) VALUES
	(7, '0899112233', 'Ivan', 'Ivanov', '8712048787', 'Sofia, bul. Bulgaria 37', NULL, '2018-09-08', '2019-05-08', 26, 306.6),
	(8, '0897220145', 'Veselin', 'Georgiev', '8512220865', 'Sofia, bul Sitnyakovo 23', NULL, '2015-07-16', '2019-06-16', 23, 637.3),
	(9, '0897219254', 'Dimitar', 'Ivanov', '6512973865', 'Sofia, bul Tsarigradsko shose 23', NULL, '2015-09-16', '2018-12-16', 18, 0),
	(10, '0897254219', 'Ivan', 'Dimitrov', '6401163865', 'Sofia, ul Tintyava 32', NULL, '2015-09-16', '2018-12-16', 19, 0),
	(11, '0879422519', 'Petko', 'Simeonov', '8303033865', 'Sofia, ul Tcherkovna 32', NULL, '2015-09-16', '2018-12-16', 20, 0),
	(12, '0879984365', 'Kostadin', 'Kirilov', '8906033865', 'Sofia, ul Pozitano 20', NULL, '2015-09-16', '2018-12-16', 21, 0),
	(14, '0894843659', 'Kosta', 'Yanev', '6211033565', 'Stara Zagora, ul Zahari Stoyanov 10', NULL, '2015-09-16', '2018-12-16', 21, 0),
	(16, '0896876567', 'Georgi', 'Ivanov', '7811197878', 'Sofia, ul Poduenska 23', NULL, '2015-09-16', '2018-12-16', 25, 0),
	(19, '0888888888', 'Boris', 'Boykov', '6010124433', 'Bankya, ul Gerber 1', NULL, '2015-09-16', '2018-12-16', 25, 0),
	(20, '0878787878', 'Stanislav', 'Trifonov', '6010187878', 'Pleven, ul Efirna 2', NULL, '2015-09-16', '2018-12-16', 24, 0),
	(21, '0896666666', 'Francesco', 'Biaggi', '5910187878', 'Rome, Piazza Navona 1', NULL, '2015-09-16', '2018-12-16', 23, 89.7),
	(24, '0899696969', 'Alexandra', 'Simeonova', '9601187878', 'Vraca, ul Bukuresht 6', NULL, '2015-09-16', '2018-09-16', 19, 0),
	(25, '0899123321', 'Silvia', 'Mitkova', '89101187338', 'Montana, ul Han Asparuh 99', NULL, '2015-09-16', '2018-10-16', 26, 0),
	(26, '0899321123', 'Debora', 'Todorova', '98101187338', 'Smolyan, ul Krim 22', NULL, '2015-09-16', '2018-10-16', 26, 9.9),
	(27, '0899322311', 'Todor', 'Sashkov', '76110187338', 'Smolyan, ul Krim 22', NULL, '2015-09-16', '2018-10-16', 26, 0),
	(29, '0899984386', 'Petko', 'Kostov', '8607038338', 'Silistra, ul Pelikanska 62', NULL, '2015-09-16', '2018-10-16', 26, 0),
	(31, '0899982386', 'Delcho', 'Gotsev', '8807038338', 'Karlovo, ul Vazrojdenska 14', NULL, '2015-09-16', '2018-10-16', 26, 0),
	(32, '0899552386', 'Stefan', 'Stankov', '8602038338', 'Pirdop, ul Miniorska 24', NULL, '2015-09-16', '2018-10-16', 26, 9.9),
	(33, '0899652386', 'Georgi', 'Veselinov', '6404098338', 'Sarnitsa, ul Ribarska 17', NULL, '2015-09-16', '2018-10-16', 26, 29.9),
	(34, '0899675386', 'Radoslav', 'Siderov', '6309098338', 'Yambol, ul Rakovska 17', NULL, '2015-09-16', '2018-10-16', 26, 9.9),
	(35, '0899679192', 'Slavi', 'Todorov', '9112078338', 'Yambol, ul Levski 37', NULL, '2015-09-16', '2018-10-16', 26, 0),
	(36, '0897902770', 'Martin', 'Ivanov', '9101256688', 'Sofia, ul Piring 5', NULL, '2015-09-16', '2018-10-16', 26, 59.7);
/*!40000 ALTER TABLE `subscribers` ENABLE KEYS */;

-- Dumping structure for table telecomdb.telecom_services
CREATE TABLE IF NOT EXISTS `telecom_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL DEFAULT '0',
  `subscription_plan` varchar(255) NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.telecom_services: ~9 rows (approximately)
/*!40000 ALTER TABLE `telecom_services` DISABLE KEYS */;
INSERT INTO `telecom_services` (`id`, `type`, `subscription_plan`, `price`) VALUES
	(31, 'TV', 'economy', 9.99),
	(32, 'TV', 'regular', 19.99),
	(33, 'TV', 'premium', 29.99),
	(34, 'Phone', 'premium', 29.99),
	(35, 'Phone', 'economy', 9.99),
	(36, 'Phone', 'regular', 19.99),
	(37, 'Internet', 'premium', 29.99),
	(38, 'Internet', 'regular', 19.99),
	(39, 'Internet', 'economy', 9.99);
/*!40000 ALTER TABLE `telecom_services` ENABLE KEYS */;

-- Dumping structure for table telecomdb.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- Dumping data for table telecomdb.users: ~12 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `enabled`) VALUES
	(40, 'veselin', '$2a$10$luJRBb2pYBvFtiWTXrYalODTnm6MjhN8jzgzkYAp9ZIJNa1NlLwmK', 1),
	(46, 'martin-admin', '$2a$10$jD/4Qh0zGqGv8dyN9zi.wuqaQ8Lv6CVQU5QaY5Ry9mEyiSsPrNhzm', 1),
	(47, 'martin-adm-demo', '$2a$10$r6t0xoQjTaM2eYAgWVLTDukamOPTH8KcA4GnbAJxm1L5WuF7Ea64q', 1),
	(48, 'dsk', '$2a$10$pkYG2.RICfkNXPtnlbOCQunUTWZU1RTV9vTaZvR4j95DUyErTF.dq', 1),
	(49, 'unicredit', '$2a$10$5v4AA/91baSGsHkC3IRIH.TaZexCcY9p.zH00xO4c6DhJXSctIwtq', 1),
	(50, 'societe-generale', '$2a$10$mer5vaW08bHWRhVEHGtxgOrVlCX0nlsIb7QKmI89Z1rEJ1LJN/vbi', 1),
	(51, 'procredit', '$2a$10$ul3KQg.s.9bKgEa2QipfIOuR2cQW3CjG6AVRi1kKOPoBdB4u9R9K6', 1),
	(52, 'allianz', '$2a$10$s175zlTnymC8/FfgPgP8..5GBMhU.eIri3aC8IvCf8EIYae14xVKW', 1),
	(53, 'raiffeisen', '$2a$10$7V68UYmS/srqRnTwP9q8oeWjWj/b98LqYVjXNeJ9RIuWZ9IXHP2xq', 1),
	(54, 'ubb', '$2a$10$xG2H3cQGV9PSR/HQnHSCQunNbSdQ9X/xayU/uQKMnWVtuXH3AMrg6', 1),
	(55, 'ccb', '$2a$10$PwER2HW9V3ZF5rzOk900de.t2Q2.UGZercOU665jkSrx1uZMJAWeG', 1),
	(56, 'fibank', '$2a$10$DUqH1ktcqs5gm4Y7yRh6P.ToFEuM/wWkKksw9Wi33omWrKBt4JZZe', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
