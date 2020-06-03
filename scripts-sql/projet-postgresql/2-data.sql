SET search_path TO dreamdev;


-- Supprimer toutes les données
DELETE FROM permis;
DELETE FROM benevole;
DELETE FROM coureur;
DELETE FROM course;
DELETE FROM competition;
DELETE FROM plat;
DELETE FROM poste;

DELETE FROM service;
DELETE FROM concerner;
DELETE FROM memo;
DELETE FROM telephone;
DELETE FROM personne;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM compte;




-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr' ),
  (2, 'chef', 'chef', 'chef@3il.fr' ),
  (3, 'job', 'job', 'job@3il.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;

--postes

INSERT INTO poste (libelle, etat, heured, nombreplaces) VALUES 
  ('porter les sacs',En cours, 12:20,20),
    ('porter les maillots',En cours, 12:20,20),
      ('donner du sucre',En cours, 14:20,10),
        ('donner les repas',En cours, 15:20,400);
	

  





-- Role

INSERT INTO role (idcompte, role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'UTILISATEUR' ),
  ( 2, 'UTILISATEUR' ),
  ( 3, 'UTILISATEUR' );


-- Categorie
  
INSERT INTO categorie (idcategorie, libelle ) VALUES 
  (1, 'Employés' ),
  (2, 'Partenaires' ),
  (3, 'Clients' ),
  (4, 'Fournisseurs' ),
  (5, 'Dirigeants' );
 
ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 6;

*

-- Competition

INSERT INTO competition ( idcompetition, nom, lieu, date) VALUES 
  ( 1, 'Mini Bol-D air', 'France | Lyon','2020-06-26'),
  ( 2, 'Bol-D air', 'France | Lyon','2020-06-26');
ALTER TABLE competition ALTER COLUMN idcompetition RESTART WITH 5;
  
-- Course

INSERT INTO course ( idcourse,idcompetition, nom, heureD, distance,lieudepart,lieuarriv ) VALUES 
  ( 1, 1, 'Cheval','12:20:20', 500 ,'bastide','carnot'),
  ( 2, 1, 'Voiture','12:40:29', 600,'nexon','carnot'),
  ( 3, 2, 'Limoges',  '11:55:12', 100,'maryse','bastide'),
  ( 4, 2, ' Brive','1:00:00',250 ,'borie','belair');
ALTER TABLE course ALTER COLUMN idcourse RESTART WITH 5;

-- Personne

INSERT INTO personne (idpersonne, nom, prenom, sexe, naissance, telephone, adresse, codepostal, email) VALUES 
  ( 1, 'GRASSET', 'Jérôme', 'Homme', {d  '2001-05-18' }, '655286355', 'Lion de mer', 82000, 'jean@gmail.fr'),
  ( 2, 'LIONNE', 'Petite', 'Femme', {d  '2001-05-18' }, '655286358', 'tres loin', 82100, 'jean@gmail.fr'),
  ( 3, 'ade', 'raissa', 'Femme', {d  '2001-05-02' }, '655286355', 'France', 87000, 'aderaissa@gmail.fr'),
  ( 4, 'youmbi', 'yvan', 'Homme', {d  '2001-05-02' }, '655286355', 'ndokoti', 81000, 'youmbi@gmail.fr'),
  ( 5, 'ngwa', 'romeo leslie', 'Femme', {d  '2001-05-02' }, '655286355', 'loin des yeux pret du coeur', 82500, 'jean@gmail.fr'),
  ( 6, 'simo', 'bertin ulrich', 'Homme', {d  '2001-05-02' }, '655286355', 'man c', 82500, 'jean@gmail.fr'),
  ( 7, 'tchea gassam', 'jordan', 'Homme', {d  '2001-05-02' }, '655286355', 'rue des beaux arts', 82500, 'jean@gmail.fr'),
  ( 8, 'yokam krev', 'murielle', 'Femme', {d  '2001-05-02' }, '655286355', 'recherche moi bien', 82500, 'jean@gmail.fr'),
  ( 9, 'tateu', 'lindsey', 'Femme', {d  '2001-05-02' }, '655286355', 'Lion de mer', 82500, 'jean@gmail.fr'),
  ( 10, 'mouafo', 'paul', 'Homme', {d  '2001-05-02' }, '655286355', 'la carte ne dira point', 82500, 'jean@gmail.fr'),
  ( 11, 'assouke', 'sophia', 'Femme', {d  '2001-05-02' }, '655286355', 'reviens pas', 82500, 'jean@gmail.fr'),
  ( 12, 'ntayi djatou', 'boris', 'Homme', {d  '2001-05-02' }, '655286355', 'attention chien mechant', 82500, 'jean@gmail.fr'),
  ( 13, 'ntowo', 'anne', 'Femme', {d  '2001-05-02' }, '655286355', 'carrefour 2 bananes', 82500, 'jean@gmail.fr'),
  ( 14, 'tiwa', 'savage', 'Femme', {d  '2001-05-02' }, '655286355', 'messassi', 82500, 'jean@gmail.fr'),
  ( 15, 'tchinda', 'william', 'Homme', {d  '2001-05-02' }, '655286355', 'akwa', 82500, 'jean@gmail.fr'),
  ( 16, 'tekeu', 'sonia', 'Femme', {d  '2001-05-02' }, '655286355', 'Lion de mer', 82500, 'jean@gmail.fr'),
  ( 17, 'tekem', 'arthur', 'Homme', {d  '2001-05-02' }, '655286355', 'rue de la joie, limoges', 87000, 'jean@gmail.fr'),
  ( 18, 'takam', 'esdras', 'Homme', {d  '2001-05-02' }, '655286355', 'La rue des saintes', 82500, 'jean@gmail.fr'),
  ( 19, 'tampo', 'randi', 'Homme', {d  '2001-05-02' }, '655286355', 'assassina comploté', 82500, 'jean@gmail.fr'),
  ( 20, 'kamwa', 'vanessa', 'Femme', {d  '2001-05-02' }, '655286355', 'manga bell rue 93', 82500, 'jean@gmail.fr'),
  ( 21, 'siki siriki', 'megane', 'Homme', {d  '2001-05-02' }, '655286355', 'nyalla', 82500, 'jean@gmail.fr'),
  ( 22, 'nana tchomo', 'belle', 'Femme', {d  '2001-05-02' }, '655286355', 'ndokoti', 82500, 'jean@gmail.fr'),
  ( 23, 'sinkam', 'wilfried', 'Homme', {d  '2001-05-02' }, '655286355', 'jordanie', 82500, 'jean@gmail.fr'),
  ( 24, 'siewe', 'sandrine', 'Femme', {d  '2001-05-02' }, '655286355', 'bertoua', 82500, 'jean@gmail.fr'),
  ( 25, 'mussa malekoum', 'salam', 'Femme', {d  '2001-05-02' }, '655286355', 'lyon', 82500, 'jean@gmail.fr');

ALTER TABLE personne ALTER COLUMN idpersonne RESTART WITH 100;

-- Coureur

INSERT INTO coureur (idcoureur, poste, club,idcompetition) VALUES 
  ( 2, 'Capitaine', '3il', 1),
  ( 3, 'Equipier', '3il', 1),
  ( 4, 'Capitaine', 'dreamdev', 2),
  ( 5, 'Equipier', 'dreamdev', 2),
  ( 6, 'Capitaine', 'academy D', 2),
  ( 7, 'Equipier', 'academy D', 2),
  ( 8, 'Capitaine', 'les lions', 2),
  ( 9, 'Equipier', 'les lions', 2),
  ( 10, 'Capitaine', 'casse tete', 1),
  ( 11, 'Equipier', 'casse tete', 1),
  ( 12, 'Capitaine', 'password', 1),
  ( 13, 'Equipier', 'password', 1),
  ( 14, 'Capitaine', 'les alcooliques', 2),
  ( 15, 'Equipier', 'les alcooliques', 2);

--Benevole

INSERT INTO benevole (idbenevole, permanent,idcompetition) VALUES 
  ( 1, 't', 1),
  ( 16, 'f', 2),
  ( 17, 'f', 2),
  ( 18, 'f', 1),
  ( 19, 't', 1),
  ( 20, 'f', 2),
  ( 21, 't', 1),
  ( 22, 't', 2),
  ( 23, 't', 1),
  ( 24, 'f', 1),
  ( 25, 'f', 2);

-- Permis

INSERT INTO permis (idpermis, numero, datedelivrance) VALUES 
  ( 1, 'E54654J5465', '2020-04-30'),
  ( 18, 'E54654J5465', '2020-04-16'),
  ( 19, 'D46546F6566', '2020-04-25'),
  ( 20, 'R4654654D46', '2020-04-09'),
  ( 22, 'T465465D546', '2020-04-01'),
  ( 23, 'E5464579879', '2020-04-12'),
  ( 24, 'A46546546S5', '2020-04-05'),
  ( 25, 'T4565465G46', '2020-04-06');

-- Telephone

INSERT INTO telephone (idtelephone, idpersonne, libelle, numero ) VALUES 
  ( 11, 1, 'Portable', '06 11 11 11 11' ),
  ( 12, 1, 'Fax', '05 55 99 11 11' ),
  ( 13, 1, 'Bureau', '05 55 11 11 11' ),
  ( 21, 2, 'Portable', '06 22 22 22 22' ),
  ( 22, 2, 'Fax', '05 55 99 22 22' ),
  ( 23, 2, 'Bureau', '05 55 22 22 22' ),
  ( 31, 3, 'Portable', '06 33 33 33 33' ),
  ( 32, 3, 'Fax', '05 55 99 33 33' ),
  ( 33, 3, 'Bureau', '05 55 33 33 33' );

ALTER TABLE telephone ALTER COLUMN idtelephone RESTART WITH 100;


-- Memo

INSERT INTO memo (idmemo, titre, description, flagurgent, statut, effectif, budget, echeance) VALUES 
  ( 1, 'Mémo n°1', 'Texte du mémo n°1', TRUE,  2,   2,   1234.56,   {d  '2020-02-25' }),
  ( 2, 'Mémo n°2', 'Texte du mémo n°2', FALSE, 1,   4,   5000.00,   {d  '2020-05-18' }),
  ( 3, 'Mémo n°3', NULL, TRUE, 0, NULL, NULL, NULL);

ALTER TABLE memo ALTER COLUMN idmemo RESTART WITH 4;


-- Concerner

INSERT INTO concerner (idmemo, idPersonne) VALUES 
  ( 1, 1 ),
  ( 1, 2 ),
  ( 1, 3 ),
  ( 2, 1 ),
  ( 2, 2 );


-- Service

INSERT INTO service ( idservice, nom, anneecreation, flagsiege ) VALUES 
  ( 1, 'Direction', 2007, TRUE ),
  ( 2, 'Comptabilité', NULL, TRUE ),
  ( 3, 'Agence Limoges', 2008, FALSE ),
  ( 4, 'Agence Brive', 2014, FALSE );
ALTER TABLE service ALTER COLUMN idservice RESTART WITH 5;

-- Plat

INSERT INTO plat ( idplat, nom, nombre) VALUES 
  ( 1, 'Macaroni au beure de vache',30),
  ( 2, 'Merguez a l huile rouge',20),
  ( 3, 'Riz-sauté',6),
  ( 4, 'Marseilaise',18),
  ( 5, 'Ndole',50),
  ( 6, 'Oko o',25),
  ( 7, 'Sanga',40);
ALTER TABLE plat ALTER COLUMN idplat RESTART WITH 8;




