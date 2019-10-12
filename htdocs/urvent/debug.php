<?php
require "DBConnector.php";

//das hier bekommt der Client (unsere java anwendung), wird dann mit org.simpleJSON zu einem Java Objekt weiterverarbeitet (siehe klassendiagramm)
print_r(json_encode(getUserByID(1)));
 ?>
