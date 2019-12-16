<?php
require "DBConnector.php";

//das hier bekommt der Client (unsere java anwendung), wird dann mit org.simpleJSON zu einem Java Objekt weiterverarbeitet (siehe klassendiagramm)
//print_r(json_encode(getUserByID(1)));
//print_r(json_encode(getEventByID(1)));
//print_r(json_encode(getInvitationByID(1)));
//print_r(json_encode(getLocationByID(1)));
//print_r(json_encode(getTicketByID(1)));
//setUserByID("steven", "haha@web.de", "123ll");
//updateUserByID(8, "lol", "hahaa@web.de", "lala")
//$arr = json_encode(tryLogin("teastmail@test.tst","efa"));
$arr = insertNewEvent(1, "TestEvt", "Lorem ipsum", 1);
print_r($arr);
 ?>
