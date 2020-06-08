<!DOCTYPE html>
<html>
<head>
	<title>inscription benevole</title>
	<link rel="stylesheet" type="text/css" href="bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="style.css">

  <meta charset="UTF-8">
		
</head>
<body>

<?php

 include("connexion.php");

		if(isset($_POST["nom"]))
		{
			$nom=$_POST["nom"];
			$prenom=$_POST["prenom"];
			$datenaiss=$_POST["datenaiss"];
			
			$numtele=$_POST["numtel"];
			$adresse=$_POST["adresse"];
			$codepostal=$_POST["codepostal"];
			$email=$_POST["email"];
			$sexe=$_POST["sexe"];
			$permis=$_POST["permis"];
			$datepermis=$_POST["datepermis"];
			$conditions=$_POST["condition"];
			$course=$_POST["course"];

			echo " <br>";

			//requette d'insertio des donnees 

			//$conn= new PDO('mysql:host=127.0.0.1;dbname=nomdelabase', 'user', 'motdepasse');
			$stmt = $con->prepare('INSERT INTO personne(nom,prenom,sexe,naissance,telephone,adresse,codepostal
				,email)  VALUES (:nom, :prenom, :sexe, :naissance, :telephone, :adresse, :codepostal, :email)');

			$stmt2= $con->prepare('SELECT idpersonne FROM personne ORDER BY idpersonne DESC
									 LIMIT 1');


			$stmt3= $con->prepare('INSERT INTO dreamdev.benevole(idbenevole,permanent,idcompetition)
						VALUES(:idbenevole,:permanent,:idcompetition)');

						//pour les permis 
			$stmt4=$con->prepare('INSERT INTO dreamdev.permis(idpermis,numero,datedelivrance)
						VALUES (:idpermis,:numero,:datedelivrance)');

			$stmt5=$con->prepare('SELECT idbenevole FROM benevole ORDER BY idbenevole DESC
									 LIMIT 1');
		


			try
			{

				
					$stmt->bindValue(':nom',$nom);
					$stmt->bindValue(':prenom',$prenom);
					$stmt->bindValue(':sexe',$sexe);	
					$stmt->bindValue(':naissance',$datenaiss);
					$stmt->bindValue(':telephone', $numtele);	
					$stmt->bindValue(':adresse', $adresse);
					$stmt->bindValue(':codepostal', $codepostal);	
					$stmt->bindValue(':email', $email);	
					$stmt->execute();//enregistrement de la personne 


					$stmt2->execute();//recuperation de l'id de la personne 
					$id= $stmt2->fetch();//reponse sur l'id personne


					//pasage des valeurs dans la table benevole 
					$stmt3->bindValue(':idbenevole',$id[0]);	
					$stmt3->bindValue(':permanent','false');	
					$stmt3->bindValue(':idcompetition',$course);	
					$stmt3->execute(); 

					//recupêrer l'idbenevole
					$stmt5->execute();
					$idbenevole=$stmt5->fetch();

					//insertion de permis
					$stmt4->bindValue(':idpermis',$idbenevole[0]);
					$stmt4->bindValue(':numero',$numtele);
					$stmt4->bindValue(':datedelivrance',$datepermis);	
					$stmt4->execute();

					



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



<div class="class-group" id="cadre">
	<form name="form1" method="POST" action=''>
		
		<center><h2><b>AJOUT D'UN BENEVOLE</b></h2></center>
		
		<label for='nom'>Nom</label>
		 <input type="text" class='form-control' name="nom" value="berrin1">

		<label for='prenom1'>Prenom </label>
		<input type="text" class='form-control' name="prenom" value="pieere1">

		<label for='datenaiss'>Date de naissance</label> 
		<input type="text" class='form-control'name="datenaiss" value="14/10/2003">
		
		<label for='numtel' >Telephone</label> 
		<input type="number"class='form-control' name="numtel" value="1111155555">
		
		<label for='adresse1'>Adresse</label>
		 <input type="text" name="adresse" class='form-control' value="19 rue bonbon1">

		<label for='codepostal1'>Code postal</label>
		 <input type="number" name="codepostal" class='form-control' value="10">

		<label for='email1'>Email</label>
		 <input type='Email' name="email" class='form-control' value="zzz@gamiloooooo.com">
		 <br>
		<label for='sexe1' >Sexe</label>
		<input type="radio" name="sexe"  value='Homme'>Homme
		<input type="radio" name="sexe"  value='Femme'>Femme
		
		<br>

		<label for='numero'>Numero du permis de conduire</label>
		<input type="text"  class='form-control' name="permis" value="eeee111rrr11">

		
		<label for='datepermis1'>Date de delivrance du permis</label>
		 <input type="text"  class='form-control' name="datepermis" value="14/04/2014">

		 <br>

		  <label for="course"> Choix de la course</label>
			<input type='radio' name='course' value='1'>Mimi-bol d'air
				<input type='radio' name='course' value='2'>Bol d'air

			<br>
		 <input type='checkbox' name='condition'> Condition d'utilisation
		 <br>
		 <br>
		 <button type="submit" class='btn btn-primary form-control'>Enregistrer</button>
	</form>

</div>


</body>
<?php 
	
			
			
			echo" insertion de benevole reussie";

?>
	
</html>