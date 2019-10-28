<?php
$command = json_decode($_GET["payload"],$assoc=true);
$commandName = $command["cmd"];
print_r($commandName);

 ?>
