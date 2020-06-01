-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2020 at 03:32 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bike`
--

-- --------------------------------------------------------

--
-- Table structure for table `abonnement`
--

CREATE TABLE `abonnement` (
  `ID_A` int(11) NOT NULL,
  `ID_P` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `abonnement`
--

INSERT INTO `abonnement` (`ID_A`, `ID_P`, `quantite`) VALUES
(34, 37, 34),
(35, 38, 4),
(36, 39, 5);

-- --------------------------------------------------------

--
-- Table structure for table `accessoire`
--

CREATE TABLE `accessoire` (
  `ID_P` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `ID_A` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `accessoire`
--

INSERT INTO `accessoire` (`ID_P`, `quantite`, `ID_A`) VALUES
(50, 10, 7),
(51, 23, 8),
(52, 50, 9),
(53, 20, 10);

-- --------------------------------------------------------

--
-- Table structure for table `achat`
--

CREATE TABLE `achat` (
  `id` int(11) NOT NULL,
  `ID_U` int(11) NOT NULL,
  `ID_A` int(11) NOT NULL,
  `DATE_D` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `DATE_F` datetime NOT NULL,
  `prix` double NOT NULL,
  `Image` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `achat`
--

INSERT INTO `achat` (`id`, `ID_U`, `ID_A`, `DATE_D`, `DATE_F`, `prix`, `Image`) VALUES
(70, 11, 34, '2020-05-14 19:18:08', '2020-06-13 19:18:08', 300, '32a64485ad202f696358513a3ae6f598.png');

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `ID_C` int(11) NOT NULL,
  `Lib_C` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Type` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`ID_C`, `Lib_C`, `Description`, `Type`) VALUES
(1, 'Premium', 'abonnement premium', 'Abonnement'),
(3, 'Essentiel', 'abonnement Essential', 'Abonnement'),
(4, 'Standard', 'abonnement Standard', 'Abonnement'),
(9, 'Bags', 'great selection of bike bags in the Outdoor Recreation store on Bike.', 'Accessoire'),
(10, 'Bottles', 'great selection of bike water bottles in the Outdoor Recreation store on Bike.', 'Accessoire'),
(11, 'Bike lights', 'a great selection of bike lights and reflectors in the Outdoor Recreation store on Bike.', 'Accessoire'),
(12, 'Locks', 'a great selection of bike locks in the Outdoor Recreation store on Bike.', 'Accessoire'),
(17, 'Bmx', 'heheheheqqqq', 'Velo'),
(20, 'categorie jdid', 'hehehe', 'Piece');

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `Libelle_P` varchar(255) NOT NULL,
  `Prix_P` float NOT NULL,
  `Quantite_P` int(11) NOT NULL,
  `Adresse` varchar(255) DEFAULT 'vide',
  `Ville` varchar(255) DEFAULT NULL,
  `Telephone` int(11) DEFAULT NULL,
  `ID_Commande` int(11) NOT NULL,
  `id` int(11) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `idcomment` int(11) NOT NULL,
  `idevenement` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `commentaire` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`idcomment`, `idevenement`, `iduser`, `commentaire`) VALUES
(59, 25, 10, 'hello'),
(60, 25, 11, 'mmm');

-- --------------------------------------------------------

--
-- Table structure for table `evenements`
--

