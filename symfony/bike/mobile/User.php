<?php
 $dbHost     = "localhost";
$dbUsername = "root";
$dbPassword = "";
$dbName     = "bike";

// Create database connection
$db = new mysqli($dbHost, $dbUsername, $dbPassword, $dbName);
        $nom="aymen";

        //get product rows
        $list = $db->query("SELECT * FROM user   ");

$id=0;


foreach ($list as $row ) {
	


if($row['username']== $nom )
{
	$id =$row['id'];
}





}
echo $id;

?>
