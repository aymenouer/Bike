<?php

namespace AbonnementBundle\Controller;

use AbonnementBundle\Entity\abonnement;
use AbonnementBundle\Form\abonnementType;
use BG\BarcodeBundle\Util\Base2DBarcode as matrixCode;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use ProduitBundle\Entity\produit;
use ProduitBundle\Form\produitType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Validator\Constraints\Image;

class abonnementController extends Controller
{
    public function ListAbonnementsAction(Request $request)
    {

        $ID_P=null;
        $produit = new produit();
        $form=$this->createForm(produitType::class,$produit);
        $form->remove("image");
        $form=$form->handleRequest($request);
        $db = $this->getDoctrine()->getManager();
        $type=$request->get('type');

        $listcategorie = $db->getRepository('CategorieBundle:categorie')->findBy(['type' => 'Abonnement']);
        $listsite = $db->getRepository('SiteBundle:site')->findAll();

        if($form->isValid() && $form->isSubmitted() ) {

            $contenu = 'Libelle : '.$produit->getLibelle().'<br>  Description : '. $produit->getDescription().' <br>  prix :'. (string) $produit->getPrix().' DT <br> Categorie : '. (string)$request->get('categorie').' <br> Site : '. (string)$request->get('site').'<br> Merci pour votre confiance &#128525;';

            $myBarcode = new matrixCode();
            $myBarcode->savePath = $this->getParameter('image_directory')."/";

             $myBarcode->getBarcodePNGPath($contenu, 'DATAMATRIX',10, 10,null,$produit->getLibelle());



                $code=md5(uniqid());
$new_name_image = $this->getParameter('image_directory')."/qrproduit/".$code .".png";

$image=$code .".png";
            rename($this->getParameter('image_directory')."/".$produit->getLibelle().".png",$new_name_image);

            $em = $this->getDoctrine()->getManager();
    $produit->setImage($image);
            $produit->setLibC((string)$request->get('categorie'));
            $produit->setLibS((string)$request->get('site'));
            $em->persist($produit);

            $em->flush();
            $abonnement = new abonnement();

            $emq = $this->getDoctrine()->getRepository(produit::class);
            $ID_P=$emq->return_produit_id($produit);
            $abonnement->setIdP((int)$ID_P['ID_P']);
            $abonnement->setQuantite((int)$request->get('Quantite'));
            $em->persist($abonnement);

            $em->flush();



            return $this->redirect($request->getUri());
        }
        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_abonment();
        // mail admin selon quantite

        $user=$this->container->get('security.token_storage')->getToken()->getUser();


        for($i=0;$i<sizeof($list);$i++ )
        {


        if ((int)$list[$i]['quantite']==5)
        {
           $message = \Swift_Message::newInstance()
                ->setSubject('Bike Regret')
                ->setFrom('bike.pidev@gmail.com')
                ->setTo($user->getEmail())
                ->setBody(
                    $this->renderView(
                    // app/Resources/views/Emails/registration.html.twig
                        'Emails/mail_admin_quantite.html.twig',
                        array(

                            'nom' =>  $user->getLastName() . " ". $user->getFirstName(),
                            'name' =>  $list[$i]['Libelle'],


                        )


                    ),
                    'text/html'
                );
            $this->get('mailer')->send($message);









        }



        }


//-------------------------------------------


        return $this->render('@Abonnement/abonnement/list_abonnements.html.twig', array('form'=>$form->createView(),'list'=>$list,'list_categorie'=>$listcategorie,'list_site'=>$listsite));
    }

    public function Update_AbonnementAction($ID_A,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());
        $abo->setQuantite((int)$request->get('Quantite'));
        $produit->setLibelle($request->get('Libelle'));
        $produit->setDescription($request->get('description'));
        $produit->setPrix((float)$request->get('prix'));
        $produit->setLibC($request->get('categorie'));
        $produit->setLibS($request->get('site'));
        unlink($this->getParameter('image_directory')."/qrproduit/".$produit->getImage()) ;
        $contenu = 'Libelle : '.$produit->getLibelle().'<br>  Description : '. $produit->getDescription().' <br>  prix :'. (string) $produit->getPrix().' DT <br> Categorie : '. (string)$request->get('categorie').' <br> Site : '. (string)$request->get('site').'<br> Merci pour votre confiance &#128525;';

        $myBarcode = new matrixCode();
        $myBarcode->savePath = $this->getParameter('image_directory')."/";

        $myBarcode->getBarcodePNGPath($contenu, 'DATAMATRIX',10, 10,null,$produit->getLibelle());



        $code=md5(uniqid());
        $new_name_image = $this->getParameter('image_directory')."/qrproduit/".$code .".png";

        $image=$code .".png";
        rename($this->getParameter('image_directory')."/".$produit->getLibelle().".png",$new_name_image);

