<?php
include_once ("db_connect.php");
include_once ("hw4utils.php");

	//print_r($_POST);

	$login = $_POST['tfLogin'];

	$email = $_POST['tfEmail'];
	
	$result = registerUser($db,$_POST);
	
	if($result == false){
		echo '<P> could not create account </P>\n';
	}
	else {
		echo '<P> Registered user </P>';
		
	$result = mail($email, 'Please confirm account', "Please verify your email by clicking on the link 
	<a href='http://www.cs.gettysburg.edu/~gallbr02/verify.php?uid=$login'>Click here to verify your email.</a>");
		
		echo "Success!";
	}

?>