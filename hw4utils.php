<?php
// php script to process hmwk4.html form
include_once("db_connect.php");

function registerUser($db, $input) {
	
	$login = $input['tfLogin'];
	$pass = $input['tfPass'];
	$bdate = $input['tfBdate'];
	$email = $input['tfEmail'];	
	
	$qstr = "SELECT login FROM USER WHERE login = '$login'";

	//echo $qstr;	
	
	$exists = $db->query($qstr);
	if($exists->rowCount() > 0) {
	//	echo "hello2";
		return false;
	}
	else{
		//echo "hello44";
	addUser($db, $login, $pass, $bdate, $email);
	
	//mail($email, 'Email Verification', "Please verify your email by clicking on the link 
//	<a href='http://www.cs.gettysburg.edu/~gallbr02/cs360/hw4/verify.php?uid= $login'>Click here to verify your email.</a>");	

	}
		return true;
	
}

function addUser($db, $login, $pass, $bdate, $email) {
	
	//echo "hello";
	$pass = md5($pass);
	
	$str1 = "INSERT INTO USER VALUE('$login', '$pass', '$bdate', '$email')";
	$str2 = "INSERT INTO unverified VALUE('$login')";
	
	//echo $str1;
	//echo $str2;
	
	$result1 = $db->query($str1);
	$result2 = $db->query($str2);
	
	if ($result1 == FALSE || $result2 == FALSE) {
		echo "<P>Error inserting $login into table</P>\n";
	}
	else {
		echo "<P>Successfully inserted $login into table</P>\n";
	}
}


function checkUser($db, $login, $pass) {
	
	$pass = md5($pass);
	
	
	$result = $db->query("SELECT login FROM USER WHERE login = '$login'");
	$result1 = $db->query("SELECT ulogin FROM unverified WHERE ulogin = '$login'");
	$result2 = $db->query("SELECT login FROM USER WHERE login = '$login' AND passHash = '$pass' ");
	
	
	if($result->rowCount() == 0){
		return -1;
	}
	else if($result1->rowCount() >0) {
		return -2;
	}
	else if($result2->rowCount() == 0) {
		return -3;
	}else {
		return 1;
	}
			
}


function verifyEmail($db, $userlogin) {
	$qstr = "DELETE FROM unverified WHERE ulogin = '$userlogin'";
	$table = $db->query($qstr);	
	return true;
	}
	

?>