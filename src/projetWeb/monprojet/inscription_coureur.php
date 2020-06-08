<!DOCTYPE html>
<html>
<head>
	<title>inscription benevole</title>
	<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style.css">

	<script type="text/javascript">

		function calculer()
		{
			

			var x =document.getElementById("platsup").value;
			var y =document.getElementById("prix").value=x*20;

	

		}

	</script>
		
</head>
<body>

<form name="form1" method="POST" action="">
	<div class='form-group' id="div1">

		<center><h2><b>CAPITAINE</b></h2></center>
		
		<label for='nom1'>Nom</label>
		 <input type="text" class='form-control' name="nom1" value="berrin1">

		<label for='prenom1'>Prenom </label>
		<input type="text" class='form-control' name="prenom1" value="pieere1">

		<label for='datenaiss1'>Date de naissance</label> 
		<input type="text" class='form-control'name="datenaiss1" value="14/10/2003">
		
		<label for='numtel1' >Telephone</label> 
		<input type="number"class='form-control' name="numtel1" value="1111155555">
		
		<label for='adresse1'>Adresse</label>
		 <input type="text" name="adresse1" class='form-control' value="19 rue bonbon1">

		<label for='codepostal1'>Code postal</label>
		 <input type="text" name="codepostal1" class='form-control' value="9700">

		<label for='email1'>Email</label>
		 <input type='Email' name="email1" class='form-control' value="zzz@gamiloooooo.com">
		 <br>
		<label for='sexe1' >Sexe</label>
		<input type="radio" name="sexe1" checked value='Homme'>Homme
		<input type="radio" name="sexe1"  value='Femme'>Femme
		
		<br>

		<label for='numero'>Numero du permis de conduire</label>
		<input type="text"  class='form-control' name="permis1" value="eeee111rrr11">

		
		<label for='datepermis1'>Date de delivrance du permis</label>
		 <input type="text"  class='form-control' name="datepermis1" value="14/04/2014">

		 <br>

		 <input type='checkbox' name='condition1' checked> Condition d'utilisation
		

		
	</div>

	<div class='form-group' id='div2'>
			<center><h2><b>EQUIPIER</b></h2></center>
		
		<label for='nom1'>Nom</label>
		 <input type="text" class='form-control' name="nom2" value="berrin1">

		<label for='prenom1'>Prenom </label>
		<input type="text" class='form-control' name="prenom2" value="pieere1">

		<label for='datenaiss2'>Date de naissance</label> 
		<input type="text" class='form-control'name="datenaiss2" value="24/10/2003">
		
		<label for='numtel2' >Telephone</label> 
		<input type="number"class='form-control' name="numtel2" value="1111155555">
		
		<label for='adresse1'>Adresse</label>
		 <input type="text" name="adresse2" class='form-control' value="19 rue bonbon1">

		<label for='codepostal1'>Code postal</label>
		 <input type="text" name="codepostal2" class='form-control' value="9700">

		<label for='emai1'>Email</label>
		 <input type="text" name="email2" class='form-control' value="zzz@gamil.com">

		 <br>
		<label for='sexe2' >Sexe</label>
		<input type="radio" name="sexe2"  checked value='Homme'>Homme
		<input type="radio" name="sexe2"  value='Femme'>Femme
		
		<br>

		<label for='numero'>Numero du permis de conduire</label>
		<input type="text"  class='form-control' name="permis2" value="eeee111rrr11">

		
		<label for='datepermis1'>Date de delivrance du permis</label>
		 <input type="text"  class='form-control' name="datepermis2" value="14/04/2014">

		 <br>

		 <input type='checkbox' name='condition2' checked > Condition d'utilisation
		
	</div>

	<div id='div3' class='form-group'>
		<center><h2><b>OPTIONS COURSE</b></h2></center>
			<br><br>

		<label for='nbplats'>Nombre de plats supps</label>
		<input type="number" name='nbplat' id='platsup' value='0' onkeyup="calculer()">
		* 20€ = <input type='number' name='prix' value='0' id='prix' onkeyup="calculer()">

		<label for='course'>Choix de la course : </label>
		<input type='radio' name='course' value='1' checked >mini  Bol dair
		<input type='radio' name='course' value='2'> Bol d'air 

		<br>
		<label for='club'>Nom du club</label>
		 <input type="text" class='form-control' name="nomclub">
		<br>

		<button type="submit" class="btn btn-primary form-control">Enregistrer</button>
		
	</div>

</form>


