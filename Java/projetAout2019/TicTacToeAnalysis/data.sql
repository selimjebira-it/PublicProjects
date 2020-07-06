--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`jId`, `jPseudo`) VALUES
(0, 'Player0'),
(1, 'Player1'),
(2, 'Player2'),
(3, 'Player3');

--
-- Déchargement des données de la table `littlegrid`
--

INSERT INTO `littlegrid` (`lgId`, `lgGagnant`, `lg1Gagnant`, `lg2Gagnant`, `lg3Gagnant`, `lg4Gagnant`, `lg5Gagnant`, `lg6Gagnant`, `lg7Gagnant`, `lg8Gagnant`, `lg9Gagnant`) VALUES
(0, 1, 1, 1, 1, NULL, 0, NULL, NULL, NULL, 1),
(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, NULL, 0, NULL, 0, NULL, 1, NULL, 1, NULL, 1),
(3, 1, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, 1),
(4, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, NULL),
(5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, 0),
(7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, 0, 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, 0),
(23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, 0, NULL, NULL, 0, NULL, 0, NULL, 0, NULL, NULL),
(21, 1, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, 1),
(20, 0, 0, 0, 0, 1, 1, NULL, 1, NULL, 1),
(19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(18, 1, 1, 1, 1, NULL, 0, NULL, NULL, NULL, 1);

--
-- Déchargement des données de la table `maingrid`
--

INSERT INTO `maingrid` (`mgID`, `mgGagnant`, `mgLittleGrid1`, `mgLittleGrid2`, `mgLittleGrid3`, `mgLittleGrid4`, `mgLittleGrid5`, `mgLittleGrid6`, `mgLittleGrid7`, `mgLittleGrid8`, `mgLittleGrid9`) VALUES
(0, NULL, 0, 1, 2, 3, 4, 5, 6, 7, 8),
(1, NULL, 9, 10, 11, 12, 13, 14, 15, 16, 17),
(2, 0, 18, 19, 20, 21, 22, 23, 24, 25, 26);

--
-- Déchargement des données de la table `jeu`
--

INSERT INTO `jeu` (`jeuId`, `jeuDebut`, `jeuFin`, `jeuGrid`) VALUES
(0, '2019-03-09 09:00:00', '1999-12-31 23:00:00', 0),
(1, '2019-03-09 10:00:00', '2019-03-09 10:00:00', 1),
(2, '2019-03-09 11:00:00', '2019-03-09 11:08:00', 2);

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`partJoueur`, `partJeu`) VALUES
(0, 0),
(1, 0),
(2, 1),
(2, 2),
(3, 1),
(3, 2);
COMMIT;
