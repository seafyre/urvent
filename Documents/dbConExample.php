<?php

//Verbindung zur Datenbank herstellen
function openConnection()
{
	//Datenbankserver
	$dbhost = "localhost";

	//Datenbanknutzer
	$dbuser = "root";

	//Datenbankpasswort
	$dbpw = "";

	//Datenbankname
	$db = "insert db name here";

	$connection = new mysqli($dbhost, $dbuser, $dbpw, $db);
	return $connection;
}

//Verbindung zur Datenbank beenden
function closeConnection($connection)
{
	$connection -> close();
}

//User Functions
function getUser($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM user WHERE ID = ".$ID, MYSQLI_USE_RESULT); //TODO don't select password

		//Funktionsergebnis alsa ssoziatives Array zurück geben
		$data = $result->fetch_array(MYSQLI_ASSOC);
		#print_r($data);

		//$row_cnt = count($data);
		$checkZero = $data["ID"]; //replace column name
		if($checkZero != NULL)
		{
			return $data;
		}
		else if($checkZero == NULL)
		{
			echo "nothing found";
			$data = false;
			return $data;
		}
		#print_r($result->num_rows);
	}

}

function getUserByMail($mail)
{
	if($mail != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM user WHERE Mail = \"".$mail."\"", MYSQLI_USE_RESULT); //replace table name

		//Funktionsergebnis alsa ssoziatives Array zurück geben
		$data = $result->fetch_array(MYSQLI_ASSOC);
		#print_r($data);

		//$row_cnt = count($data);
		$checkZero = $data["ID"]; //replace column name
		if($checkZero != NULL)
		{
			return $data;
		}
		else if($checkZero == NULL)
		{
			echo "nothing found";
			$data = false;
			return $data;
		}
		#print_r($result->num_rows);
	}

}

function createUser($name, $mail, $password)
{
	$connection = openConnection();
	$result = $connection->query("	INSERT INTO user (Name, Mail, Password)
									VALUES (\"".$name."\", \"".$mail."\",\"".$password."\")");
	return $result;
}

function storeLoginToken($ID, $token)
{
	$connection = openConnection();
	$result = $connection->query("	UPDATE user
									SET LoginToken = \"".$token."\"
									WHERE ID = ".$ID.";");
}

function getUserChats($ID)
{
	$user = getUser($ID);
	$UserChatIDs = $user["Chats"];
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM chat WHERE ID IN (".$UserChatIDs.");", MYSQLI_USE_RESULT);

		$data = array();
		while ($row = $result->fetch_array(MYSQLI_ASSOC))
		{
			array_push($data, $row);
		}

		$checkZero = sizeof($data);
		if($checkZero != 0)
		{
			return $data;
		}
		else if($checkZero == 0)
		{
			//echo "nothing found<br>";
			$data = false;
			return $data;
		}
	}
}

//Message Functions
function getMessage($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM message WHERE ID = ".$ID, MYSQLI_USE_RESULT); //replace table name, replace column name

		//Funktionsergebnis alsa ssoziatives Array zurück geben
		$data = $result->fetch_array(MYSQLI_ASSOC);
		#print_r($data);

		//$row_cnt = count($data);
		$checkZero = $data["ID"]; //replace column name
		if($checkZero != NULL)
		{
			return $data;
		}
		else if($checkZero == NULL)
		{
			echo "nothing found<br>";
			$data = false;
			return $data;
		}
		#print_r($result->num_rows);
	}
}

function storeMessage($Content, $Sender, $Recipient, $Chat, $Ack)
{
	$connection = openConnection();
	$result = $connection->query("	INSERT INTO message (Content, Sender, Recipient, Chat, Ack)
									VALUES (\"".$Content."\", \"".$Sender."\",\"".$Recipient."\",\"".$Chat."\",\"".$Ack."\")");
	return true;

}

//Chat Functions
function getChat($ID)
{
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM chat WHERE ID = ".$ID, MYSQLI_USE_RESULT); //replace table name, replace column name

		//Funktionsergebnis alsa ssoziatives Array zurück geben
		$data = $result->fetch_array(MYSQLI_ASSOC);
		#print_r($data);

		//$row_cnt = count($data);
		$checkZero = $data["ID"]; //replace column name
		if($checkZero != NULL)
		{
			return $data;
		}
		else if($checkZero == NULL)
		{
			echo "nothing found<br>";
			$data = false;
			return $data;
		}
		#print_r($result->num_rows);
	}
}

