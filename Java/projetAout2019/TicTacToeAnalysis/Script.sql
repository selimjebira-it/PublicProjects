/* Create tables */
CREATE TABLE Joueur (
    jId INTEGER NOT NULL,
    jPseudo VARCHAR(30) NOT NULL,
    PRIMARY KEY (jId)
);

CREATE TABLE Participation (
    partJoueur INTEGER NOT NULL,
    partJeu INTEGER NOT NULL,
    PRIMARY KEY (partJoueur, partJeu)
);

CREATE TABLE MainGrid (
    mgID INTEGER NOT NULL,
    mgGagnant INTEGER,
    mgLittleGrid1 INTEGER,
    mgLittleGrid2 INTEGER,
    mgLittleGrid3 INTEGER,
    mgLittleGrid4 INTEGER,
    mgLittleGrid5 INTEGER,
    mgLittleGrid6 INTEGER,
    mgLittleGrid7 INTEGER,
    mgLittleGrid8 INTEGER,
    mgLittleGrid9 INTEGER,
    PRIMARY KEY (mgID)
);

CREATE TABLE LittleGrid (
    lgId INTEGER NOT NULL,
    lgGagnant INTEGER,
    lg1Gagnant INTEGER,
    lg2Gagnant INTEGER,
    lg3Gagnant INTEGER,
    lg4Gagnant INTEGER,
    lg5Gagnant INTEGER,
    lg6Gagnant INTEGER,
    lg7Gagnant INTEGER,
    lg8Gagnant INTEGER,
    lg9Gagnant INTEGER,
    PRIMARY KEY (lgId)
);

CREATE TABLE Jeu (
    jeuId INTEGER NOT NULL,
    jeuDebut TIMESTAMP NOT NULL,
    jeuFin TIMESTAMP NOT NULL,
    jeuGrid INTEGER NOT NULL,
    PRIMARY KEY (jeuId),
    UNIQUE (jeuGrid)
);

/* Create FK */
ALTER TABLE Participation ADD FOREIGN KEY (partJoueur) REFERENCES Joueur(jId);
ALTER TABLE Participation ADD FOREIGN KEY (partJeu) REFERENCES Jeu(jeuId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgGagnant) REFERENCES Joueur(jId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid1) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid2) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid3) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid4) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid5) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid6) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid7) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid8) REFERENCES LittleGrid(lgId);
ALTER TABLE MainGrid ADD FOREIGN KEY (mgLittleGrid9) REFERENCES LittleGrid(lgId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lgGagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg1Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg2Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg3Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg4Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg5Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg6Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg7Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg8Gagnant) REFERENCES Joueur(jId);
ALTER TABLE LittleGrid ADD FOREIGN KEY (lg9Gagnant) REFERENCES Joueur(jId);
ALTER TABLE Jeu ADD FOREIGN KEY (jeuGrid) REFERENCES MainGrid(mgID);

/* Comment tables */
COMMENT ON TABLE Joueur IS "Un joueur inscrit"
COMMENT ON TABLE Participation IS "Participation d'un joueur à un jeu"
COMMENT ON TABLE MainGrid IS "Une grande grille de jeu"
COMMENT ON TABLE LittleGrid IS "Une petite grille de jeu"
COMMENT ON TABLE Jeu IS "Un jeu"
/* Comment columns */
COMMENT ON COLUMN Joueur.jId IS "Id du joueur"
COMMENT ON COLUMN Joueur.jPseudo IS "Pseudo du joueur"

COMMENT ON COLUMN Participation.partJoueur IS "Id du joueur participant"
COMMENT ON COLUMN Participation.partJeu IS "Id du jeu auquel participe le joueur"

COMMENT ON COLUMN Jeu.jeuId IS "Id du jeu"
COMMENT ON COLUMN Jeu.jeuDebut IS "Date et heure du début de jeu"
COMMENT ON COLUMN Jeu.jeuFin IS "Date et heure de fin de jeu"
COMMENT ON COLUMN Jeu.jeuGrid IS "Id de la main grid"

