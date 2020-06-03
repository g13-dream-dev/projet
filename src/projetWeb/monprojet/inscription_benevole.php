<!DOCTYPE html>
<html>
<head>
	<title>inscription coureur</title>
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

			echo " hello";
			echo $nom;
			echo " <br>";

			//requette d'insertio des donnees 

			//$conn= new PDO('mysql:host=127.0.0.1;dbname=nomdelabase', 'user', 'motdepasse');
			$stmt = $con->prepare('INSERT INTO personne(nom,prenom,sexe,naissance,telephone,adresse,codepostal
				,email)  VALUES (:nom, :prenom, :sexe, :naissance, :telephone, :adresse, :codepostal, :email)');

			$stmt2= $con->prepare('SELECT idpersonne FROM personne ORDER BY idpersonne DESC
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

					$stmt->execute();
					$stmt2->execute();
					$id= $stmt2->fetch();

					$stmt3=$con->prepare('INSERT INTO benevole(idbenevole,permanent)
										VALUES(:idbenevole,:permanent)');

					$stmt3->bindValue(':idbenevole','$id[0]');	
					$stmt3->bindValue(':permanent', 'false');	
					echo" insertion de benevole reussie";
					
			}
			catch(Exception $e)
			{
					echo $e->getMessage();
			}
			

		}
		else
		{
			echo " svp veuillez remplir tous les champs";
		}

?>
<form name="form1" method="POST" action=''>
	Nom <input type="text" name="nom"><br>
	Prenom <input type="text" name="prenom"><br>
	Date de naissance 
	<input type="text" name="datenaiss">
	<br>
	
	Telephone 
	<input type="number" name="numtel">
	<br>
	Adresse <input type="text" name="adresse"><br>
	Code postal <input type="text" name="codepostal"><br>
	Email <input type="Email" name="email"><br>
	Sexe 
	<input type="radio" name="sexe" value='Homme'>Homme
	<input type="radio" name="sexe" value='Femme'>Femme
	<br>
	Permis de conduire 
	<br>
	Numero
	<input type="text" name="permis">
	<br>
	d=Date de delivrance <input type="text" name="datepermis">
	<br>

	<input type="checkbox" name="condition" value='yes'>
	Valier les conditions de la course.
	<br> 



	<button type="submit">VALIDER</button>

</form>

<table border="1">

<tr>
	<td>id personne </td>
	<td>nom </td>
	<td>prenom </td>
	<td>Sexe </td>
	<td>Date de naissance</td>
	<td>Telephone </td>
	<td>Adresse </td>
	<td>Code postale  </td>
	<td> E-mail </td>
	<td>Code postale  </td>
	<td> E-mail </td>
</tr>
</tr>


<?php

		$stmt = $con->prepare("SELECT * FROM personne INNER JOIN benevole ON personne.idpersonne=benevole.idbenevole");

		if ($stmt->execute()) {
		  while ($row = $stmt->fetch()) {
		    echo "<tr>";
		    echo "<td> $row[0]</td>";
		    echo " <td>$row[1] </td>";
		     echo " <td>$row[2] </td>";
		      echo " <td>$row[3] </td>";
		       echo " <td>$row[4] </td>";
		        echo " <td>$row[5] </td>";
		         echo " <td>$row[6] </td>";
		           echo " <td>$row[7] </td>";
		            echo " <td>$row[8] </td>";
		            echo " <td>$row[9] </td>";
		            echo " <td>$row[10] </td>";
		     }
		    }

?>


</table>



</body>
	
</html>