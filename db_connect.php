<?php

//variable names must start with $
//string concat operator is .

$host=	"ada";
$dbase=  "s19_gallbr02";
$user=	"gallbr02";
$pass=	"gallbr02";

try{
	$db = new PDO("mysql:host=$host;dbname=$dbase", $user, $pass);
	//echo "hello";
}
catch(PDOException $e){
	die("Error connecting to mysql server: " . $e->getMessage());
}

?>
