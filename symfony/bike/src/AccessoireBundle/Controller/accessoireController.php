<?php

namespace AccessoireBundle\Controller;

use AccessoireBundle\Entity\accessoire;
use ProduitBundle\Entity\produit;
use ProduitBundle\Form\produitType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class accessoireController extends Controller
{
    public function ListAccessoireAction(Request $request)
    {
        $ID_P=null;
        $produit = new produit();
        $form=$this->createForm(produitType::class,$produit);
        $form=$form->handleRequest($request);
        $db = $this->getDoctrine()->getManager();
        $type=$request->get('type');

        $listcategorie = $db->getRepository('CategorieBundle:categorie')->findBy(['type' => 'Accessoire']);
        $listsite = $db->getRepository('SiteBundle:site')->findAll();

        if($form->isValid() && $form->isSubmitted() ) {

            $file=$produit->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('image_directory'),$fileName);
            $em = $this->getDoctrine()->getManager();
            $produit->setImage($fileName);
            $produit->setLibC((string)$request->get('categorie'));
            $produit->setLibS((string)$request->get('site'));
            $em->persist($produit);

            $em->flush();
            $accessoire = new accessoire();

            $emq = $this->getDoctrine()->getRepository(produit::class);
            $ID_P=$emq->return_produit_id($produit);
            $accessoire->setIdP((int)$ID_P['ID_P']);
            $accessoire->setQuantite((int)$request->get('Quantite'));
            $em->persist($accessoire);

            $em->flush();



            return $this->redirect($request->getUri());
        }
        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_accessoire();
        return $this->render('@Accessoire/accessoire/list_accessoire.html.twig', array('form'=>$form->createView(),'list'=>$list,'list_categorie'=>$listcategorie,'list_site'=>$listsite));
    }

    public function Update_AccessoireAction($ID_A,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $accessoire=$em->getRepository(accessoire::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($accessoire->getIdP());
        $accessoire->setQuantite((int)$request->get('Quantite'));
        $produit->setLibelle($request->get('Libelle'));
        $produit->setDescription($request->get('description'));
        $produit->setPrix((float)$request->get('prix'));
        $produit->setLibC($request->get('categorie'));
        $produit->setLibS($request->get('site'));
        $em->persist($produit);
        $em->persist($accessoire);
        $em->flush();

        return $this->redirectToRoute('_list_accessoire');
    }

    public function Delete_AccessoireAction($ID_A)
    {
        $em=$this->getDoctrine()->getManager();
        $accessoire=$em->getRepository(accessoire::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($accessoire->getIdP());
        $em->remove($accessoire);
        $em->remove($produit);
        $em->flush();
        return $this->redirectToRoute('_list_accessoire');
    }

    public function Detail_AccessoireAction($ID_A)
    {
        $em=$this->getDoctrine()->getManager();
        $accessoire=$em->getRepository(accessoire::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($accessoire->getIdP());



        return $this->render('@Accessoire/accessoire/detail.accessoire.html.twig', array('produit'=>$produit,'quantite'=>$accessoire->getQuantite()));
    }
    public function Detail_front_AccessoireAction($ID_A)
    {
        $em=$this->getDoctrine()->getManager();
        $accessoire=$em->getRepository(accessoire::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($accessoire->getIdP());
        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $db = $this->getDoctrine()->getManager();
        $verif_rating = $db->getRepository('RatingBundle:rating')->findBy(['idU' => $user->getId(),'idP'=>$accessoire->getIdP()]);



        return $this->render('@Accessoire/accessoire/detail_front_accessoire.html.twig', array('produit'=>$produit,'quantite'=>$accessoire->getQuantite(),'verif_rating'=>$verif_rating));
    }

    public function PrintPDF_AccessoireAction()
    {
        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list=$requestsql->produit_accessoire();



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
        $fileName = "accessoire";
        return new Response($snappy->getOutputFromHtml($html),200,array('Content-Type' => 'application/pdf','Content-Disposition' => 'attachment; filename="'.$fileName.'.pdf"'));
    }



}