function storeChat($Participants, $Name = "Chat")
{
	$connection = openConnection();
	$participantIDs = explode(",", $Participants);
	$newIDresult = $connection->query("	SELECT AUTO_INCREMENT
									FROM information_schema.TABLES
									WHERE TABLE_SCHEMA = \"whatsappcloneproj\"
									AND TABLE_NAME = \"chat\" ");
	$newID = $newIDresult->fetch_array(MYSQLI_ASSOC)["AUTO_INCREMENT"];
	//var_dump($newIDresult);

	$result = $connection->query("	INSERT INTO chat (Participants, Name)
									VALUES (\"".$Participants."\", \"".$Name."\")");

	for ($n=0; $n < count($participantIDs); $n++)
	{
		//$user = getUser($participantIDs[$n]);
		//$userID = $user["ID"];
		//$userChats = $user["Chats"];
		//var_dump($newValue);
		//$newValue = $userChats.",".$newID;
		addChatToUser($participantIDs[$n], $newID);
		//updateUserChats($userID, $newValue);
	}
	return true;
}

function addChatParticipant($chatID, $userID)
{
	$connection = openConnection();
	$chat = getChat($chatID);
	$oldParticipants = $chat["Participants"];
	$newParticipants = "";
	if($oldParticipants != "")
	{
		$newParticipants = $oldParticipants.",".$userID;
	}
	else if ($oldParticipants == "")
	{
		$newParticipants = $userID;
	}
	rtrim($newParticipants,",");
	var_dump($newParticipants);
	updateChatParticipants($chatID, $newParticipants);
	return true;
}

//777
function addChatToUser($userID, $chatID)
{
	$user = getUser($userID);
	$oldChats = $user["Chats"];
	$newChats = "";
	if($oldChats != "")
	{
		$newChats = $oldChats.",".$chatID;
	}
	else if ($oldChats == "")
	{
		$newChats = $chatID;
	}
	rtrim($newChats,",");
	updateUserChats($userID, $newChats);
}

function updateUserChats($ID, $NewValue)
{
	$connection = openConnection();
	$result = $connection->query("	UPDATE user
									SET Chats = \"".$NewValue."\"
									WHERE ID = ".$ID.";");
}

function updateChatParticipants($ID, $NewValue)
{
	$connection = openConnection();
	$result = $connection->query("	UPDATE chat
									SET Participants = \"".$NewValue."\"
									WHERE ID = ".$ID.";");
}

function getChatMessages($ID)
{
	$chat = getChat($ID);
	$chatID = $chat["ID"];
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM message WHERE Chat =".$ID.";", MYSQLI_USE_RESULT);

		$data = array();
		$dataCount = sizeof($data);
		while ($row = $result->fetch_array(MYSQLI_ASSOC))
		{
			array_push($data, $row);
		}

		$checkZero = sizeof($data);
		if($checkZero != 0)
		{
			return $data;
		}
		else if($checkZero == 0)
		{
			echo "nothing found<br>";
			$data = false;
			return $data;
		}
	}
}

function getChatUsers($ID)
{
	$chat = getChat($ID);
	$chatUsrIDs = $chat["Participants"];
	if($ID != NULL)
	{
		$connection = openConnection();
		$result = $connection->query("SELECT * FROM user WHERE ID IN (".$chatUsrIDs.");", MYSQLI_USE_RESULT);

		$data = array();
		while ($row = $result->fetch_array(MYSQLI_ASSOC))
		{
			array_push($data, $row);
		}

		$checkZero = sizeof($data);
		if($checkZero != 0)
		{
			return $data;
		}
		else if($checkZero == 0)
		{
			echo "nothing found<br>";
			$data = false;
			return $data;
		}
	}
}



?>
