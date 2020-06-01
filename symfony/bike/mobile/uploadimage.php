<?php

$allowedExts = array("gif", "jpeg", "jpg", "png");
$temp = explode(".", $_FILES["file"]["name"]);
$extension = end($temp);

if ((($_FILES["file"]["type"] == "image/gif") || ($_FILES["file"]["type"] == "image/jpeg") || ($_FILES["file"]["type"] == "image/jpg") || ($_FILES["file"]["type"] == "image/pjpeg") || ($_FILES["file"]["type"] == "image/x-png") || ($_FILES["file"]["type"] == "image/png"))	) {
    if ($_FILES["file"]["error"] > 0) {
        $named_array = array("Response" => array(array("Status" => "error")));
        //echo json_encode($named_array);
    } else {
       copy($_FILES["file"]["tmp_name"], "C:/xampp/htdocs/bike/web/Upload/". $_FILES["file"]["name"]);
        move_uploaded_file($_FILES["file"]["tmp_name"], "C:/Users/aymen/Desktop/Bike_Mobile/src/Image/" . $_FILES["file"]["name"]);
        $Path = $_FILES["file"]["name"];
        $named_array = array("Response" => array(array("Status" => "ok")));
        $_GET['image']=$Path;


      //  echo json_encode($named_array);
    }
} else {
    $named_array = array("Response" => array(array("Status" => "invalid")));
    //echo json_encode($named_array);
}



?>