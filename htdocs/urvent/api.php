<?php
require "DBConnector.php";

//sampleRequest: localhost/urvent/api.php?payload={"cmd":"getUserByID","csv":"false","param":"1","user":"usermail@mail.de","token" : "3536322425ffwsg"}
//{"cmd":"insertNewEvent","csv":"false","param":"","owner":"0","name":"nameHere","descr":"descrHere","location":"locationHere"}
//var_dump($_GET);
//var_dump($_REQUEST);

$command = json_decode($_REQUEST["payload"], $assoc=true);
$commandName = $command["cmd"];
$csv = $command["csv"];
$parameter = $command["param"];
//var_dump($command);

if($commandName == "getUserByID")
{
  if (validRequest($command) == true)
  {
    $user = json_encode(getUserByID($parameter));
    echo($user);
  }

}
elseif($commandName == "getUserByMail")
{
  //if (validRequest($command) == true)
  //{
    $user = json_encode(getUserByMail($parameter));
    echo($user);
  //}
}
elseif ($commandName == "login") //this one needs no validation
{
  $usermail = $command["um"];
  $password = $command["pw"];
  $result = json_encode(tryLogin($usermail, $password));
  echo($result);
}
elseif ($commandName == "getEventByID")
{
    $event = json_encode(getEventByID($parameter));
    echo($event);
}
elseif ($commandName == "getEventByUser")
{
  $events = json_encode(getEventByUser($parameter));
  echo($events);
}
elseif ($commandName == "getLocationByID")
{
  $location = json_encode(getLocationByID($parameter));
  echo($location);
}
elseif ($commandName == "getTicketByID")
{
  $ticket = json_encode(getTicketByID($parameter));
  echo($ticket);
}
elseif ($commandName == "getTicketByInvitationID")
{
  $ticket = json_encode(getTicketByInvitationID($parameter));
  echo($ticket);
}
elseif ($commandName == "insertNewUser")
{
  $name = $command["un"];
  $mail = $command["um"];
  $password = $command["pw"];
  $result = insertNewUser($name, $mail, $password);
  echo($result);
}
elseif ($commandName == "getLocationByUser")
{
  $locations = json_encode(getLocationByUser($parameter));
  echo($locations);
}
elseif ($commandName == "insertNewEvent")
{
  //owner,name,descr,location
  $owner = intval($command["owner"]);
  $name = $command["name"];
  $descr = $command["descr"];
  $location = intval($command["location"]);
  $result = insertNewEvent($owner, $name, $descr, $location); //TODO
  echo($result);
}
elseif ($commandName == "insertNewLocation")
{
  //$name, $descr, $coordinates, $owner
  $name = $command["name"];
  $descr = $command["descr"];
  $coordinates = $command["coordinates"];
  $owner = intval($command["owner"]);
  $result = insertNewLocation($name, $descr, $coordinates, $owner); //TODO
  echo($result);
}
elseif ($commandName == "insertNewInvitation")
{
  //$relatedEvent, $relatedTicket, $host, $guest
  $relatedEvent = $command["relatedEvent"];
  $host = $command["host"];
  $guest = $command["guest"];
  $result = insertNewInvitation($relatedEvent, $host, $guest);
  echo ($result);
}
elseif ($commandName == "getInvitationsByUserID")
{
  $invitations = json_encode(getInvitationsByUserID($parameter));
  echo($invitations);
}
elseif ($commandName == "acceptInvitation")
{
  $invitationID = $parameter;
  $event = $command["event"];
  $owner = $command["owner"];
  $result = json_encode(acceptInvitation($invitationID));
  $res = insertNewTicket($owner, $event, $invitationID);
  echo($result);
}

function validRequest($req)
{
  $usermail = $req["user"];
  $token = $req["token"];
  $dbRow = getUserByMail($usermail);
  $valid = false;
  if ($token == $dbRow["loginToken"])
  {
    $valid = true;
  }
  return $valid;
}
//print_r($commandName);

 ?>
