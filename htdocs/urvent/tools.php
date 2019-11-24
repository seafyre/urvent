<?php

function getReplyArray($success, $function, $parameter)
{
  $arr = array
  (
    "succ" => $success,
    "func" => $function,
    "param" => $parameter
  );

  return $arr;
}

 ?>
