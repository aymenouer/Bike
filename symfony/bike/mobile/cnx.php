<?php
 $dbHost     = "localhost";
$dbUsername = "root";
$dbPassword = "";
$dbName     = "bike";

// Create database connection
$db = new mysqli($dbHost, $dbUsername, $dbPassword, $dbName);
        
        //get product rows
        $list = $db->query("SELECT * FROM user ");

$nom=$_GET["name"];
$password=$_GET["password"];
$x= "-1";



foreach ($list as $row ) {
	$hash=$row['password'];

if($nom==$row['username'] && password_verify($password, $hash) )
{

if($row['roles']=="a:0:{}")
{
$x=$row['id'];
}
else
{
$x=$row['id'];;	
}


}


}
echo $x;


?>
