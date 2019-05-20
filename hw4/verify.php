<?php

include_once("db_connect.php");
include_once("hw4utils.php");

	print_r($_GET);
	
   $login = $_GET['uid'];
 //  $pass = $_GET['pass'];
 
   verifyEmail($db, $login);
 
	echo "<P>Successfully Verified </P>\n";
?>