<?php
include("connexion.php");

		if(isset($_POST["nom1"]))
		{

			//capitaine
			$nom1=$_POST["nom1"];
			$prenom1=$_POST["prenom1"];
			$datenaiss1=$_POST["datenaiss1"];
			
			$numtele1=$_POST["numtel1"];
			$adresse1=$_POST["adresse1"];
			$codepostal1=$_POST["codepostal1"];
			$email1=$_POST["email1"];
			$sexe1=$_POST["sexe1"];
			$permis1=$_POST["permis1"];
			$datepermis1=$_POST["datepermis1"];
			$conditions1=$_POST["condition1"];


			//equipier
			$nom2=$_POST["nom2"];
			$prenom2=$_POST["prenom2"];
			$datenaiss2=$_POST["datenaiss2"];
			
			$numtele2=$_POST["numtel2"];
			$adresse2=$_POST["adresse2"];
			$codepostal2=$_POST["codepostal2"];
			$email2=$_POST["email2"];
			$sexe2=$_POST["sexe2"];
			$permis2=$_POST["permis2"];
			$datepermis2=$_POST["datepermis2"];
			$conditions2=$_POST["condition2"];

			//infos course
			$course=$_POST["course"];
			$nbplat=$_POST["nbplat"];
			$nomclub=$_POST["nomclub"];



			echo " <br>";

			//requette d'insertio des donnees 

			//$conn= new PDO('mysql:host=127.0.0.1;dbname=nomdelabase', 'user', 'motdepasse');
			$stmt = $con->prepare('INSERT INTO personne(nom,prenom,sexe,naissance,telephone,adresse,codepostal
				,email)  VALUES (:nom, :prenom, :sexe, :naissance, :telephone, :adresse, :codepostal, :email)');

			$stmt2= $con->prepare('SELECT idpersonne FROM personne ORDER BY idpersonne DESC
									 LIMIT 1');

			/*select*
from dreamdev.personne inner join  dreamdev.coureur
on dreamdev.coureur.idcoureur=dreamdev.personne.idpersonne*/

			$stmt3= $con->prepare('INSERT INTO dreamdev.coureur(idcoureur,poste,club,idcompetition)
						VALUES(:idcoureur,:poste, :club, :idcompetition)');

					
			try
			{

				
					$stmt->bindValue(':nom',$nom1);
					$stmt->bindValue(':prenom',$prenom1);
					$stmt->bindValue(':sexe',$sexe1);	
					$stmt->bindValue(':naissance',$datenaiss1);
					$stmt->bindValue(':telephone', $numtele1);	
					$stmt->bindValue(':adresse', $adresse1);
					$stmt->bindValue(':codepostal', $codepostal1);	
					$stmt->bindValue(':email', $email1);	
					$stmt->execute();//enregistrement de la personne 


					$stmt2->execute();//recuperation de l'id de la personne 
					$id= $stmt2->fetch();//reponse sur l'id personne


					//pasage des valeurs dans la table coureur
					$stmt3->bindValue(':idcoureur',$id[0]);	
					$stmt3->bindValue(':poste','Capitaine');	
					$stmt3->bindValue(':club',$nomclub);	
					$stmt3->bindValue(':idcompetition',$course);	
					$stmt3->execute(); 



					//pour l'equipier

					$stmt->bindValue(':nom',$nom2);
					$stmt->bindValue(':prenom',$prenom2);
					$stmt->bindValue(':sexe',$sexe2);	
					$stmt->bindValue(':naissance',$datenaiss2);
					$stmt->bindValue(':telephone', $numtele2);	
					$stmt->bindValue(':adresse', $adresse2);
					$stmt->bindValue(':codepostal', $codepostal2);	
					$stmt->bindValue(':email', $email2);	
					$stmt->execute();//enregistrement de la personne 


					$stmt2->execute();//recuperation de l'id de la personne 
					$id= $stmt2->fetch();//reponse sur l'id personne


					//pasage des valeurs dans la table coureur
					$stmt3->bindValue(':idcoureur',$id[0]);	
					$stmt3->bindValue(':poste','Equipier');	
					$stmt3->bindValue(':club',$nomclub);	
					$stmt3->bindValue(':poste',$course);	
					$stmt3->execute(); 

					

				
					echo"<script type='text/javascript'> alert('Equipe Enregistré');

					</script>";

					//header('location:benevole_ou_coureur.php');
					



			}		
			catch(Exception $e)
			{

					echo $e->getMessage();
			}
			

		}
		else
		{
			echo " svp veuillez remplir0 tous les champs";
		}

?>

</body>
</html>