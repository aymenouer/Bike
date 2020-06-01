<?php

namespace AchatBundle\Controller;

use AbonnementBundle\Entity\abonnement;
use AchatBundle\Entity\achat;
use BG\BarcodeBundle\Util\Base2DBarcode as matrixCode;
use DateTime;
use ProduitBundle\Entity\produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class AchatController extends Controller
{
    public function Achat_troisMoisAction($ID_A,Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find($ID_A);
        $quantite=$abo->getQuantite();
        $abo->setQuantite($quantite-1);

        $produit=$em->getRepository(produit::class)->find($abo->getIdP());

        $em->persist($abo);
        $em->flush();




        $dateqr=new \DateTime();

        $date=new \DateTime();
        $datefin=date_add($date, date_interval_create_from_date_string('90 days'));
        $achat = new achat();
        $user=$this->container->get('security.token_storage')->getToken()->getUser();




        $contenu = 'Libelle : '.$produit->getLibelle().'<br>  Description : '. $produit->getDescription().' <br>  prix :'. (string) $produit->getPrix().' DT <br> Categorie : '. $produit->getLibC().' <br> Site : '. $produit->getLibS().'<br> achetée par MR/MD '. $user->getLastName().' '.$user->getFirstName().'<br> Le '.$dateqr->format('d-m-y') .'<br> valable jusqu a '.$datefin->format('d-m-y')  .'<br> Merci pour votre confiance &#128525;';

        $myBarcode = new matrixCode();
        $myBarcode->savePath = $this->getParameter('image_directory')."/";

        $myBarcode->getBarcodePNGPath($contenu, 'DATAMATRIX',10, 10,null,$user->getId());



        $code=md5(uniqid());
        $new_name_image = $this->getParameter('image_directory')."/qruser/".$code .".png";

        $image=$code .".png";
        rename($this->getParameter('image_directory')."/".$user->getId().".png",$new_name_image);







        $prix = (float)$request->get('prix');
        $prix=($prix- ($prix * 0.1));
        $achat->setPrix($prix);
        $achat->setIdA($ID_A);
        $achat->setDateD(new \DateTime());
        $achat->setDateF($datefin);
        $achat->setIdU($user->getId());
        $achat->setImage($image);
        $em = $this->getDoctrine()->getManager();
        $em->persist($achat);
        $em->flush();
        $number = '+216'.$user->getUserNumber() ;
        $message="        Bonjour,
Vous venez d’acheter un abonnement  ". $produit->getLibC() ." au nom de ".$user->getLastName()." ".$user->getFirstName(). " valable pendant trois mois à partir du ". $dateqr->format('d-m-Y') ."
Nous vous remercions pour votre précieuse confiance
 Bienvenue à Bike";

        $twilio = $this->get('twilio.api');

        $message = $twilio->account->messages->sendMessage(
            +13213254739,
            $number,
            $message
        );
        return $this->redirectToRoute("redirect");
    }

    public function Achat_unansAction($ID_A,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find($ID_A);
        $quantite=$abo->getQuantite();
        $abo->setQuantite($quantite-1);
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());


        $em->persist($abo);
        $em->flush();
        $date=new \DateTime();
        $datefin=date_add($date, date_interval_create_from_date_string('365 days'));
        $achat = new achat();
        $user=$this->container->get('security.token_storage')->getToken()->getUser();


        $dateqr=new \DateTime();


        $contenu = 'Libelle : '.$produit->getLibelle().'<br>  Description : '. $produit->getDescription().' <br>  prix :'. (string) $produit->getPrix().' DT <br> Categorie : '.  $produit->getLibC().' <br> Site : '. $produit->getLibS().'<br> achetee par MR/MD '. $user->getLastName().' '.$user->getFirstName().'<br> Le '.$dateqr->format('d-m-Y') .'<br> valable jusqu a '.$datefin->format('d-m-Y')  .'<br> Merci pour votre confiance &#128525;';

        $myBarcode = new matrixCode();
        $myBarcode->savePath = $this->getParameter('image_directory')."/";

        $myBarcode->getBarcodePNGPath($contenu, 'DATAMATRIX',10, 10,null,$user->getId());



        $code=md5(uniqid());
        $new_name_image = $this->getParameter('image_directory')."/qruser/".$code .".png";

        $image=$code .".png";
        rename($this->getParameter('image_directory')."/".$user->getId().".png",$new_name_image);





        $prix = (float)$request->get('prix');
        $prix=($prix- ($prix * 0.15));
        $achat->setPrix($prix);
        $achat->setIdA($ID_A);
        $achat->setDateD(new \DateTime());
        $achat->setDateF($datefin);
        $achat->setIdU($user->getId());
        $achat->setImage($image);
        $em = $this->getDoctrine()->getManager();
        $em->persist($achat);
        $em->flush();
        $date = new DateTime();
        $number = '+216'.$user->getUserNumber() ;
        $message="        Bonjour,
Vous venez d’acheter un abonnement  ". $produit->getLibC() ." au nom de ".$user->getLastName()." ".$user->getFirstName(). " valable pendant un ans à partir du ". $dateqr->format('d-m-Y') ."
Nous vous remercions pour votre précieuse confiance
 Bienvenue à Bike";

        $twilio = $this->get('twilio.api');

        $message = $twilio->account->messages->sendMessage(
            +13213254739,
            $number,
            $message
        );
        return $this->redirectToRoute("redirect");
    }

    public function Achat_unmoisAction($ID_A,Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $abo=$em->getRepository(abonnement::class)->find($ID_A);
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());
        $quantite=$abo->getQuantite();
        $abo->setQuantite($quantite-1);
        $em->persist($abo);
        $em->flush();
        $date=new \DateTime();
        $datefin=date_add($date, date_interval_create_from_date_string('30 days'));
        $dateqr=new \DateTime();
        $achat = new achat();
        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $contenu = 'Libelle : '.$produit->getLibelle().'<br>  Description : '. $produit->getDescription().' <br>  prix :'. (string) $produit->getPrix().' DT <br> Categorie : '. $produit->getLibC().' <br> Site : '. $produit->getLibS().'<br> achetee par MR/MD '. $user->getLastName().' '.$user->getFirstName().'<br> Le '.$dateqr->format('d-m-Y') .'<br> valable jusqu a '.$datefin->format('d-m-Y')  .'<br> Merci pour votre confiance &#128525;';

        $myBarcode = new matrixCode();
        $myBarcode->savePath = $this->getParameter('image_directory')."/";

        $myBarcode->getBarcodePNGPath($contenu, 'DATAMATRIX',10, 10,null,$user->getId());



        $code=md5(uniqid());
        $new_name_image = $this->getParameter('image_directory')."/qruser/".$code .".png";

        $image=$code .".png";
        rename($this->getParameter('image_directory')."/".$user->getId().".png",$new_name_image);

        $achat->setPrix((float)$request->get('prix'));
        $achat->setIdA($ID_A);
        $achat->setDateD(new \DateTime());
        $achat->setDateF($datefin);
        $achat->setIdU($user->getId());
        $achat->setImage($image);
        $em = $this->getDoctrine()->getManager();
        $em->persist($achat);
        $em->flush();

        $number = '+216'.$user->getUserNumber() ;
        $message="        Bonjour,
