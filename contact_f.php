<?php
// php script to process contactUs.html form

$name    = $_POST['tfName'];
$email   = $_POST['tfEmail'];
$content = $_POST['taComment'];

$subject = 'comment from $name';
$header  = 'FROM: $email\r\n';

$toEmail = 'skim@gettysburg.edu';

$result = mail($toEmail, 
               $subject,
               $content,
               $header);

?>

<html>
<head><title>Thank you</title></head>
<body>
<h2>Thank you for contacting us</h2>

Your name :  <?php echo $name; ?><br />
Your email:  <?php echo $email; ?><br />
<br />

Comment: <BR />

<textarea rows='6' cols='60' READONLY>
<?php echo $content; ?>
</textarea>>

</body>
</html>