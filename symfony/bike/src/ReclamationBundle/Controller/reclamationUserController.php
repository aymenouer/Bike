<?php

namespace ReclamationBundle\Controller;

use ReclamationBundle\Entity\reclamation;
use ReclamationBundle\Form\reclamationType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class reclamationUserController extends Controller
{
    public function ListReclamationUserAction()
    {
        $em = $this->getDoctrine()->getManager();

        $date=new \DateTime();
        $converstion = $date->format('d-m-yy');

        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $db = $this->getDoctrine()->getManager();


$content="";
        $listreclamation = $this->getDoctrine()->getRepository(reclamation::class)->return_reclamation_user($user->getId());


        for ($i=0 ;$i<count($listreclamation) ;$i++)
        {


$content=$content. "<tr> ". "<td>".  $listreclamation[$i]['Type']  ."</td>". "<td>".  $listreclamation[$i]['Contenu']."</td>". "<td>".  $listreclamation[$i]['etat']    ."</td>" . "<td>"."<img src=\C:/wamp64/www/bike/web/uploads/images/".$listreclamation[$i]['image']."     "   ."style=\"width: 50px;\" ></td>" ."</tr>";
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
<div style=\"position:absolute;left:79.37px;top:200.86px\" class=\"cls_002\"><span class=\"cls_002\">Cher MR/Mme ".    $user->getLastName(). " ".$user->getFirstName()  ." ,</span></div>
<div style=\"position:absolute;left:79.37px;top:213.54px\" class=\"cls_002\"><span class=\"cls_002\">Voici votre List de Reclamation </span></div>
<table style=\"position:absolute;left:55.37px;top:263.54px\">
  <tr>
    <th>Type</th>
    <th>Contenu</th>
    <th>etat</th>
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



     /*   $html = $this->render('ProduitBundle:Default:pdf.html.twig',array(
            'produits'=>$produits
        ));*/
        $fileName = "reclamation";
        return new Response($snappy->getOutputFromHtml($html),200,array('Content-Type' => 'application/pdf','Content-Disposition' => 'attachment; filename="'.$fileName.'.pdf"'));
    }
    public function ListReclamationUsersAction()
    {





        $listreclamation = $this->getDoctrine()->getRepository('ReclamationBundle:reclamation')->findAll();
        return $this->render('@Reclamation/reclamationUser/afficher_reclamation_user.html.twig', array(
            'listreclamation'=>$listreclamation
        ));


    }

    public function affmapAction()
    {





        return $this->render('@Reclamation/reclamationUser/test.html.twig');


    }

    function filterwords($text){
        $filterWords = array('fuck', 'nike', 'pute','bitch');
        $filterCount = sizeof($filterWords);
        for ($i = 0; $i < $filterCount; $i++) {
            $text = preg_replace_callback('/\b' . $filterWords[$i] . '\b/i', function($matches){return str_repeat('*', strlen($matches[0]));}, $text);
        }
        return $text;
    }


    public function AddReclamationUserAction(Request $request)
    {
        $reclamation = new reclamation();
        $form=$this->createForm(reclamationType::class,$reclamation);
        $form=$form->handleRequest($request);
        $etat="mauvais";
        $reclamation->setTraite("Non Traite");
        if ($request->get('etat') == "on")
        {
            $etat="Bien";
            $reclamation->setTraite("Traite");
        }

        if($form->isValid())
        {
            $file=$reclamation->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('image_directory'),$fileName);
            $reclamation->setImage($fileName);



            $reclamation->setContenu($this->filterwords($reclamation->getContenu()));


            $reclamation->setEtat($etat);
            $reclamation->setIdU($this->container->get('security.token_storage')->getToken()->getUser()->getId());
            $em=$this->getDoctrine()->getManager();
            $em->persist($reclamation);


            $em->flush();
            $user=$this->container->get('security.token_storage')->getToken()->getUser();
           if( $etat=="mauvais")
           { $message = \Swift_Message::newInstance()
                ->setSubject('BIKE: Réponse suite à la réclamation')
                ->setFrom('bike.pidev@gmail.com')
                ->setTo($user->getEmail())
                ->setBody(
                    $this->renderView(
                    // app/Resources/views/Emails/registration.html.twig
                        'Emails/mail_reclamation_mauvais.html.twig',
                        array(

                            'nom' =>  $user->getLastName() . " ". $user->getFirstName(),



                        )


                    ),
                    'text/html'
                );
            $this->get('mailer')->send($message);
           }
           else
           {
               $message = \Swift_Message::newInstance()
                   ->setSubject('BIKE: Réponse suite à la réclamation')
                   ->setFrom('bike.pidev@gmail.com')
                   ->setTo($user->getEmail())
                   ->setBody(
                       $this->renderView(
                       // app/Resources/views/Emails/registration.html.twig
                           'Emails/mail_reclamation_Bien.html.twig',
                           array(

                               'nom' =>  $user->getLastName() . " ". $user->getFirstName(),



                           )


                       ),
                       'text/html'
                   );
               $this->get('mailer')->send($message);
           }


return $this->redirectToRoute('_list_reclamation_users');
        }
        return $this->render('@Reclamation/reclamationUser/add_reclamation_user.html.twig', array(
            'form'=>$form->createView()
        ));
    }

}
