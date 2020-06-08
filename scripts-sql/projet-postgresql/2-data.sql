SET search_path TO dreamdev;


-- Supprimer toutes les données
DELETE FROM permis;
DELETE FROM attribuer;
DELETE FROM poste;
DELETE FROM benevole;
DELETE FROM coureur;
DELETE FROM course;
DELETE FROM competition;
DELETE FROM personne;
DELETE FROM role;
DELETE FROM compte;
DELETE FROM plat;




-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES 
  (1, 'dreamdev', 'dreamdev', 'dreamdev@3il.fr' ),
  (2, 'jordan', 'jordan', 'jordan@3il.fr' ),
  (3, 'zidane', 'zidane', 'zidane@3il.fr' ),
  (4, 'lindsey', 'lindsey', 'lindsey@3il.fr' ),
  (5, 'laurent', 'laurent', 'laurent@3il.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;
	
-- Role

INSERT INTO role (idcompte, role) VALUES 
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'UTILISATEUR' ),
  ( 2, 'ADMINISTRATEUR' ),
  ( 3, 'UTILISATEUR' ),
  ( 4, 'UTILISATEUR' ),
  ( 5, 'UTILISATEUR' ),
  ( 2, 'UTILISATEUR' );

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
  ( 4, 2, ' Brive','1:00:00',250 ,'borie','belair'),
  ( 5, 1, 'Toulouse',  '11:55:12', 100,'don t know','don t know'),
  ( 6, 2, ' Lyon amsterdam','1:00:00',250 ,'don t know','don t know');
ALTER TABLE course ALTER COLUMN idcourse RESTART WITH 5;

-- Personne

INSERT INTO personne (idpersonne, nom, prenom, sexe, naissance, telephone, adresse, codepostal, email) VALUES 
  ( 1, 'GRASSET', 'Jérôme', 'Homme', {d  '2001-05-18' }, '655286355', 'Lion de mer', 82000, 'jean68@gmail.fr'),
  ( 2, 'LIONNE', 'Petite', 'Femme', {d  '2001-05-18' }, '655286358', 'tres loin', 82100, 'jean65@gmail.fr'),
  ( 3, 'ade', 'raissa', 'Femme', {d  '2001-05-02' }, '655286355', 'France', 87000, 'aderaissa@gmail.fr'),
  ( 4, 'youmbi', 'yvan', 'Homme', {d  '2001-05-02' }, '655286355', 'ndokoti', 81000, 'youmbi@gmail.fr'),
  ( 5, 'ngwa', 'romeo leslie', 'Femme', {d  '2001-05-02' }, '655286355', 'loin des yeux pret du coeur', 82500, 'jean@gmail.fr'),
  ( 6, 'simo', 'bertin ulrich', 'Homme', {d  '2001-05-02' }, '655286355', 'man c', 82500, 'jean26@gmail.fr'),
  ( 7, 'tchea gassam', 'jordan', 'Homme', {d  '2001-05-02' }, '655286355', 'rue des beaux arts', 82500, 'jean25@gmail.fr'),
  ( 8, 'yokam krev', 'murielle', 'Femme', {d  '2001-05-02' }, '655286355', 'recherche moi bien', 82500, 'jean24@gmail.fr'),
  ( 9, 'tateu', 'lindsey', 'Femme', {d  '2001-05-02' }, '655286355', 'Lion de mer', 82500, 'jean23@gmail.fr'),
  ( 10, 'mouafo', 'paul', 'Homme', {d  '2001-05-02' }, '655286355', 'la carte ne dira point', 82500, 'jean22@gmail.fr'),
  ( 11, 'assouke', 'sophia', 'Femme', {d  '2001-05-02' }, '655286355', 'reviens pas', 82500, 'jean21@gmail.fr'),
  ( 12, 'ntayi djatou', 'boris', 'Homme', {d  '2001-05-02' }, '655286355', 'attention chien mechant', 82500, '20jean@gmail.fr'),
  ( 13, 'ntowo', 'anne', 'Femme', {d  '2001-05-02' }, '655286355', 'carrefour 2 bananes', 82500, 'jean19@gmail.fr'),
  ( 14, 'tiwa', 'savage', 'Femme', {d  '2001-05-02' }, '655286355', 'messassi', 82500, 'jean18@gmail.fr'),
  ( 15, 'tchinda', 'william', 'Homme', {d  '2001-05-02' }, '655286355', 'akwa', 82500, 'jean17@gmail.fr'),
  ( 16, 'tekeu', 'sonia', 'Femme', {d  '2001-05-02' }, '655286355', 'Lion de mer', 82500, 'jean16@gmail.fr'),
  ( 17, 'tekem', 'arthur', 'Homme', {d  '2001-05-02' }, '655286355', 'rue de la joie, limoges', 87000, 'jean15@gmail.fr'),
  ( 18, 'takam', 'esdras', 'Homme', {d  '2001-05-02' }, '655286355', 'La rue des saintes', 82500, 'jean14@gmail.fr'),
  ( 19, 'tampo', 'randi', 'Homme', {d  '2001-05-02' }, '655286355', 'assassina comploté', 82500, 'jean13@gmail.fr'),
  ( 20, 'kamwa', 'vanessa', 'Femme', {d  '2001-05-02' }, '655286355', 'manga bell rue 93', 82500, 'jean12@gmail.fr'),
  ( 21, 'siki siriki', 'megane', 'Homme', {d  '2001-05-02' }, '655286355', 'nyalla', 82500, 'jean11@gmail.fr'),
  ( 22, 'nana tchomo', 'belle', 'Femme', {d  '2001-05-02' }, '655286355', 'ndokoti', 82500, 'jean10@gmail.fr'),
  ( 23, 'sinkam', 'wilfried', 'Homme', {d  '2001-05-02' }, '655286355', 'jordanie', 82500, 'jean9@gmail.fr'),
  ( 24, 'siewe', 'sandrine', 'Femme', {d  '2001-05-02' }, '655286355', 'bertoua', 82500, 'jean8@gmail.fr'),
  ( 25, 'mussa malekoum', 'salam', 'Femme', {d  '2001-05-02' }, '655286355', 'lyon', 82500, 'jean7@gmail.fr'),
  ( 26, 'kamwa1', 'vanessa1', 'Femme', {d  '2001-05-02' }, '655286355', 'manga bell rue 93', 82500, 'jean6@gmail.fr'),
  ( 27, 'siki siriki1', 'megane1', 'Homme', {d  '2001-05-02' }, '655286355', 'nyalla', 82500, 'jean5@gmail.fr'),
  ( 28, 'nana tchomo1', 'belle1', 'Femme', {d  '2001-05-02' }, '655286355', 'ndokoti', 82500, 'jean4@gmail.fr'),
  ( 29, 'sinkam1', 'wilfried1', 'Homme', {d  '2001-05-02' }, '655286355', 'jordanie', 82500, 'jean3@gmail.fr'),
  ( 30, 'siewe1', 'sandrine1', 'Femme', {d  '2001-05-02' }, '655286355', 'bertoua', 82500, 'jean2@gmail.fr'),
  ( 31, 'mussa malekoum1', 'salam1', 'Femme', {d  '2001-05-02' }, '655286355', 'lyon', 82500, 'jean1@gmail.fr'),
  ( 32, 'sinkam2', 'wilfried', 'Homme', {d  '2001-05-02' }, '655286355', 'jordanie', 82500, 'jean99@gmail.fr'),
  ( 33, 'siewe2', 'sandrine', 'Femme', {d  '2001-05-02' }, '655286355', 'bertoua', 82500, 'jean89@gmail.fr'),
  ( 34, 'mussa malekoum2', 'salam2', 'Femme', {d  '2001-05-02' }, '655286355', 'lyon', 82500, 'jean79@gmail.fr'),
  ( 35, 'kamwa12', 'vanessa12', 'Femme', {d  '2001-05-02' }, '655286355', 'manga bell rue 93', 82500, 'jean96@gmail.fr'),
  ( 36, 'siki siriki12', 'megane12', 'Homme', {d  '2001-05-02' }, '655286355', 'nyalla', 82500, 'jean59@gmail.fr'),
  ( 37, 'nana tchomo12', 'belle12', 'Femme', {d  '2001-05-02' }, '655286355', 'ndokoti', 82500, 'jean94@gmail.fr'),
  ( 38, 'sinkam12', 'wilfried12', 'Homme', {d  '2001-05-02' }, '655286355', 'jordanie', 82500, 'jean83@gmail.fr'),
  ( 39, 'siewe12', 'sandrine12', 'Femme', {d  '2001-05-02' }, '655286355', 'bertoua', 82500, 'jean25@gmail.fr'),
  ( 40, 'mussa malekoum12', 'salam12', 'Femme', {d  '2001-05-02' }, '655286355', 'lyon', 82500, 'jean12@gmail.fr');;

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
  ( 15, 'Equipier', 'les alcooliques', 2),
  ( 26, 'Capitaine', 'club1', 1),
  ( 27, 'Equipier', 'club1', 1),
  ( 28, 'Capitaine', 'club2', 2),
  ( 29, 'Equipier', 'club2', 2),
  ( 30, 'Capitaine', 'club3', 1),
  ( 31, 'Equipier', 'club3', 1);

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
  ( 25, 'f', 2),
  ( 32, 'f', 2),
  ( 33, 'f', 1),
  ( 34, 't', 1),
  ( 35, 'f', 2),
  ( 36, 't', 1),
  ( 37, 't', 2),
  ( 38, 't', 1),
  ( 39, 'f', 1),
  ( 40, 'f', 2);

