<?php

namespace ReservationBundle\Controller;

use AchatBundle\Entity\achat;
use CategorieBundle\Entity\categorie;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Knp\Component\Pager\Paginator;
use ProduitBundle\Entity\produit;
use ReservationBundle\Entity\Reservation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use mikehaertl\wkhtmlto\Pdf;
use VeloBundle\Entity\Velo;


class ReservationController extends Controller
{
    public function AfficherReservationAction()
    {
        $db = $this->getDoctrine()->getManager();
        $listcategorie = $db->getRepository('CategorieBundle:categorie')->findAll();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $requestsql = $this->getDoctrine()->getRepository(produit::class);

        $list = $requestsql->produit_velo();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $requestsql1 = $this->getDoctrine()->getRepository(Reservation::class);
        $x=$requestsql1->count($user->getId());

        return $this->render("@Reservation/Reservation/list.html.twig", array('x'=>$x,'velo' => $list,  'list_categorie' => $listcategorie, 'list_site' => $listsite));
    }
    public function countAction()
    {

        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $requestsql1 = $this->getDoctrine()->getRepository(Reservation::class);
        $x=$requestsql1->count($user->getId());

        return $this->render('@Reservation/Reservation/test.html.twig',array('x'=>$x));
    }
    public function bookingAction($id_v)
    {

        $requestsql = $this->getDoctrine()->getRepository(produit::class);
        $list_velos = $requestsql->produit_velo();
        $velo = null;

        foreach ($list_velos as $row) {
            if ((int)$row['id'] == $id_v) {
                $velo = $row;
            }

        }
        $requestsql = $this->getDoctrine()->getRepository(reservation::class);
        $list_velo_reserve_date = $requestsql->list_velo_reserve_id($id_v);
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $requestsql1 = $this->getDoctrine()->getRepository(Reservation::class);
        $x=$requestsql1->count($user->getId());
        return $this->render("@Reservation/Reservation/booking.html.twig", array('x'=>$x,'velo' => $velo, 'list_velo_reserve_date' => $list_velo_reserve_date));
    }
    public function prix1($idR)
    {
        $requestsql1 = $this->getDoctrine()->getRepository(Reservation::class);
        $x=$requestsql1->prixReservation($idR);

        return $x;

    }

    public function reserverAction(Request $request)
    {
        $ID_V = (int)$request->get('ID_V');
        $Date_D = (string)($request->get('Date_D'));
        $Date_F = (string)($request->get('Date_F'));

        $list = $this->getDoctrine()->getRepository(Reservation::class)->list_disponible1($Date_F,$Date_D,$ID_V);


       if (sizeof($list)==0) {

            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $velo = $this->getDoctrine()->getRepository(velo::class)->find($ID_V);
            $produit = $this->getDoctrine()->getRepository(produit::class)->find($velo->getIdA());
            $reservation = new Reservation();

            $prix = (float)$produit->getPrix();

            $reservation->setIdV($ID_V);
            $reservation->setPrix((float)$prix);
            $reservation->setIDU((int)$user->getId());
            $reservation->setDateD(new \DateTime($Date_D));
            $reservation->setDateF(new \DateTime($Date_F));


            $em = $this->getDoctrine()->getManager();
            $em->flush();
            $em->persist($reservation);


            $em->flush();
            $velo->setEtat('reservée');
            $em->persist($velo);


           $x=$this->prix1($reservation->getId());
           $nbr=$x['nbr'];
           $xx=floatval($nbr);

           $reservation->setPrix($xx*$prix);
           $em->persist($reservation);
           $em->flush();



            return  $this->redirectToRoute("redirect");
        }

        return $this->render("@Reservation/Reservation/test.html.twig", array('a'=>$Date_D,'b'=>$Date_F));
    }
    public function printAction()
{

    $user = $this->container->get('security.token_storage')->getToken()->getUser();
    $id=$user->getId();


    $list = $this->getDoctrine()->getRepository(Reservation::class)->produit_reservation_velo($id);




    $date=new \DateTime();
    $converstion = $date->format('d-m-yy');

    $user=$this->container->get('security.token_storage')->getToken()->getUser();
    $db = $this->getDoctrine()->getManager();


    $content="";


    for ($i=0 ;$i<count($list) ;$i++)
    {

        $content=$content. "<tr> ". "<td>".  $list[$i]['Libelle']  ."</td>". "<td>".  $list[$i]['Prix']."</td>". "<td>".  $list[$i]['Date_D']    ."</td>" ."<td>".  $list[$i]['Date_F']    ."</td>". "<td>"."<img src=\C:/wamp64/www/PhpstormProjects/bike/bike/web/uploads/images/".$list[$i]['Image']."     "   ."style=\"width: 50px;\" ></td>" ."</tr>";
    }

    $snappy = $this->get("knp_snappy.pdf");

    $html = "
 <html >
<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">
<style>
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
 
   color: black;
   
}
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
  font-family :Montserrat;
  font-size:16px;
  font-weight:400;
  line-height:24px;
font-smoothing: antialiased;
  text-size-adjust: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}
