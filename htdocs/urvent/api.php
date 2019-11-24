<?php
require "DBConnector.php";

//sampleRequest: localhost/urvent/api.php?payload={"cmd":"getUserByID","csv":"false","param":"1"}
//var_dump($_GET);
//var_dump($_REQUEST);

$command = json_decode($_REQUEST["payload"], $assoc=true);
$commandName = $command["cmd"];
$csv = $command["csv"];
$parameter = $command["param"];

if($commandName == "getUserByID")
{
  $user = json_encode(getUserByID($parameter));
  echo($user);
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

//print_r($commandName);

 ?>
