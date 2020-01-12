<?php
require "DBConnector.php";

$command = json_decode($_REQUEST["payload"], $assoc=true);
$commandName = $command["cmd"];
$csv = $command["csv"];
$parameter = $command["param"];

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
elseif ($commandName == "insertNewUser")
{
  $name = $command["un"];
  $mail = $command["um"];
  $password = $command["pw"];
  $result = insertNewUser($name, $mail, $password);
  echo($result);
}
elseif ($commandName == "editUser")
{
  $ID = $parameter;
  $name = $command["name"];
  $descr = $command["descr"];
  $result = json_encode(editUser($ID, $name, $descr));
  echo($result);
}
elseif ($commandName == "getLocationByUser")
{
  $locations = json_encode(getLocationByUser($parameter));
  echo($locations);
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
elseif($commandName == "deleteEventByID")
{
  if (validRequest($command) == true)
  {
    $delete = json_encode(deleteEventByID($parameter));
    echo($delete);
  }
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
