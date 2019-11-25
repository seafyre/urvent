<?php
require "DBConnector.php";

//sampleRequest: localhost/urvent/api.php?payload={"cmd":"getUserByID","csv":"false","param":"1","user":"usermail@mail.de","token" : "3536322425ffwsg"}
//var_dump($_GET);
//var_dump($_REQUEST);

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
  if (validRequest($command) == true)
  {
    $user = json_encode(getUserByMail($parameter));
    echo($user);
  }
}
elseif ($commandName == "login")
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
elseif ($commandName = "insertNewUser")
{
  $name = $command["un"];
  $mail = $command["um"];
  $password = $command["pw"];
  $result = insertNewUser($name, $mail, $password);
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
