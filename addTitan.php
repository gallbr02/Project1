<?php

include_once("db_connect.php");

// $_POST: special hash table variable for
//         all of user's input
print_r($_POST);

// extract input data
$id     = $_POST['id'];
$name   = $_POST['name'];
$planet = $_POST['planet'];
$power  = $_POST['power'];

// T1: id, name
$str1 = "INSERT INTO T1 VALUE($id, '$name');"   ;

// T2: id, planet, power
$str2 = "INSERT INTO T2 VALUE($id, '$planet', '$power');" ;

//echo "<P>DEBUG: $str1     $str2</P>\n";

$result1 = $db->query($str1);
$result2 = $db->query($str2);

if ($result1 == FALSE || $result2 == FALSE) {
	echo "<P>Error inserting $name into Titan tables</P>\n";
}
else {
	echo "<P>Successfully inserted $name into Titan tables</P>\n";
}

?>
