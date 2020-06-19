-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 09 avr. 2018 à 21:20
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `soukelmdina`
--

-- --------------------------------------------------------

--
-- Structure de la table `acl_classes`
--

DROP TABLE IF EXISTS `acl_classes`;
CREATE TABLE IF NOT EXISTS `acl_classes` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_type` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_69DD750638A36066` (`class_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `acl_entries`
--

DROP TABLE IF EXISTS `acl_entries`;
CREATE TABLE IF NOT EXISTS `acl_entries` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_id` int(10) UNSIGNED NOT NULL,
  `object_identity_id` int(10) UNSIGNED DEFAULT NULL,
  `security_identity_id` int(10) UNSIGNED NOT NULL,
  `field_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ace_order` smallint(5) UNSIGNED NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `granting_strategy` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_46C8B806EA000B103D9AB4A64DEF17BCE4289BF4` (`class_id`,`object_identity_id`,`field_name`,`ace_order`),
  KEY `IDX_46C8B806EA000B103D9AB4A6DF9183C9` (`class_id`,`object_identity_id`,`security_identity_id`),
  KEY `IDX_46C8B806EA000B10` (`class_id`),
  KEY `IDX_46C8B8063D9AB4A6` (`object_identity_id`),
  KEY `IDX_46C8B806DF9183C9` (`security_identity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `acl_object_identities`
--

DROP TABLE IF EXISTS `acl_object_identities`;
CREATE TABLE IF NOT EXISTS `acl_object_identities` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_object_identity_id` int(10) UNSIGNED DEFAULT NULL,
  `class_id` int(10) UNSIGNED NOT NULL,
  `object_identifier` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_9407E5494B12AD6EA000B10` (`object_identifier`,`class_id`),
  KEY `IDX_9407E54977FA751A` (`parent_object_identity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `acl_object_identity_ancestors`
--

DROP TABLE IF EXISTS `acl_object_identity_ancestors`;
CREATE TABLE IF NOT EXISTS `acl_object_identity_ancestors` (
  `object_identity_id` int(10) UNSIGNED NOT NULL,
  `ancestor_id` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`object_identity_id`,`ancestor_id`),
  KEY `IDX_825DE2993D9AB4A6` (`object_identity_id`),
  KEY `IDX_825DE299C671CEA1` (`ancestor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `acl_security_identities`
--

DROP TABLE IF EXISTS `acl_security_identities`;
CREATE TABLE IF NOT EXISTS `acl_security_identities` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `identifier` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `username` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8835EE78772E836AF85E0677` (`identifier`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `blog_post`
--

DROP TABLE IF EXISTS `blog_post`;
CREATE TABLE IF NOT EXISTS `blog_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `blog_post`
--

INSERT INTO `blog_post` (`id`, `title`, `body`) VALUES
(4, 'je cherche', 'mm'),
(6, 'salem', 'yo'),
(7, 'test', 'yo 06'),
(8, '+66+', 'rytuy'),
(9, 'et', 'zr'),
(10, '+66+', 'rytuy'),
(11, '+66+', 'rytuy'),
(12, '5', '85');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  `paiement` int(11) DEFAULT NULL,
  `livraison` int(11) DEFAULT NULL,
  `adresse` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateLivraison` varchar(66) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Etat` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `ville` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pays` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idClient` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `total`, `paiement`, `livraison`, `adresse`, `dateLivraison`, `Etat`, `date`, `num`, `ville`, `pays`, `idClient`) VALUES
(1, 63, 1, 1, 'dhouib60@gmail.com', NULL, '1', '2018-02-27', 52002272, 'ben arous', 'Tunisie', 1),
(2, 40, 1, 1, 'ben arous', NULL, '1', '2018-02-27', 52002272, 'Nouvelle Medina', 'Tunisie', 1),
(3, 40, 1, 1, '17 rue iine nouvelle madina 2 ben arous', NULL, '1', '2018-02-27', 58686, 'Nouvelle', 'Tu', 1),
(4, 40, 1, 1, '1312', NULL, '1', '2018-02-27', 2121, '357', '8752', 1),
(5, 40, 1, 1, '777', NULL, '1', '2018-02-27', 777, '777', '777', 1),
(14, NULL, NULL, NULL, NULL, NULL, '0', '2018-02-27', NULL, NULL, NULL, 2),
(20, 12, 1, 1, '22', NULL, '1', '2018-02-28', 22, '22', '22', 4),
(21, NULL, NULL, NULL, NULL, NULL, '0', '2018-02-28', NULL, NULL, NULL, 5),
(23, 1014.2, NULL, NULL, NULL, NULL, '0', '2018-02-28', NULL, NULL, NULL, 4),
(24, NULL, NULL, NULL, NULL, NULL, '0', '2018-02-28', NULL, NULL, NULL, 3),
(30, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `idevent` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `ancestors` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `depth` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9474526CE2904019` (`thread_id`),
  KEY `IDX_9474526CF675F31B` (`author_id`),
  KEY `IDX_9474526CEDAB66BE` (`idevent`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `thread_id`, `author_id`, `idevent`, `body`, `ancestors`, `depth`, `created_at`, `state`) VALUES
(1, '4', 1, NULL, 'good !', '', 0, '2018-02-28 17:02:40', 0);

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

DROP TABLE IF EXISTS `commentaires`;
CREATE TABLE IF NOT EXISTS `commentaires` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idevent` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `contenu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D9BEC0C4EDAB66BE` (`idevent`),
  KEY `IDX_D9BEC0C45E5C27E9` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `enseignes`
--

DROP TABLE IF EXISTS `enseignes`;
CREATE TABLE IF NOT EXISTS `enseignes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descripEnseignes` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbreTables` int(11) NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numTel` int(11) NOT NULL,
  `lat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lng` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `categorieEnseignes` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_1139B55B6B3CA4B` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `enseignes`
--

INSERT INTO `enseignes` (`id`, `id_user`, `nom`, `email`, `descripEnseignes`, `adresse`, `nbreTables`, `etat`, `numTel`, `lat`, `lng`, `categorieEnseignes`, `image`) VALUES
(5, 6, 'Little Sarrajine', 'sarrajine@gmail.com', 'traditionnel', 'sarrajine', 7, 'Modifié', 56896352, '36.795599', '10.168812', 'café', 'FB_IMG_1478288698168.jpg'),
(6, 6, 'sidi bou', 'oumayma@oum.com', 'restaurant', 'nasr2', 6, 'Accepter', 78569365, '36.819639', '10.065402', 'restaurant', 'sidi bou.png'),
(10, 6, 'ggggg', 'zdfqfqr', 'rfqefeq', 'erqfe', 12, 'Modifié', 54859632, NULL, NULL, 'café', 'FB_IMG_1478288703345.jpg'),
(13, 7, 'sidi bou', 'oumayma@oum.com', 'restaurant', 'nasr2', 6, 'Modifié', 78569365, '36819639', '10065402', 'restaurant', 'FB_IMG_1478288685402.jpg'),
(15, 7, 'sidi bou', 'oumayma@oum.com', 'restaurant', 'nasr2', 6, 'Accepter', 78569365, '36819639', '10065402', 'restaurant', 'FB_IMG_1478288637026.jpg'),
(16, 6, 'xcfvdw', 'fghjk', 'dfghjkl', 'fghjkl', 4, 'En attente', 563, '36.815295845653814', '10.179611724853544', 'restaurant', 'FB_IMG_1478288661729.jpg'),
(17, 6, 'zrfetz', 'tztz', 'eyesy', 'rty', 8, 'En attente', 68, '36.81034830681951', '10.162445587158231', 'café', 'FB_IMG_1478288661729.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `lieu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbreparticipation` int(11) NOT NULL,
  `etat` int(11) NOT NULL,
  `nbreticket` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5387574A8D93D649` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `events`
--

INSERT INTO `events` (`id`, `user`, `titre`, `date`, `lieu`, `prix`, `description`, `image`, `nbreparticipation`, `etat`, `nbreticket`) VALUES
(3, 3, 'test', '2013-01-01', 'test', 10, 'test', 'test.png', 0, 1, 10),
(4, 1, 'Hadhra', '2018-03-20', 'kasba', 15, 'soyez nombreux!', 'Hadhra.png', 0, 1, 120),
(5, 1, 'love Tunisa', '2017-01-01', 'centre ville tunis', 20, 'be there !', 'love Tunisa.png', 0, 0, 50),
(6, 3, 'resgy', '2013-01-01', 'hhhh', 111, 'sdzq', 'resgy.png', 0, 0, 20),
(7, 3, 'fffffff', '2017-05-06', 'ggg', 111, 'ffff', 'fffffff.png', 0, 0, 111),
(8, 1, 'fffffff', '2020-01-01', 'ffff', 444, 'iii', 'fffffff.png', 0, 1, 22);

-- --------------------------------------------------------

--
-- Structure de la table `jaim`
--

DROP TABLE IF EXISTS `jaim`;
CREATE TABLE IF NOT EXISTS `jaim` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `idroduit` int(11) DEFAULT NULL,
  `etat` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ind1` (`id_client`),
  KEY `ind2` (`idroduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

DROP TABLE IF EXISTS `lignecommande`;
CREATE TABLE IF NOT EXISTS `lignecommande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcommande` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `idProduit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `produndex` (`idProduit`),
  KEY `cmdindex` (`idcommande`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `mail`
--

DROP TABLE IF EXISTS `mail`;
CREATE TABLE IF NOT EXISTS `mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` int(11) NOT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `notifiable`
--

DROP TABLE IF EXISTS `notifiable`;
CREATE TABLE IF NOT EXISTS `notifiable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `class` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notifiable`
--

INSERT INTO `notifiable` (`id`, `identifier`, `class`) VALUES
(1, '1', 'ClientBundle\\Entity\\User'),
(2, '4', 'ClientBundle\\Entity\\User'),
(3, '3', 'ClientBundle\\Entity\\User'),
(4, '6', 'ClientBundle\\Entity\\User'),
(5, '7', 'ClientBundle\\Entity\\User');

-- --------------------------------------------------------

--
-- Structure de la table `notifiable_notification`
--

DROP TABLE IF EXISTS `notifiable_notification`;
CREATE TABLE IF NOT EXISTS `notifiable_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notification_id` int(11) DEFAULT NULL,
  `notifiable_entity_id` int(11) DEFAULT NULL,
  `seen` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ADCFE0FAEF1A9D84` (`notification_id`),
  KEY `IDX_ADCFE0FAC3A0A4F8` (`notifiable_entity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notifiable_notification`
--

INSERT INTO `notifiable_notification` (`id`, `notification_id`, `notifiable_entity_id`, `seen`) VALUES
(1, 1, 1, 1),
(2, 2, 1, 1),
(3, 3, 1, 1),
(4, 4, 1, 1),
(5, 5, 2, 1),
(6, 6, 2, 1),
(7, 7, 3, 1),
(8, 8, 3, 1),
(9, 9, 3, 1),
(10, 10, 3, 1),
(11, 11, 3, 1),
(12, 12, 3, 1),
(13, 13, 1, 1),
(14, 14, 1, 1),
(15, 15, 2, 1),
(16, 16, 2, 1),
(17, 17, 2, 1),
(18, 18, 1, 1),
(19, 19, 1, 1),
(20, 20, 1, 1),
(21, 21, 1, 1),
(22, 22, 1, 1),
(23, 23, 4, 1),
(24, 24, 4, 1),
(25, 25, 5, 1),
(26, 26, 5, 1),
(27, 27, 5, 1),
(28, 28, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `link` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `date`, `subject`, `message`, `link`) VALUES
(1, '2018-02-27 17:22:01', 'enseignef²fest en attente', 'Ajout d un boutique ', 'http://symfony.com/'),
(2, '2018-02-28 15:43:18', 'oua ajouté une reclamation', 'Ajout d une reclamation ', 'http://symfony.com/'),
(3, '2018-02-28 15:46:16', 'fga ajouté une reclamation', 'Ajout d une reclamation ', 'http://symfony.com/'),
(4, '2018-02-28 15:52:52', 'sdfgha ajouté une reclamation', 'Ajout d une reclamation ', 'http://symfony.com/'),
(5, '2018-02-28 17:47:29', 'email', 'commande', 'http://symfony.com/'),
(6, '2018-02-28 17:57:27', 'email', 'email ', 'http://symfony.com/'),
(7, '2018-02-28 18:01:18', 'vous a envoyé un email', 'email ', 'http://symfony.com/'),
(8, '2018-02-28 18:02:18', 'adminvous a envoyé un email', 'email ', 'http://symfony.com/'),
(9, '2018-02-28 18:03:40', 'admin@gmail.comvous a envoyé un email', 'email ', 'http://symfony.com/'),
(10, '2018-02-28 18:12:58', 'enseigneresgyest en attente', 'Ajout d un boutique ', 'http://symfony.com/'),
(11, '2018-02-28 18:22:53', 'l evenement fffffffest en attente', 'Ajout d un evenement ', 'http://symfony.com/'),
(12, '2018-02-28 18:30:28', 'un blog+66+est ajouté', 'Ajout blog ', 'http://symfony.com/'),
(13, '2018-02-28 18:45:08', 'enseignesarrajineest en attente', 'Ajout d un boutique ', 'http://symfony.com/'),
(14, '2018-02-28 19:19:11', 'l evenement fffffffest en attente', 'Ajout d un evenement ', 'http://symfony.com/'),
(15, '2018-02-28 19:47:57', 'omar1vous a envoyé un email', 'email ', 'http://symfony.com/'),
(16, '2018-02-28 23:17:25', 'omar1vous a envoyé un email', 'email ', 'http://symfony.com/'),
(17, '2018-02-28 23:28:33', 'un blogetest ajouté', 'Ajout blog ', 'http://symfony.com/'),
(18, '2018-02-28 23:31:33', 'enseignedhouibest en attente', 'Ajout d un boutique ', 'http://symfony.com/'),
(19, '2018-02-28 23:34:44', 'un blog+66+est ajouté', 'Ajout blog ', 'http://symfony.com/'),
(20, '2018-02-28 23:35:35', 'un blog+66+est ajouté', 'Ajout blog ', 'http://symfony.com/'),
(21, '2018-02-28 23:45:59', 'ahmedvous a envoyé un email', 'email ', 'http://symfony.com/'),
(22, '2018-02-28 23:47:05', 'un blog5est ajouté', 'Ajout blog ', 'http://symfony.com/'),
(23, '2018-03-01 07:04:52', 'enseigneLittle Sarrajineest en attente', 'Ajout d un boutique ', 'http://symfony.com/'),
(24, '2018-03-01 07:08:22', 'enseigneLittle Sarrajineest modifié', 'modifié une boutique ', 'http://symfony.com/'),
(25, '2018-03-01 07:21:29', 'enseignesidi bouest en attente', 'Ajout d un boutique ', 'http://symfony.com/'),
(26, '2018-03-01 07:24:02', 'enseigneprinceest en attente', 'Ajout d un boutique ', 'http://symfony.com/'),
(27, '2018-03-01 07:33:05', 'enseigneprinceest supprimé', 'un enseigne est supprimé ', 'http://symfony.com/'),
(28, '2018-03-01 11:38:25', 'enseignetestest en attente', 'Ajout d un boutique ', 'http://symfony.com/');

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carte` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `mdp` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id`, `carte`, `mdp`, `total`) VALUES
(1, '123456', '123456', 100000000000),
(2, '789456', '789456', 1);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idevent` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `etat` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AB55E24FEDAB66BE` (`idevent`),
  KEY `IDX_AB55E24F5E5C27E9` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `reference` int(11) NOT NULL,
  `quantite` int(11) DEFAULT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `categorie` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `NbJaimes` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_29A5EC276B3CA4B` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `id_user`, `reference`, `quantite`, `nom`, `categorie`, `prix`, `description`, `image`, `NbJaimes`, `date`, `etat`) VALUES
(2, 3, 55, 22, 'ddd', 'Chicha', 22, '2', 'dd.png', 0, '2018-02-27', 'Accepter'),
(3, 4, 12, 13, 'omar', 'Artisana', 12, 'omar', 'omar.png', 0, '2018-02-27', 'Accepter'),
(5, 3, 999, 5412, 'hhh', 'Other', 992.2, 'fcgxjchklk hg q,hj b', 'hhh.png', 0, '2018-02-28', 'Accepter'),
(6, 1, 6, 20, 'hkh', 'TraditionalFood', 9, 'vbn,; hjklm nn ngfvgbhcn', 'hkh.png', 0, '2018-02-28', 'Accepter'),
(7, 3, 56, 5, 'zarbia', 'Artisana', 6282, 'fg f', 'zarbia.png', 0, '2018-02-28', 'Accepter'),
(8, 1, 123, 123, 'ooo', 'Chicha', 12, 'ooo', 'ooo.png', 0, '2018-03-01', 'Accepter');

-- --------------------------------------------------------

--
-- Structure de la table `promouvoir`
--

DROP TABLE IF EXISTS `promouvoir`;
CREATE TABLE IF NOT EXISTS `promouvoir` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reference` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `date` date DEFAULT NULL,
  `nomimage` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `idClient` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_A81AFBCCA455ACCF` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `promouvoir`
--

INSERT INTO `promouvoir` (`id`, `reference`, `nom`, `description`, `image`, `date`, `nomimage`, `idClient`) VALUES
(8, 'Basic', 'yo', 'sdqdsq', 'ah.png', '2018-02-28', 'ah', 5),
(9, 'Professional', 'yoo', 'good chacchia', 'chachia.png', '2018-02-28', 'chachia', 5);

-- --------------------------------------------------------

--
-- Structure de la table `pub`
--

DROP TABLE IF EXISTS `pub`;
CREATE TABLE IF NOT EXISTS `pub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `nomimage` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `pub`
--

INSERT INTO `pub` (`id`, `image`, `nomimage`) VALUES
(1, 'brik.png', 'brik');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `nom` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `text` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_48FCEBD36B3CA4B` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `id_user`, `nom`, `prenom`, `email`, `text`, `date`) VALUES
(2, 1, 'a', 'b', 'l@gm.c', 'ggggggg', '2018-02-27'),
(3, 1, 'b', 'v', 'k@g.c', 'hhhhhhhhh', '2018-02-27'),
(6, 1, 'ou', 'mo', 'k@h.l', 'dgfh hj;vgj hhhhhhh', '2018-02-28'),
(7, 1, 'fg', 'gh', 'ghj@h.v', 'tghj fghj,', '2018-02-28'),
(8, 1, 'sdfgh', 'n', 'klk@l.v', 'gdshjkl', '2018-02-28');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateR` date DEFAULT NULL,
  `numTel` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateInsert` date DEFAULT NULL,
  `Enseigne` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_42C84955CEA215D8` (`Enseigne`),
  KEY `IDX_42C849558D93D649` (`user`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id`, `user`, `nom`, `prenom`, `etat`, `email`, `dateR`, `numTel`, `dateInsert`, `Enseigne`) VALUES
(22, 5, 'jjjjjjjj', 'jjjjjjjjjjjj', 'réservée', 'jjjjjjjj', '2018-04-26', '78458596', '2018-04-03', 5),
(23, 5, 'yyyyyyyyyyyyyyyyyyyyyyy', 'ddddddddddddd', 'réservée', 'ddddddddddd', '2018-04-26', '78458965', '2018-04-03', 5),
(24, 6, 'firass', 'ihhuhu', 'Modifié', 'jgjggggg', '2018-04-29', '45779632', '2018-04-03', 5),
(25, 6, 'oumayma', 'galai', 'réservée', 'gkhkds', '2018-04-13', '25693654', '2018-04-05', 5);

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permalink` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `is_commentable` tinyint(1) NOT NULL,
  `num_comments` int(11) NOT NULL,
  `last_comment_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread`
--

INSERT INTO `thread` (`id`, `permalink`, `is_commentable`, `num_comments`, `last_comment_at`) VALUES
('3', 'http://localhost/madame/web/app_dev.php/admin/Events_read_admin?id=3', 1, 0, NULL),
('4', 'http://localhost/madame/web/app_dev.php/client/Events_read?id=4', 1, 1, '2018-02-28 17:02:40');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prenom` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cin` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `salaire` double DEFAULT NULL,
  `poste` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EtatBlocage` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numtel` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classe` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lien_fb` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lien_linkedin` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_2DA1797792FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_2DA17977A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_2DA17977C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `cin`, `salaire`, `poste`, `EtatBlocage`, `code`, `numtel`, `classe`, `image`, `lien_fb`, `lien_linkedin`, `role`) VALUES
(1, 'ahmed', 'ahmed', 'ahmed.hammouda1@esprit.tn', 'ahmed.hammouda1@esprit.tn', 1, NULL, 'lol', '2018-02-28 23:31:01', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'omar', 'omar', 'omar@h.com', 'omar@h.com', 1, NULL, '123', '2018-02-27 21:56:04', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'admin', 'admin', 'admin@gmail.com', 'admin@gmail.com', 1, NULL, '0000', '2018-03-01 07:06:02', NULL, NULL, 'a:1:{i:0;s:14:\"ROLE_SUPERUSER\";}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'omar1', 'omar1', 'dhouib60@gmail.com', 'dhouib60@gmail.com', 1, NULL, 'omar', '2018-02-28 23:15:38', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'jomjom841', 'jomjom841', 'jomjom841@gmail.com', 'jomjom841@gmail.com', 1, NULL, 'jomjom841', '2018-02-28 02:51:11', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'smsm', 'saleh', NULL, NULL, NULL, NULL, '359078', '69875944', 'salem', 'Media/User191580.jpg', NULL, NULL, '1'),
(6, 'oumayma', 'oumayma', 'oumayma@gmail.com', 'oumayma@gmail.com', 1, NULL, '123', '2018-03-01 10:59:27', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'oumayma', 'galai', '25984635', NULL, NULL, NULL, NULL, '54669363', NULL, NULL, NULL, NULL, NULL),
(7, 'oum', 'oum', 'oumayma@galai.com', 'oumayma@galai.com', 1, NULL, '123', '2018-03-01 11:35:39', NULL, NULL, 'a:1:{i:0;s:9:\"ROLE_USER\";}', 'oumayma', 'galai', '2558896354', NULL, NULL, NULL, NULL, '54896253', NULL, NULL, NULL, NULL, NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `acl_entries`
--
ALTER TABLE `acl_entries`
  ADD CONSTRAINT `FK_46C8B8063D9AB4A6` FOREIGN KEY (`object_identity_id`) REFERENCES `acl_object_identities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_46C8B806DF9183C9` FOREIGN KEY (`security_identity_id`) REFERENCES `acl_security_identities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_46C8B806EA000B10` FOREIGN KEY (`class_id`) REFERENCES `acl_classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `acl_object_identities`
--
ALTER TABLE `acl_object_identities`
  ADD CONSTRAINT `FK_9407E54977FA751A` FOREIGN KEY (`parent_object_identity_id`) REFERENCES `acl_object_identities` (`id`);

--
-- Contraintes pour la table `acl_object_identity_ancestors`
--
ALTER TABLE `acl_object_identity_ancestors`
  ADD CONSTRAINT `FK_825DE2993D9AB4A6` FOREIGN KEY (`object_identity_id`) REFERENCES `acl_object_identities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_825DE299C671CEA1` FOREIGN KEY (`ancestor_id`) REFERENCES `acl_object_identities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_6EEAA67DA455ACCF` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526CE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_9474526CEDAB66BE` FOREIGN KEY (`idevent`) REFERENCES `events` (`id`),
  ADD CONSTRAINT `FK_9474526CF675F31B` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `FK_D9BEC0C45E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_D9BEC0C4EDAB66BE` FOREIGN KEY (`idevent`) REFERENCES `events` (`id`);

--
-- Contraintes pour la table `enseignes`
--
ALTER TABLE `enseignes`
  ADD CONSTRAINT `FK_1139B55B6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FK_5387574A8D93D649` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `jaim`
--
ALTER TABLE `jaim`
  ADD CONSTRAINT `FK_BBF6A4678DDEEF7D` FOREIGN KEY (`idroduit`) REFERENCES `produit` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_BBF6A467E173B1B8` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD CONSTRAINT `FK_853B7939391C87D5` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `FK_853B7939C43FEE70` FOREIGN KEY (`idcommande`) REFERENCES `commande` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `notifiable_notification`
--
ALTER TABLE `notifiable_notification`
  ADD CONSTRAINT `FK_ADCFE0FAC3A0A4F8` FOREIGN KEY (`notifiable_entity_id`) REFERENCES `notifiable` (`id`),
  ADD CONSTRAINT `FK_ADCFE0FAEF1A9D84` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F5E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_AB55E24FEDAB66BE` FOREIGN KEY (`idevent`) REFERENCES `events` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_29A5EC276B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `promouvoir`
--
ALTER TABLE `promouvoir`
  ADD CONSTRAINT `FK_A81AFBCCA455ACCF` FOREIGN KEY (`idClient`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_48FCEBD36B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C849558D93D649` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_42C84955CEA215D8` FOREIGN KEY (`Enseigne`) REFERENCES `enseignes` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