Vous venez d’acheter un abonnement  ". $produit->getLibC() ." au nom de ".$user->getLastName()." ".$user->getFirstName(). " valable pendant un mois à partir du ". $dateqr->format('d-m-Y')."
Nous vous remercions pour votre précieuse confiance
 Bienvenue à Bike";

        $twilio = $this->get('twilio.api');

        $message = $twilio->account->messages->sendMessage(
            +13213254739,
            $number,
            $message
        );


        $otherInstance = $twilio->createInstance('BBBB', 'CCCCC');
        return $this->redirectToRoute("redirect");
    }

    public function AffichageOffreAction()
    {
        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $db = $this->getDoctrine()->getManager();
        $achat = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $em=$this->getDoctrine()->getManager();

        $abo=$em->getRepository(abonnement::class)->find($achat[0]->getIdA());
        $produit=$em->getRepository(produit::class)->find($abo->getIdP());








        $verif_rating = $db->getRepository('RatingBundle:rating')->findBy(['idU' => $user->getId(),'idP'=>$abo->getIdP()]);







        return $this->render('@Achat/Achat/affichage_offre.html.twig',array('produit'=>$produit,'quantite'=>$abo->getQuantite(),'achat'=>$achat,'verif_rating'=>$verif_rating));
    }
    public function DeleteOffreAction()
    {

        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $db = $this->getDoctrine()->getManager();
        $achat = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
        $achat2 = new achat();
        $achat2 = $db->getRepository('AchatBundle:achat')->find($achat[0]->getId());
        $em=$this->getDoctrine()->getManager();
        unlink($this->getParameter('image_directory')."/qruser/".$achat2->getImage()) ;
        $em->remove($achat2);

        $em->flush();
        return $this->redirectToRoute("redirect");
    }

}