COMMENT ON COLUMN MainGrid.mgId IS "Id de la main grid"
COMMENT ON COLUMN MainGrid.mgGagnant IS "ID du joueur gagnant de la main grid"
COMMENT ON COLUMN MainGrid.mgLittleGrid1 IS "Id de la little grid en haut à gauche"
COMMENT ON COLUMN MainGrid.mgLittleGrid2 IS "Id de la little grid en haut au milieu"
COMMENT ON COLUMN MainGrid.mgLittleGrid3 IS "Id de la little grid en haut à droite"
COMMENT ON COLUMN MainGrid.mgLittleGrid4 IS "Id de la little grid à gauche au milieu"
COMMENT ON COLUMN MainGrid.mgLittleGrid5 IS "Id de la little grid au centre"
COMMENT ON COLUMN MainGrid.mgLittleGrid6 IS "Id de la little grid à droite au milieu"
COMMENT ON COLUMN MainGrid.mgLittleGrid7 IS "Id de la little grid en bas à gauche"
COMMENT ON COLUMN MainGrid.mgLittleGrid8 IS "Id de la little grid en bas au milieu"
COMMENT ON COLUMN MainGrid.mgLittleGrid9 IS "Id de la little grid en bas à doite"

COMMENT ON COLUMN LittleGrid.lgId IS "Id de la little grid"
COMMENT ON COLUMN LittleGrid.lgGagnant IS "ID du joueur gagnant de la Little grid"
COMMENT ON COLUMN LittleGrid.lg1Gagnant IS "Id du gagnant de la case en haut à gauche"
COMMENT ON COLUMN LittleGrid.lg2Gagnant IS "Id du gagnant de la case en haut au milieu"
COMMENT ON COLUMN LittleGrid.lg3Gagnant IS "Id du gagnant de la case en haut à droite"
COMMENT ON COLUMN LittleGrid.lg4Gagnant IS "Id du gagnant de la case à gauche au milieu"
COMMENT ON COLUMN LittleGrid.lg5Gagnant IS "Id du gagnant de la case au centre"
COMMENT ON COLUMN LittleGrid.lg6Gagnant IS "Id du gagnant de la case à droite au milieu"
COMMENT ON COLUMN LittleGrid.lg7Gagnant IS "Id du gagnant de la case en bas à gauche"
COMMENT ON COLUMN LittleGrid.lg8Gagnant IS "Id du gagnant de la case en bas au milieu"
COMMENT ON COLUMN LittleGrid.lg9Gagnant IS "Id du gagnant de la case en bas à doite"

/* Insert joueurs */
INSERT INTO joueur (jId, jPseudo) VALUES
(0, 'JoueurTest0'),
(1, 'JoueurTest1'),
(2, 'JoueurTest2'),
(3, 'JoueurTest3');

/* Insert Little grid */
INSERT INTO littlegrid (lgId, lgGagnant, lg1Gagnant, lg2Gagnant, lg3Gagnant, lg4Gagnant, lg5Gagnant, lg6Gagnant, lg7Gagnant, lg8Gagnant, lg9Gagnant) VALUES
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

/* Insert main grid */
INSERT INTO maingrid (mgID, mgGagnant, mgLittleGrid1, mgLittleGrid2, mgLittleGrid3, mgLittleGrid4, mgLittleGrid5, mgLittleGrid6, mgLittleGrid7, mgLittleGrid8, mgLittleGrid9) VALUES
(0, NULL, 0, 1, 2, 3, 4, 5, 6, 7, 8),
(1, NULL, 9, 10, 11, 12, 13, 14, 15, 16, 17),
(2, 0, 18, 19, 20, 21, 22, 23, 24, 25, 26);

/* Insert jeu */
INSERT INTO jeu (jeuId, jeuDebut, jeuFin, jeuGrid) VALUES
(0, '2019-03-09 09:00:00', '1999-12-31 23:00:00', 0),
(1, '2019-03-09 10:00:00', '2019-03-09 10:00:00', 1),
(2, '2019-03-09 11:00:00', '2019-03-09 11:08:00', 2);

/* Insert participation */
INSERT INTO participation (partJoueur, partJeu) VALUES
(0, 0),
(1, 0),
(2, 1),
(2, 2),
(3, 1),
(3, 2);