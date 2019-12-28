<?php
require "tools.php";
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
		$result = $connection->query("SELECT * FROM user WHERE ID = ".$ID, MYSQLI_USE_RESULT);
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getUserByMail($mail)
{
	if($mail != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM user WHERE mail = \"".$mail."\"", MYSQLI_USE_RESULT);
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}
}

function getEventByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM event WHERE ID = ".$ID, MYSQLI_USE_RESULT);
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getEventByUser($userID)
{
	if($userID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM event WHERE owner = ".$userID, MYSQLI_USE_RESULT);
		$results = array();
		foreach ($result as $n)
		{
			array_push($results, json_encode($n));
		}
		return $results;
	}
}

function getTicketByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM ticket WHERE ID = ".$ID, MYSQLI_USE_RESULT);
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getLocationByID($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM location WHERE ID = ".$ID, MYSQLI_USE_RESULT);
		$data = $result->fetch_array(MYSQLI_ASSOC);
		return $data;
	}

}

function getLocationByUser($userID)
{
	if($userID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM location WHERE owner = ".$userID, MYSQLI_USE_RESULT);
		$results = array();
		foreach ($result as $n)
		{
			array_push($results, json_encode($n));
		}
		return $results;
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

function insertNewInvitation($relatedEvent, $relatedTicket, $host, $guest)
{
	$reply = json_encode(getReplyArray(false, "insertNewInvitation", ""));
	$connection = openConnection();
	$entry = $connection->query("INSERT INTO invitation (relatedEvent,relatedTicket,host,guest) VALUES ($relatedEvent,$relatedTicket,$host,$guest)");
	if($entry == true)
	{
		$reply = json_encode(getReplyArray(true, "insertNewInvitation", ""));
	}
	return $reply;
}

function insertNewUser($name, $mail, $password)
{
		$reply = json_encode(getReplyArray(false, "insertNewUser", ""));
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO user (name,mail,password) VALUES (\"$name\",\"$mail\",\"$password\")");
		if($entry == true)
		{
			$reply = json_encode(getReplyArray(true, "insertNewUser", ""));
		}
		return $reply;
}

function setTicketByID($owner, $event, $name, $qrdata)
{
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO ticket VALUES (DEFAULT, '".$owner."', '".$event."', '".$name."', '".$qrdata."')"); //qrdata??
}

function insertNewLocation($name, $descr, $coordinates, $owner)
{
		$reply = json_encode(getReplyArray(false, "insertNewLocation", ""));
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO location (name,descr,coordinates,owner) VALUES (\"$name\",\"$descr\",\"$coordinates\",$owner)");
		if ($entry == true)
		{
			$reply = json_encode(getReplyArray(true, "insertNewLocation", ""));
		}
		return $reply;
}

function insertNewEvent($owner, $name, $descr, $location)
{
		$reply = json_encode(getReplyArray(false, "insertNewEvent", ""));
    $connection = openConnection();
    $entry = $connection->query("INSERT INTO event (owner,name,descr,location) VALUES ($owner,\"$name\",\"$descr\",$location)");
		if ($entry == true)
		{
			$reply = json_encode(getReplyArray(true, "insertNewEvent", ""));
		}
		return $reply;
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

function tryLogin($usermail, $password)
{
	$creds = getUserByMail($usermail);
	$reply;

	if($creds["password"] == $password)
	{
		$token = createLoginToken($usermail);
		$reply = getReplyArray(true, "tryLogin", $token);
	}
	else
	{
		$reply = getReplyArray(false, "tryLogin", "");
	}

	return $reply;
}

function tryLoginWithToken($usermail, $token)
{

}

function storeLoginToken($ID, $token)
{
	$connection = openConnection();
	$result = $connection->query("	UPDATE user	SET LoginToken = \"".$token."\" WHERE ID = ".$ID.";");
}

function createLoginToken($usermail)
{
	$token = bin2hex(openssl_random_pseudo_bytes(32));

	$user = getUserByMail($usermail);
	$userID = $user["ID"];
	storeLoginToken($userID, $token);

	return $token;
}


?>
