<?php

/**
*Get a connection to the Database
*@return connection
*/
function openConnection()
{
	//Datenbankserver
	$dbhost = "localhost";

	//Datenbanknutzer
	$dbuser = "root";

	//Datenbankpasswort
	$dbpw = "";

	//Datenbankname
	$db = "urvent";

	$connection = new mysqli($dbhost, $dbuser, $dbpw, $db);
	return $connection;
}

function getUserByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM user WHERE ID = ".$ID, MYSQLI_USE_RESULT); //TODO remove password
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getEventByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM event WHERE ID = ".$ID, MYSQLI_USE_RESULT); //TODO remove password
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getTicketByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM ticket WHERE ID = ".$ID, MYSQLI_USE_RESULT); //TODO remove password
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getLocationByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM location WHERE ID = ".$ID, MYSQLI_USE_RESULT); //TODO remove password
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getInvitationByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM invitation WHERE ID = ".$ID, MYSQLI_USE_RESULT); //TODO remove password
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}


?>