CREATE TABLE `evenements` (
  `id` int(11) NOT NULL,
  `titre` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `rate` double DEFAULT NULL,
  `vote` int(11) DEFAULT NULL,
  `discription` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `lieu` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `nbreplaces` int(11) NOT NULL,
  `nbreparticipants` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `evenements`
--

INSERT INTO `evenements` (`id`, `titre`, `rate`, `vote`, `discription`, `DateDebut`, `DateFin`, `lieu`, `nbreplaces`, `nbreparticipants`, `image`) VALUES
(28, 'zz', 0, 0, 'ee', '2020-05-28', '2020-05-28', 'aa', 40, 0, '20200528063429.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `maitenance`
--

CREATE TABLE `maitenance` (
  `ID_M` int(11) NOT NULL,
  `ID_U` int(11) NOT NULL,
  `Problem` text COLLATE utf8_unicode_ci NOT NULL,
  `Prix` double NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DATE_D` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `DATE_F` datetime NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `maitenance`
--

INSERT INTO `maitenance` (`ID_M`, `ID_U`, `Problem`, `Prix`, `etat`, `image`, `DATE_D`, `DATE_F`, `type`) VALUES
(10, 6, '<p>problem dans **** bike</p>', 250, 'repare', '1e05f0041f7d5bba38913fb9cc95482f.jpeg', '2020-04-21 16:29:49', '2020-04-14 17:27:46', 'Velo'),
(11, 6, '<p>**** you this shit velo&nbsp;</p>', 550, 'repare', '10c961c3e0717ca9ad95c95164dba409.jpeg', '2020-04-21 17:06:45', '2020-04-20 18:04:36', 'Velo'),
(15, 11, '<p>zadzdazad</p>', 600, 'repare', '2a17576ef847974ce7fc92a20e0cbaf5.png', '2020-05-07 01:12:14', '2020-05-06 21:04:51', 'accessoire'),
(16, 11, '<p>leleleel</p>', 600, 'repare', '9724c0a34427ad3baf4d1766577d5984.png', '2020-05-14 18:47:16', '2020-05-07 18:04:32', 'accessoire'),
(20, 11, 'problem', 500, 'repare', '0861a099e9593791de261ebb86e75eacvel1.jpg', '2020-05-17 16:06:46', '2020-05-16 00:00:00', 'Velo'),
(23, 11, '**** piece', 500, 'repare', '9603358288cd1378f8464e8ebe24311cacc3.jpg', '2020-06-01 12:58:47', '2020-05-29 04:46:50', 'Piece');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `thread_id`, `sender_id`, `body`, `created_at`) VALUES
(1, 0, 10, 'hello', '2020-05-28 18:49:43'),
(2, 0, 11, 'qq', '2020-05-28 18:50:49'),
(3, 0, 11, 'cavaa ?', '2020-05-28 18:51:00'),
(4, 0, 10, 'hmdl', '2020-05-28 18:51:32');

-- --------------------------------------------------------

--
-- Table structure for table `message_metadata`
--

CREATE TABLE `message_metadata` (
  `id` int(11) NOT NULL,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `message_metadata`
--

INSERT INTO `message_metadata` (`id`, `message_id`, `participant_id`, `is_read`) VALUES
(1, 1, 11, 1),
(2, 1, 10, 1),
(3, 2, 11, 1),
(4, 2, 10, 1),
(5, 3, 11, 1),
(6, 3, 10, 1),
(7, 4, 11, 0),
(8, 4, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

CREATE TABLE `panier` (
  `id_panier` int(11) NOT NULL,
  `ID_P` int(11) NOT NULL,
  `Libelle_P` varchar(255) NOT NULL,
  `Quantite_P` int(11) NOT NULL,
  `Prix_P` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `participer`
--

CREATE TABLE `participer` (
  `id` int(11) NOT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idEvenement` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `participer`
--

INSERT INTO `participer` (`id`, `iduser`, `idEvenement`) VALUES
(5, 11, 27);

-- --------------------------------------------------------

--
-- Table structure for table `piece`
--

CREATE TABLE `piece` (
  `ID_Pi` int(11) NOT NULL,
  `ID_P` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `piece`
--

INSERT INTO `piece` (`ID_Pi`, `ID_P`, `quantite`, `etat`) VALUES
(6, 107, 60, 'Bonne etat'),
(7, 108, 50, 'Bonne etat');

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `ID_R` int(11) NOT NULL,
  `ID_U` int(11) NOT NULL,
  `ID_P` int(11) NOT NULL,
  `vote` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`ID_R`, `ID_U`, `ID_P`, `vote`) VALUES
(15, 6, 33, 4),
(16, 6, 39, 4),
(17, 6, 40, 4),
(19, 9, 38, 4),
(20, 9, 37, 4),
(21, 9, 39, 5),
(22, 6, 46, 5),
(31, 11, 37, 5),
(32, 11, 53, 4),
(33, 11, 50, 3);

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `ID` int(11) NOT NULL,
  `ID_U` int(11) NOT NULL,
  `Contenu` text COLLATE utf8_unicode_ci NOT NULL,
  `Type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `traite` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`ID`, `ID_U`, `Contenu`, `Type`, `etat`, `image`, `traite`) VALUES
(10, 11, '<p>azdzadzad</p>', 'Abonnment', 'Mauvais', '35c4740fe969757a89e92dda810c98a1.png', 'Traite'),
(11, 11, '<p>zadzaddz</p>', 'Abonnment', 'Bien', 'e4117416aea1fb0533e48cf82c48fb2c.jpeg', 'Traite'),
(12, 11, '<p>zadzaddz</p>', 'Abonnment', 'mauvais', '5c85e6066c358232f1f728aa8eca6a98.jpeg', 'Traite'),
(13, 11, 'problem	', 'Abonnement', 'Mauvais', '0ee0f58c709ea1c865a408f5c0c9c7acacc2.jpg', 'Traite'),
(14, 11, '<p>zdazadzadzda</p>', 'Abonnment', 'Bien', '566c3587daa2bed9816e51a29d2587b7.jpeg', 'Traite');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `idR` int(11) NOT NULL,
  `Date_D` date NOT NULL,
  `Date_F` date NOT NULL,
  `prix` double NOT NULL,
  `idV` int(11) NOT NULL,
  `ID_U` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`idR`, `Date_D`, `Date_F`, `prix`, `idV`, `ID_U`) VALUES
(9, '2020-07-08', '2020-09-07', 30500, 2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `site`
--

CREATE TABLE `site` (
  `capacite` int(11) NOT NULL,
  `lieu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Lib_S` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `id_S` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `site`
--

INSERT INTO `site` (`capacite`, `lieu`, `Lib_S`, `id_S`) VALUES
(200, 'Menzah 1', 'M1', 1),
(200, 'Menzah 5', 'M5', 2),
(500, 'Kef', 'HDR', 3),
(500, 'menzah 6', 'm6', 17);

-- --------------------------------------------------------

--
-- Table structure for table `sponsor`
--

CREATE TABLE `sponsor` (
  `idsponsor` int(11) NOT NULL,
  `ide` int(11) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `typesponsor` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nomsponsor` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `thread`
--

CREATE TABLE `thread` (
  `id` int(11) NOT NULL,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thread`
--

INSERT INTO `thread` (`id`, `created_by_id`, `subject`, `created_at`, `is_spam`) VALUES
(0, 10, 'reclamation', '2020-05-28 18:49:43', 0);

-- --------------------------------------------------------

--
-- Table structure for table `thread_metadata`
--

CREATE TABLE `thread_metadata` (
  `id` int(11) NOT NULL,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thread_metadata`
--

INSERT INTO `thread_metadata` (`id`, `thread_id`, `participant_id`, `is_deleted`, `last_participant_message_date`, `last_message_date`) VALUES
(1, 0, 11, 0, '2020-05-28 18:51:00', '2020-05-28 18:51:32'),
(2, 0, 10, 0, '2020-05-28 18:51:32', '2020-05-28 18:51:00');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `First_Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `User_Number` int(11) NOT NULL,
  `User_Image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `User_age` int(11) NOT NULL,
  `Last_Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `First_Name`, `User_Number`, `User_Image`, `User_age`, `Last_Name`) VALUES
(10, 'aymen', 'aymen', 'bike.pidev@gmail.com', 'bike.pidev@gmail.com', 1, NULL, '$2y$13$fakWnuhqR7eJ8rR5YwVteuFdhUMTFLEacodOMCrSSV2IAy3TTOXi2', '2020-05-28 18:51:17', 'd9epBu7B9X2s0k67bAr6wB50enyM8Phhw--c95mMiHs', '2020-05-04 05:28:54', 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'aymen', 53059656, '6e282f40881070bdabbd8624ee25a801aymen.png', 21, 'ouerghui'),
(11, 'rayen', 'rayen', 'aymen.ouerghui@esprit.tn', 'aymen.ouerghui@esprit.tn', 1, NULL, '$2y$10$RStzkN4tmVNerP0BZUUsnuCQBXksedAgPk8AINsnSqyp7p0q7Ei5q', '2020-05-28 19:48:42', 'aDlG3DwawOAhgjwfuXcj2HmfJ9zUDHK4dkoxNwKenkQ', '2020-05-04 05:34:31', 'a:0:{}', 'rayen', 53059655, '10afd4a713622023b3fb330eb19d774bprofile-adam-levine.png', 26, 'ouerghui');

-- --------------------------------------------------------

--
-- Table structure for table `velo`
--

CREATE TABLE `velo` (
  `id` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(11) NOT NULL,
  `couleur` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idA` int(11) NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `velo`
--

INSERT INTO `velo` (`id`, `type`, `age`, `couleur`, `idA`, `etat`) VALUES
(2, 'x500', 10, 'ddd', 44, 'non reservee'),
(3, 'x501', 14, 'orange', 47, 'non reservee');

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE `vote` (
  `id` int(11) NOT NULL,
  `id_comm` int(11) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vote`
--

INSERT INTO `vote` (`id`, `id_comm`, `id_client`, `type`) VALUES
(27, 26, 11, 2),
(28, 25, 24, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vote2`
--

CREATE TABLE `vote2` (
  `id` int(11) NOT NULL,
  `id_comm` int(11) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vote2`
--

INSERT INTO `vote2` (`id`, `id_comm`, `id_client`, `type`) VALUES
(3, 59, 11, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`ID_A`),
  ADD KEY `FK_ID_p` (`ID_P`);

--
-- Indexes for table `accessoire`
--
ALTER TABLE `accessoire`
  ADD PRIMARY KEY (`ID_A`),
  ADD KEY `FK_idproduitss` (`ID_P`);

--
-- Indexes for table `achat`
--
ALTER TABLE `achat`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_26A9845696E90957` (`ID_U`),
  ADD KEY `FK_id_u` (`ID_U`),
  ADD KEY `FK_id_a` (`ID_A`);

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`ID_C`);

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`ID_Commande`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`idcomment`),
  ADD KEY `IDX_9474526C753DC1EB` (`idevenement`),
  ADD KEY `IDX_9474526C5E5C27E9` (`iduser`);

--
-- Indexes for table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `maitenance`
--
ALTER TABLE `maitenance`
  ADD PRIMARY KEY (`ID_M`),
  ADD KEY `FK_id_u` (`ID_U`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B6BD307FE2904019` (`thread_id`),
  ADD KEY `IDX_B6BD307FF624B39D` (`sender_id`);

--
-- Indexes for table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_4632F005537A1329` (`message_id`),
  ADD KEY `IDX_4632F0059D1C3019` (`participant_id`);

--
-- Indexes for table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id_panier`);

--
-- Indexes for table `participer`
--
ALTER TABLE `participer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_EDBE16F8F7CC4348` (`idEvenement`),
  ADD KEY `IDX_EDBE16F85E5C27E9` (`iduser`);

--
-- Indexes for table `piece`
--
ALTER TABLE `piece`
  ADD PRIMARY KEY (`ID_Pi`),
  ADD KEY `FK_ID_p` (`ID_P`);

--
-- Indexes for table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`ID_R`),
  ADD KEY `FK_id_u` (`ID_U`),
  ADD KEY `FK_ID_p` (`ID_P`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_rec` (`ID_U`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idR`);

--
-- Indexes for table `site`
--
ALTER TABLE `site`
  ADD PRIMARY KEY (`id_S`);

--
-- Indexes for table `sponsor`
--
ALTER TABLE `sponsor`
  ADD PRIMARY KEY (`idsponsor`),
  ADD KEY `IDX_818CC9D4840E12C9` (`ide`);

--
-- Indexes for table `thread`
--
ALTER TABLE `thread`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_31204C83B03A8386` (`created_by_id`);

--
-- Indexes for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_40A577C8E2904019` (`thread_id`),
  ADD KEY `IDX_40A577C89D1C3019` (`participant_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`);

--
-- Indexes for table `velo`
--
ALTER TABLE `velo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vote`
--
ALTER TABLE `vote`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_5A108564232C1FE1` (`id_comm`),
  ADD KEY `id_client` (`id_client`);

--
-- Indexes for table `vote2`
--
ALTER TABLE `vote2`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_50500BC9232C1FE1` (`id_comm`),
  ADD KEY `id_client` (`id_client`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `ID_A` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `accessoire`
--
ALTER TABLE `accessoire`
  MODIFY `ID_A` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `achat`
--
ALTER TABLE `achat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `ID_C` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `ID_Commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `idcomment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `maitenance`
--
ALTER TABLE `maitenance`
  MODIFY `ID_M` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `message_metadata`
--
ALTER TABLE `message_metadata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `panier`
--
ALTER TABLE `panier`
  MODIFY `id_panier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `participer`
--
ALTER TABLE `participer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `piece`
--
ALTER TABLE `piece`
  MODIFY `ID_Pi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rating`
--
ALTER TABLE `rating`
  MODIFY `ID_R` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `vote2`
--
ALTER TABLE `vote2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);

--
-- Constraints for table `vote2`
--
ALTER TABLE `vote2`
  ADD CONSTRAINT `FK_50500BC9232C1FE1` FOREIGN KEY (`id_comm`) REFERENCES `comment` (`idcomment`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_50500BC9E173B1B8` FOREIGN KEY (`id_client`) REFERENCES `user` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
