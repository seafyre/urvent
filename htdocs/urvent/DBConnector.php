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

function setUserByID($name, $mail, $password)
{
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO user VALUES (DEFAULT, '".$name."', '".$mail."', '".$password."', DEFAULT)");
}

function setTicketByID($owner, $event, $name, $qrdata)
{
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO ticket VALUES (DEFAULT, '".$owner."', '".$event."', '".$name."', '".$qrdata."')"); //qrdata??
}

function setLocationByID($name, $descr, $coordinates, $owner)
{
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO location VALUES (DEFAULT, '".$name."', '".$descr."', '".$coordinates."', '".$owner."')");
}

function setEventByID($owner, $name, $descr, $location)
{
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO event VALUES (DEFAULT, '".$owner."', '".$name."', '".$descr."', '".$location."')");
}

function setInvitationByID($relatedTicket, $host, $guest)
{
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO invitation VALUES (DEFAULT, '".$relatedTicket."', '".$host."', '".$guest."')");
}

function updateUserByID($ID, $name, $email, $password)
{
    $connection = openConnection();
    $update = $connection->query("UPDATE user SET name = '".$name."', mail = '".$email."', password = '".$password."' WHERE ID ='".$ID."'");
}

function updateInvitationByID($ID, $relatedTicket, $host, $guest)
{
    $connection = openConnection();
    $update = $connection->query("UPDATE invitation SET relatedTicket = '".$relatedTicket."', host = '".$host."', guest = '".$guest."' WHERE ID ='".$ID."'");
}

function updateLocationByID($ID, $name, $descr, $coordinates, $owner)
{
    $connection = openConnection();
    $update = $connection->query("UPDATE location SET name = '".$name."', descr = '".$descr."', coordinates = '".$coordinates."', owner = '".$owner."' WHERE ID ='".$ID."'");
}

function updateEventByID($ID, $owner, $name , $descr, $location)
{
    $connection = openConnection();
    $update = $connection->query("UPDATE event SET owner ='".$owner."', name = '".$name."', descr = '".$descr."', location = '".$location."' WHERE ID ='".$ID."'");
}

function updateTicketByID($ID, $owner, $event, $name, $qrdata)
{
    $connection = openConnection();
    $update = $connection->query("UPDATE ticket SET owner = '".$owner."', event = '".$event."', name = '".$name."', qrdata = '".$qrdata."' WHERE ID = '".$ID."'");
}

?>