</style>
<body>
<div ><span >Le " .  $converstion  ."</span></div>
<div class=\"w3-container\">
  <h2> Reservation </h2>
  <p>Voila votre liste de reservations</p>


          </div>
<table class=\"shop_table table--responsive cart table\">
  <tr class=\"cart-title\">
    <th>Libelle</th>
    <th>Prix</th>
    
     <th>Date_Debut</th>
      <th>Date_Fin</th>
    <th>Image</th>
  </tr>
  
  ".$content."
  
  
 </div>
</table>


</body>
<div class=\"footer\">
<div ><span class=\"cls_002\">Cher MR/Mme ".    $user->getLastName(). " ".$user->getFirstName()  ." ,</span></div>
<div ><span class=\"cls_004\">Vous pouvez nous contater sur </span><A HREF=\"http://www.Bike.fr/\">www.Bike.fr</A> </div>
<div >Notre Adresse : Esprit, Ariana soghra tunis</span></div>
</div>

</html>


  ";
    $fileName = "Reservation";
    return new Response($snappy->getOutputFromHtml($html),200,array('Content-Type' => 'application/pdf','Content-Disposition' => 'attachment; filename="'.$fileName.'.pdf"'));
}

    public function AfficherLesReservationAction(Request $request)
    {

        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $em = $this->getDoctrine()->getManager();
        $rese = $em->getRepository('ReservationBundle:Reservation')->produit_reservation_velo($user->getId());


        /**
         * @var $paginator Paginator
         */
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate(
            $rese,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',3)
        );


        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $requestsql1 = $this->getDoctrine()->getRepository(Reservation::class);
        $x=$requestsql1->count($user->getId());

        return $this->render("@Reservation/Reservation/display.html.twig", array('x'=>$x,'rese' => $result));

    }


    public function DeleteAction($idR)
    {
        $em = $this->getDoctrine()->getManager();
        $modele = $em->getRepository(Reservation::class)->find($idR);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('reservation_display');
    }
    public function editReservationAction($idR,Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $modele = $em->getRepository(Reservation::class)->editing($request->get('Date_D'),$request->get('Date_F'),$idR);
        $em->persist($modele);
        $em->flush();
        return $this->redirectToRoute('reservation_display');
    }
    public function shopnowAction()
    {    $db = $this->getDoctrine()->getManager();
        $listcategorie = $db->getRepository('CategorieBundle:categorie')->findAll();
        $listsite = $db->getRepository('SiteBundle:site')->findAll();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $id=$user->getId();
        $list = $this->getDoctrine()->getRepository(Reservation::class)->produit_reservation_velo($id);
        return $this->render("@Reservation/Reservation/product.html.twig",array('rese'=>$list,'LC'=>$listcategorie,'LS'=>$listsite));

    }
    public function print1Action()
    {

        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $id=$user->getId();


        $list = $this->getDoctrine()->getRepository(Reservation::class)->produit_reservation_velo($id);




        $date=new \DateTime();
        $converstion = $date->format('d-m-yy');

        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $db = $this->getDoctrine()->getManager();


        $content="";


        for ($i=0 ;$i<count($list) ;$i++)
        {

            $content=$content. "<tr> ". "<td>".  $list[$i]['Libelle']  ."</td>". "<td>".  $list[$i]['Prix']."</td>". "<td>".  $list[$i]['Date_D']    ."</td>" ."<td>".  $list[$i]['Date_F']    ."</td>". "<td>"."<img src=\C:/wamp64/www/PhpstormProjects/bike/bike/web/uploads/images/".$list[$i]['Image']."     "   ."style=\"width: 50px;\" ></td>" ."</tr>";
        }

        $snappy = $this->get("knp_snappy.pdf");

        $html = "
<html>

<head>

<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">
<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">
<style>
.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
 
   color: black;
   
}
</style>
</head>
<body>
<div ><span >Le ".  $converstion  ."</span></div>
<div class=\"w3-container\">
  <h2> Reservation </h2>
  <p>Voila votre liste de reservations</p>

<table class=\"w3-table-all\">
  <tr class=\"w3-red\">
    <th>Libelle</th>
    <th>Prix</th>
    
     <th>Date_Debut</th>
      <th>Date_Fin</th>
    <th>Image</th>
  </tr>
  
  ".$content."
  
  
 </div>
</table>


</body>
<div class=\"footer\">
<div ><span class=\"cls_002\">Cher MR/Mme ".    $user->getLastName(). " ".$user->getFirstName()  ." ,</span></div>
<div ><span class=\"cls_004\">Vous pouvez nous contater sur </span><A HREF=\"http://www.Bike.fr/\">www.Bike.fr</A> </div>
<div >Notre Adresse : Esprit, Ariana soghra tunis</span></div>
</div>>
</html>


  ";
        $fileName = "Reservation";
        return new Response($snappy->getOutputFromHtml($html),200,array('Content-Type' => 'application/pdf','Content-Disposition' => 'attachment; filename="'.$fileName.'.pdf"'));
    }

}