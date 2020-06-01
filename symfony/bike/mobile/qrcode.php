<?php

 
include('libs/phpqrcode/qrlib.php'); 




	$tempDir = '../web/uploads/images/qrproduit/'; 
	$data = $_GET["data"];

	$filename = md5(uniqid());

	$codeContents = $data; 
	QRcode::png($codeContents, $tempDir.''.$filename.'.png', QR_ECLEVEL_L, 5);
echo $filename.'.png';


?>
