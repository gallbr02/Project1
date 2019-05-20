<?php
include_once("db_connect.php");
include_once("hw4utils.php");

	//print_r($_POST);

	//$login = $_POST['login'];
	//$pass = $_POST['pass'];
	
	//echo"yarn";
	
	$result = checkUser($db, $login, $pass);
	
	//echo "hello";
	
	if($result == -1) {
		echo"The user does not exist in the table";	
	}
	else if($result == -2) {
		echo"The user is in unverified table";
	}
	else if($result == -3) {
		echo"Password does not match";	
	}else{
		echo "Successfully logged in";
	}

?>