-- Permis

INSERT INTO permis (idpermis, numero, datedelivrance) VALUES 
  ( 1, 'E54654J5465', '2020-04-30'),
  ( 18, 'E54654J5465', '2020-04-16'),
  ( 19, 'D46546F6566', '2020-04-25'),
  ( 20, 'R4654654D46', '2020-04-09'),
  ( 22, 'T465465D546', '2020-04-01'),
  ( 23, 'E5464579879', '2020-04-12'),
  ( 24, 'A46546546S5', '2020-04-05'),
  ( 25, 'T4565465G46', '2020-04-06'),
  ( 33, 'IGI45654664', '2020-04-09'),
  ( 34, 'SYVUSVY4546', '2020-04-01'),
  ( 35, 'NBV65468787', '2020-04-12'),
  ( 36, 'POJOI654788', '2020-04-05'),
  ( 37, 'AFQ65481651', '2020-04-06');

  --postes

INSERT INTO poste (libelle, etat, heured, nombreplaces, placesoccupees) VALUES 
  ('porter les sacs','En cours', '12:20', 20, 0),
  ('porter les maillots','En cours', '12:20', 2, 0),
  ('donner du sucre','En cours', '14:20', 10, 0),
  ('donner les repas','En cours', '15:20', 400, 0);
  
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

-- Materiel

INSERT INTO materiel ( idmateriel, nom, nombre, nombredistribue) VALUES 
  ( 1, 'dossards',30, 0),
  ( 2, 'canoes',20, 0),
  ( 3, 'gilets',6, 0),
  ( 4, 'pagaies',18, 0);
ALTER TABLE materiel ALTER COLUMN idmateriel RESTART WITH 5;