        $em = $this->getDoctrine()->getManager();
        $produit->setImage($image);
        $em->persist($produit);
        $em->persist($abo);
        $em->flush();

        return $this->redirectToRoute('_list_abonnements');

    }

    public function Delete_AbonnementAction($ID_A)
    {


        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());


        unlink($this->getParameter('image_directory')."/qrproduit/".$produit->getImage()) ;
        $em->remove($abo);
        $em->remove($produit);
        $em->flush();
        return $this->redirectToRoute('_list_abonnements');

    }
    public function Detail_AbonnementAction($ID_A)
    {


        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());

        return $this->render('@Abonnement/abonnement/detail.html.twig', array('produit'=>$produit,'quantite'=>$abo->getQuantite()));

    }

    public function printAction()
    {




        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_abonment();



        $date=new \DateTime();
        $converstion = $date->format('d-m-yy');

        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $db = $this->getDoctrine()->getManager();


        $content="";


        for ($i=0 ;$i<count($list) ;$i++)
        {


            $content=$content. "<tr> ". "<td>".  $list[$i]['Libelle']  ."</td>". "<td>".  $list[$i]['Prix']."</td>". "<td>".  $list[$i]['quantite']    ."</td>" . "<td>".  $list[$i]['Lib_C']    ."</td>" ."<td>".  $list[$i]['Lib_S']    ."</td>". "<td>"."<img src=\C:/wamp64/www/bike/web/uploads/images/".$list[$i]['Image']."     "   ."style=\"width: 50px;\" ></td>" ."</tr>";
        }

        $snappy = $this->get("knp_snappy.pdf");

        $html = "
<html>
<head><meta http-equiv=Content-Type content=\"text/html; charset=UTF-8\">
<style type=\"text/css\">
<!--
span.cls_003{font-family:\"Verdana\",serif;font-size:8.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
div.cls_003{font-family:\"Verdana\",serif;font-size:8.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
span.cls_002{font-family:\"Verdana\",serif;font-size:9.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
div.cls_002{font-family:\"Verdana\",serif;font-size:9.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
span.cls_004{font-family:\"Verdana\",serif;font-size:6.0px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
div.cls_004{font-family:\"Verdana\",serif;font-size:6.0px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
-->
</style>
<style>
table {
  border-collapse: collapse;
  width: 80%;
  border: 2px;
}

th, td {
  text-align: left;
  padding: 1px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>
<div style=\"position:absolute;left:50%;margin-left:-297px;top:0px;width:595px;height:841px;border-style:outset;overflow:hidden\">
<div style=\"position:absolute;left:0px;top:0px\">
<img src=\"C:/wamp64/www/bike/web/uploads/images/tmp_pdf.jpg\" width=595 height=841></div>


<div style=\"position:absolute;left:320.30px;top:154.33px\" class=\"cls_002\"><span class=\"cls_002\">Le ".  $converstion  ."</span></div>
<div style=\"position:absolute;left:79.37px;top:162.54px\" class=\"cls_002\"><span class=\"cls_002\">Votre Numero :  ".    $user->getUserNumber()  ."   </span></div>
<div style=\"position:absolute;left:79.37px;top:200.86px\" class=\"cls_002\"><span class=\"cls_002\">Cher MR/Mme ADMIN ".    $user->getLastName(). " ".$user->getFirstName()  ." ,</span></div>
<div style=\"position:absolute;left:79.37px;top:213.54px\" class=\"cls_002\"><span class=\"cls_002\">Voici votre List de Reclamation </span></div>
<table style=\"position:absolute;left:55.37px;top:263.54px\">
  <tr>
    <th>Libelle</th>
    <th>Prix</th>
    <th>Quantite</th>
     <th>Categorie</th>
      <th>Site</th>
    <th>Image</th>
  </tr>
  
  ".$content."
  
  
  
</table>


<div style=\"position:absolute;left:79.37px;top:669.60px\" class=\"cls_002\"><span class=\"cls_002\">Aymen Ouerghui</span></div>
<div style=\"position:absolute;left:79.37px;top:680.07px\" class=\"cls_002\"><span class=\"cls_002\">Votre service clientèle</span></div>
<div style=\"position:absolute;left:79.37px;top:812.85px\" class=\"cls_004\"><span class=\"cls_004\">vous pouvez nous contacter à-partir de notre addresse mail </span><A HREF=\"http://www.Bike.fr/\">www.Bike.fr</A> </div>
<div style=\"position:absolute;left:79.37px;top:821.14px\" class=\"cls_004\"><span class=\"cls_004\">Bike : 9 RUE DU Ariana soghra tunis</span></div>
</div>

</body>
</html>


  ";
        $fileName = "abonnment";
        return new Response($snappy->getOutputFromHtml($html),200,array('Content-Type' => 'application/pdf','Content-Disposition' => 'attachment; filename="'.$fileName.'.pdf"'));
    }


}
