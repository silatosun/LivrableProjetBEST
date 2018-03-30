-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 29 Mars 2018 à 22:20
-- Version du serveur :  5.7.21-0ubuntu0.16.04.1
-- Version de PHP :  7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bestdata`
--

-- --------------------------------------------------------

--
-- Structure de la table `batiment`
--

CREATE TABLE `batiment` (
  `b_id` bigint(20) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `alerte` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `batiment`
--

INSERT INTO `batiment` (`b_id`, `nom`, `alerte`) VALUES
(5, 'Algeco', 0),
(6, 'Prunais', 0),
(8, 'Classe', 0),
(9, 'Copernic', 0);

-- --------------------------------------------------------

--
-- Structure de la table `batiment_sortie`
--

CREATE TABLE `batiment_sortie` (
  `Batiment_b_id` bigint(20) NOT NULL,
  `listSortie_so_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `batiment_sortie`
--

INSERT INTO `batiment_sortie` (`Batiment_b_id`, `listSortie_so_id`) VALUES
(6, 1),
(8, 2),
(5, 3),
(5, 4);

-- --------------------------------------------------------

--
-- Structure de la table `batiment_zone`
--

CREATE TABLE `batiment_zone` (
  `Batiment_b_id` bigint(20) NOT NULL,
  `listZone_z_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `batiment_zone`
--

INSERT INTO `batiment_zone` (`Batiment_b_id`, `listZone_z_id`) VALUES
(6, 11),
(8, 12),
(5, 10),
(5, 13),
(5, 14),
(5, 15),
(9, 16);

-- --------------------------------------------------------

--
-- Structure de la table `point`
--

CREATE TABLE `point` (
  `p_id` bigint(20) NOT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `point`
--

INSERT INTO `point` (`p_id`, `x`, `y`) VALUES
(21, 139, 137),
(42, 88, 787),
(46, 602, 810),
(68, 152, 757),
(70, 191, 495),
(71, 382, 512),
(72, 382, 512),
(89, 586, 88),
(90, 512, 1336),
(91, 512, 1336),
(92, 285, 464),
(93, 53, 1208),
(94, 613, 596),
(95, 613, 596),
(96, 613, 596),
(97, 283, 523),
(98, 283, 523),
(99, 359, 530),
(100, 341, 880),
(101, 341, 880),
(102, 607, 96),
(103, 341, 880),
(104, 609, 118),
(105, 341, 880),
(106, 563, 1304),
(107, 584, 1280),
(108, 586, 1270),
(109, 241, 206),
(110, 259, 544),
(111, 584, 106);

-- --------------------------------------------------------

--
-- Structure de la table `point_signale`
--

CREATE TABLE `point_signale` (
  `Point_p_id` bigint(20) NOT NULL,
  `listSignal_s_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `point_signale`
--

INSERT INTO `point_signale` (`Point_p_id`, `listSignal_s_id`) VALUES
(21, 59),
(21, 60),
(92, 61),
(94, 62),
(95, 63),
(96, 64),
(97, 65),
(98, 66),
(99, 67),
(100, 68),
(101, 69),
(103, 70),
(104, 71),
(105, 72),
(109, 73),
(110, 74);

-- --------------------------------------------------------

--
-- Structure de la table `signale`
--

CREATE TABLE `signale` (
  `s_id` bigint(20) NOT NULL,
  `mac` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rssi` int(11) DEFAULT NULL,
  `ssid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `signale`
--

INSERT INTO `signale` (`s_id`, `mac`, `rssi`, `ssid`) VALUES
(59, '24:95:04:98:f3:44', -41, 'SFR_F340'),
(60, '48:ee:0c:ed:8e:31', -42, 'dlink'),
(61, '48:ee:0c:ed:8e:31', -33, 'dlink'),
(62, '48:ee:0c:ed:8e:31', -79, 'dlink'),
(63, '48:ee:0c:ed:8e:31', -79, 'dlink'),
(64, '48:ee:0c:ed:8e:31', -79, 'dlink'),
(65, '48:ee:0c:ed:8e:31', -43, 'dlink'),
(66, '48:ee:0c:ed:8e:31', -43, 'dlink'),
(67, '48:ee:0c:ed:8e:31', -45, 'dlink'),
(68, '48:ee:0c:ed:8e:31', -43, 'dlink'),
(69, '48:ee:0c:ed:8e:31', -43, 'dlink'),
(70, '48:ee:0c:ed:8e:31', -43, 'dlink'),
(71, '48:ee:0c:ed:8e:31', -41, 'dlink'),
(72, '48:ee:0c:ed:8e:31', -43, 'dlink'),
(73, '48:ee:0c:ed:8e:31', -43, 'dlink'),
(74, '48:ee:0c:ed:8e:31', -44, 'dlink');

-- --------------------------------------------------------

--
-- Structure de la table `sortie`
--

CREATE TABLE `sortie` (
  `so_id` bigint(20) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `coordonne_p_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `sortie`
--

INSERT INTO `sortie` (`so_id`, `nom`, `coordonne_p_id`) VALUES
(1, 'porteprunais', 91),
(2, 'porteA07', 93),
(3, 'Sortie1', 107),
(4, 'sortie2', 111);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `u_id` bigint(20) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `useradmin`
--

CREATE TABLE `useradmin` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `useradmin`
--

INSERT INTO `useradmin` (`id`, `email`, `password`) VALUES
(3, 'test@test.com', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `zone`
--

CREATE TABLE `zone` (
  `z_id` bigint(20) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `zone`
--

INSERT INTO `zone` (`z_id`, `nom`) VALUES
(10, 'Zone1'),
(11, 'Chambre'),
(12, 'A07'),
(13, 'A09'),
(14, 'A08'),
(15, 'A10'),
(16, 'zone');

-- --------------------------------------------------------

--
-- Structure de la table `zone_point`
--

CREATE TABLE `zone_point` (
  `Zone_z_id` bigint(20) NOT NULL,
  `listPoint_p_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contenu de la table `zone_point`
--

INSERT INTO `zone_point` (`Zone_z_id`, `listPoint_p_id`) VALUES
(12, 94),
(12, 95),
(12, 96),
(11, 92),
(11, 97),
(11, 98),
(11, 99),
(14, 109),
(10, 100),
(10, 101),
(10, 103),
(10, 104),
(10, 105),
(10, 110);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `batiment`
--
ALTER TABLE `batiment`
  ADD PRIMARY KEY (`b_id`);

--
-- Index pour la table `batiment_sortie`
--
ALTER TABLE `batiment_sortie`
  ADD KEY `FK9ir1ei0e7v7910mcj175tkijw` (`listSortie_so_id`),
  ADD KEY `FK2xbdijxl89mgkbyuwgqeusm8c` (`Batiment_b_id`);

--
-- Index pour la table `batiment_zone`
--
ALTER TABLE `batiment_zone`
  ADD KEY `FK5g8pqel8f7fdm8gkao70cqymh` (`listZone_z_id`),
  ADD KEY `FK7ho3dgpmjgant0g4s16w6ve52` (`Batiment_b_id`);

--
-- Index pour la table `point`
--
ALTER TABLE `point`
  ADD PRIMARY KEY (`p_id`);

--
-- Index pour la table `point_signale`
--
ALTER TABLE `point_signale`
  ADD KEY `FKpo18u4178pxvxybgn9xliimr7` (`listSignal_s_id`),
  ADD KEY `FK37q1eml1l1d2oxegq3ivcfer6` (`Point_p_id`);

--
-- Index pour la table `signale`
--
ALTER TABLE `signale`
  ADD PRIMARY KEY (`s_id`);

--
-- Index pour la table `sortie`
--
ALTER TABLE `sortie`
  ADD PRIMARY KEY (`so_id`),
  ADD KEY `FK93chhun6ya9wbxon2c3ltbgp` (`coordonne_p_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`u_id`);

--
-- Index pour la table `useradmin`
--
ALTER TABLE `useradmin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`z_id`);

--
-- Index pour la table `zone_point`
--
ALTER TABLE `zone_point`
  ADD KEY `FK5ospiasog9ox07ffeoqkmurg3` (`listPoint_p_id`),
  ADD KEY `FKhnre46b1824ucq9wv46cdvf5m` (`Zone_z_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `batiment`
--
ALTER TABLE `batiment`
  MODIFY `b_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `point`
--
ALTER TABLE `point`
  MODIFY `p_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;
--
-- AUTO_INCREMENT pour la table `signale`
--
ALTER TABLE `signale`
  MODIFY `s_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;
--
-- AUTO_INCREMENT pour la table `sortie`
--
ALTER TABLE `sortie`
  MODIFY `so_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `u_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `useradmin`
--
ALTER TABLE `useradmin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `zone`
--
ALTER TABLE `zone`
  MODIFY `z_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `batiment_sortie`
--
ALTER TABLE `batiment_sortie`
  ADD CONSTRAINT `FK2xbdijxl89mgkbyuwgqeusm8c` FOREIGN KEY (`Batiment_b_id`) REFERENCES `batiment` (`b_id`),
  ADD CONSTRAINT `FK9ir1ei0e7v7910mcj175tkijw` FOREIGN KEY (`listSortie_so_id`) REFERENCES `sortie` (`so_id`);

--
-- Contraintes pour la table `batiment_zone`
--
ALTER TABLE `batiment_zone`
  ADD CONSTRAINT `FK5g8pqel8f7fdm8gkao70cqymh` FOREIGN KEY (`listZone_z_id`) REFERENCES `zone` (`z_id`),
  ADD CONSTRAINT `FK7ho3dgpmjgant0g4s16w6ve52` FOREIGN KEY (`Batiment_b_id`) REFERENCES `batiment` (`b_id`);

--
-- Contraintes pour la table `point_signale`
--
ALTER TABLE `point_signale`
  ADD CONSTRAINT `FK37q1eml1l1d2oxegq3ivcfer6` FOREIGN KEY (`Point_p_id`) REFERENCES `point` (`p_id`),
  ADD CONSTRAINT `FKpo18u4178pxvxybgn9xliimr7` FOREIGN KEY (`listSignal_s_id`) REFERENCES `signale` (`s_id`);

--
-- Contraintes pour la table `sortie`
--
ALTER TABLE `sortie`
  ADD CONSTRAINT `FK93chhun6ya9wbxon2c3ltbgp` FOREIGN KEY (`coordonne_p_id`) REFERENCES `point` (`p_id`);

--
-- Contraintes pour la table `zone_point`
--
ALTER TABLE `zone_point`
  ADD CONSTRAINT `FK5ospiasog9ox07ffeoqkmurg3` FOREIGN KEY (`listPoint_p_id`) REFERENCES `point` (`p_id`),
  ADD CONSTRAINT `FKhnre46b1824ucq9wv46cdvf5m` FOREIGN KEY (`Zone_z_id`) REFERENCES `zone` (`z_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
