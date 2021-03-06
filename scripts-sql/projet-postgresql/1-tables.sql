SET search_path TO dreamdev;


-- Schéma

DROP SCHEMA IF EXISTS dreamdev CASCADE;
CREATE SCHEMA dreamdev AUTHORIZATION dreamdev;
GRANT ALL PRIVILEGES ON SCHEMA dreamdev TO dreamdev;


-- Tables

CREATE TABLE compte (
	idcompte		INT				GENERATED BY DEFAULT AS IDENTITY,
	pseudo			VARCHAR(25)		NOT NULL,
	motdepasse		VARCHAR(25) 	NOT NULL,
	email			VARCHAR(100)	NOT NULL,
	PRIMARY KEY (idcompte),
	UNIQUE (pseudo)
);

CREATE TABLE role (
	idcompte		INT				NOT NULL,
	role			VARCHAR(20)		NOT NULL,
	CHECK( Role IN ('ADMINISTRATEUR','UTILISATEUR') ),	
	PRIMARY KEY (idcompte, role),
	FOREIGN KEY (idcompte) REFERENCES compte (idcompte)
);

CREATE TABLE personne (
	idpersonne		INT				GENERATED BY DEFAULT AS IDENTITY,
	nom				VARCHAR(25)  	NOT NULL,
	prenom			VARCHAR(25) 	NOT NULL,
	sexe			VARCHAR(5)		NOT NULL,
	naissance		DATE,
	telephone			VARCHAR(15),
	adresse			VARCHAR(100)	NOT NULL,
	codepostal		INT				NOT NULL,
	email			VARCHAR(100)	NOT NULL,
	PRIMARY KEY (idpersonne)
);

------------------------------------------------------------
-- Table: competition
------------------------------------------------------------

CREATE TABLE competition(
	idcompetition 		INT				GENERATED BY DEFAULT AS IDENTITY,
	nom 		VARCHAR(100),
	lieu		VARCHAR(100),
	date		DATE,
	PRIMARY KEY (idcompetition)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: course
------------------------------------------------------------

CREATE TABLE course (	
	idcourse		INT				GENERATED BY DEFAULT AS IDENTITY,
	idcompetition	INT NOT NULL,
	nom 			VARCHAR(50)		NOT NULL, 
	heureD			TIME   NOT NULL, 
	distance  		INT				NOT NULL	,
	lieudepart			VARCHAR(50)		NOT NULL, 
	lieuarriv			VARCHAR(50)   NOT NULL, 
	
	PRIMARY KEY (idcourse)
);

------------------------------------------------------------
-- Table: benevol
------------------------------------------------------------
CREATE TABLE benevole(
	idbenevole 		INT NOT NULL,
	permanent 			BOOLEAN NOT NULL,
	idcompetition	INT NOT NULL,
	PRIMARY KEY (idbenevole),
	FOREIGN KEY(idcompetition) REFERENCES competition(idcompetition),
	FOREIGN KEY (idbenevole) REFERENCES personne(idpersonne)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: permis
------------------------------------------------------------

CREATE TABLE permis(
	idpermis 		INT NOT NULL,
	numero 		VARCHAR(100),
	datedelivrance		DATE,
	PRIMARY KEY (idpermis),
	FOREIGN KEY (idpermis) REFERENCES benevole (idbenevole)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: poste
------------------------------------------------------------

CREATE TABLE poste(
	idposte 			INT	GENERATED BY DEFAULT AS IDENTITY,
	libelle 			VARCHAR(100),
	nombreplaces		INT NOT NULL,
	placesoccupees		INT NOT NUll,
	heured 				TIME,
	etat 				VARCHAR(20),
	PRIMARY KEY (idposte)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: coureur
------------------------------------------------------------
CREATE TABLE coureur(
	idcoureur 		INT NOT NULL,
	poste 			VARCHAR(10) NOT NULL,
	club			VARCHAR(30) NOT NULL,
	idcompetition	INT NOT NULL,
	PRIMARY KEY (idcoureur),
	FOREIGN KEY(idcompetition) REFERENCES competition(idcompetition),
	FOREIGN KEY (idcoureur) REFERENCES personne(idpersonne)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: plat
------------------------------------------------------------

CREATE TABLE plat(
	idplat		INT				GENERATED BY DEFAULT AS IDENTITY,
	nom 		VARCHAR(100),
	nombre		INT check(nombre > -1),
	PRIMARY KEY (idplat)
)WITHOUT OIDS;
------------------------------------------------------------
-- Table: materiel
------------------------------------------------------------

CREATE TABLE materiel(
	idmateriel			INT				GENERATED BY DEFAULT AS IDENTITY,
	nom 				VARCHAR(100),
	nombre				INT check(nombre > -1),
	nombredistribue		INT NOT NULL,
	PRIMARY KEY (idmateriel)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: attribuer
------------------------------------------------------------

CREATE TABLE attribuer(
	idposte		INT,
	idbenevole		INT,
	FOREIGN KEY (idposte) REFERENCES poste(idposte),
	FOREIGN KEY (idbenevole) REFERENCES benevole(idbenevole)
)WITHOUT OIDS;

------------------------------------------------------------
-- Table: distribuer
------------------------------------------------------------

CREATE TABLE distribuer(
	idmateriel		INT,
	idcoureur		INT,
	FOREIGN KEY (idmateriel) REFERENCES materiel(idmateriel),
	FOREIGN KEY (idcoureur) REFERENCES coureur(idcoureur)
)WITHOUT OIDS;


-- Vues

CREATE VIEW v_compte_avec_roles AS
	SELECT c.*, ARRAY_AGG( r.role ) AS roles
	FROM compte c 
	LEFT JOIN ROLE r ON c.idcompte = r.idcompte
	GROUP BY c.idcompte;

