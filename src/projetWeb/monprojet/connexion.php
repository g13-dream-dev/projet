<?php

	
		try{
		/*$connect = pg_pconnect("host=localhost port=5432 dbname=dreamdev user=dreamdev password=dreamdev");*/

		$con = new PDO('pgsql:host=localhost ;port=5432;dbname=postgres', 'dreamdev', 'dreamdev');
		$con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		//$conn = Foo::Conecction();
		$con->exec('SET search_path TO dreamdev');

			
			//echo " <br> connection a la bd reussie <br>";
		}
		catch( Exception $e)
		{

			//echo " erreur lors de la connexion";
			echo " <br> la connection a la BD a échoué >>".$e->getMessage()."<br/>";




			
			die();
			
		}
		//$_SESSION["connect"]=$connect;


?>