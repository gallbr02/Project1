
<?php
	include_once('db_connect.php');
phpinfo();
?>

<?php 

include_once('db_connect.php');
//phpinfo();

?>

<HTML>
<HEAD>
<TITLE>Teen Titans</TITLE>
</HEAD>

<BODY>
<H1>Hello World</H1>
<H2>Hello World</H2>
<H3>Hello World</H3>
<H4>Hello World</H4>
<H5>Hello World</H5>
<H6>Hello World</H6>

<TABLE border="1" cellspacing="0" cellpadding="5">
<CAPTION>Teen Titans</CAPTION>

<TR>
<TH>ID</TH>
<TH>Name</TH>
</TR>

<?php

$qStr = "SELECT * FROM T1;";

$t1Data = $db->query($qStr);

if ($t1Data != FALSE) {

	//printf("T1 has %d rows and %d columns\n", 
	//       $t1Data->rowCount(), $t1Data->columnCount()); 

	// for each row from the result table
	while ($row = $t1Data->fetch()) {
		$id   = $row['id'];	
		$name = $row['name'];

		$rowStr = "<TR>\n"
		        . "<TD>$id</TD>\n"
				. "<TD>$name</TD>\n"
				. "</TR>\n";

		echo $rowStr;
	}
}

?>

</TABLE>

<HR />
<H2>Add a New Titan</H2>

<FORM name="fmAdd" method="POST" action="add_titan_f.php">
<TABLE>

<TR>
<TD>ID</TD>     
<TD><INPUT type="password" name="id"     placeholder="enter id" /> </TD>
</TR>

<TR>
<TD>NAME </TD>  
<TD><INPUT type="text" name="name"   placeholder="enter name" /> </TD>
</TR>

<TR>
<TD>PLANET </TD>
<TD><INPUT type="text" name="planet" placeholder="enter planet" /> </TD>
</TR>

<TR>
<TD>POWER </TD> 
<TD><INPUT type="text" name="power"  placeholder="enter power" /> </TD>
</TR>

<BR />

<TR>
<TD><INPUT type="submit" value="Add a new titan" /></TD>
<TD><INPUT type="reset"  value="Clear the form" /></TD>
</TR>

</TABLE>
</FORM>

// create a new form to ask user to enter an id
//	and delete the titan associated with that id from T1 and T2

</BODY>
</HTML>