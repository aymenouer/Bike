<?php

namespace UserBundle\Controller;

use AbonnementBundle\Entity\abonnement;
use AccessoireBundle\Entity\accessoire;
use AchatBundle\Entity\achat;
use AppBundle\Entity\Message;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use MaitenanceBundle\Entity\maitenance;
use ProduitBundle\Entity\produit;
use RatingBundle\Entity\rating;
use ReclamationBundle\Entity\reclamation;
use ReservationBundle\Entity\Reservation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use BG\BarcodeBundle\Util\Base1DBarcode as barCode;
use BG\BarcodeBundle\Util\Base2DBarcode as matrixCode;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Translation\Translator;
class SecurityController extends Controller
{
    public function addAction()
    {
        $userManager = $this->container->get('fos_user.user_manager');
        $user = $userManager->createUser();
        $user->setUsername('aymen');
        $user->setEmail('aymen.ouerghui@esprit.tn');
        $user->setRoles(array('ROLE_ADMIN'));
        $user->setPlainPassword('12345');
        $user->setFirstName('aymen');
        $user->setLastName('ouerghui');
        $user->setUserImage('image.png');
        $user->setUserAge('21');
        $user->setUserNumber('53059655');
        $user->setEnabled(true);
        $userManager->updateUser($user);
        return $this->redirectToRoute("redirect");

    }

    public function redirectAction()
    {
        $authChecker = $this->container->get('security.authorization_checker');
        $this->getDoctrine()->getRepository(abonnement::class)->mise_a_jour_quantite();
        if($authChecker->isGranted('ROLE_ADMIN'))
        {
            $this->oublier_maitenance();



            $this->getDoctrine()->getRepository(maitenance::class)->mise_a_jour();
            $ratemax_produit=$this->getDoctrine()->getRepository(rating::class)->maxrate();



            $pieChartabo = new PieChart();
            $pieChartaccessoire = new PieChart();
            //stat premieum
            $pieChartabo = $this->statistique_Abonnment();
            $pieChartaccessoire = $this->statistique_Accessoire();



                $listrecalamtion_bien = $this->getDoctrine()->getRepository(reclamation::class)->return_reclamations_bien();
            $listrecalamtion_mauvais = $this->getDoctrine()->getRepository(reclamation::class)->return_reclamations_mauvais();

            return $this->render('@User/Security/admin_home.html.twig',array('statabo'=>$pieChartabo,'stataccessoire'=>$pieChartaccessoire,'listrecalamtion_bien'=>$listrecalamtion_bien,'ratemax_produit'=>$ratemax_produit,'listrecalamtion_mauvais'=>$listrecalamtion_mauvais));
        }
        else if($authChecker->isGranted('ROLE_USER'))
        {


            $requestsql = $this->getDoctrine()->getRepository(achat::class)->mise_a_jour();
            $requestsql = $this->getDoctrine()->getRepository(Reservation::class)->mise_a_jour();

            $this->getDoctrine()->getRepository(maitenance::class)->mise_a_jour();


            $db = $this->getDoctrine()->getManager();
            $user=$this->container->get('security.token_storage')->getToken()->getUser();
            //fonction passer premieum
            $this->passer_premium();

            $verif_abonnment = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);

            $list=$this->getDoctrine()->getRepository(produit::class)->produit_abonment();
            $listacessoire=$this->getDoctrine()->getRepository(produit::class)->produit_accessoire();
            $requestsql1 = $this->getDoctrine()->getRepository(Reservation::class);
            $nombre_reservation=$requestsql1->count($user->getId());
            $requestsql = $this->getDoctrine()->getRepository(produit::class);

            $listvelo = $requestsql->produit_velo();
            $listpiece=$this->getDoctrine()->getRepository(produit::class)->produit_piece();
            $nombre_message=$this->getDoctrine()->getRepository(abonnement::class)-> get_NBR_Messages($user->getId());
            return $this->render('@User/Security/user_home.html.twig', array('nombre_message'=>$nombre_message['nbr'],'listvelo'=>$listvelo,'listpiece'=>$listpiece,'nombre_reservation'=>$nombre_reservation,'listproduit'=>$list,'listaccessoire'=>$listacessoire,'verif_abonnment'=>$verif_abonnment));
        }
        else
        {
            return $this->render('@FOSUser/Security/login.html.twig');
        }

    }
    protected function getBarcodeCachePath($public = false)
    {

        return (!$public) ? $this->get('kernel')->getRootDir(). '/../web/upload/barcode/cache' : '/upload/barcode/cache';
    }

    private function  statistique_Abonnment()
    {
        $pieChartabo = new PieChart();
        $statistique=$this->getDoctrine()->getRepository(abonnement::class)->statistique_abo();

        $totalabo=0;
        for ($i=0 ;$i<count($statistique) ;$i++)
        {
            $totalabo=$totalabo+(int)$statistique[$i]['nombre'];
        }

        $data= array();
        $stat=['categorie', 'Nbr abo'];
        $nb=0;
        array_push($data,$stat);
        for ($i=0 ;$i<count($statistique) ;$i++)
        {
            $stat=array();
            array_push($stat,$statistique[$i]['Lib_C'],(((int)$statistique[$i]['nombre']) *100)/$totalabo);
            $nb=((int)$statistique[$i]['nombre'] *100)/$totalabo;
            $stat=[$statistique[$i]['Lib_C'],$nb];
            array_push($data,$stat);
        }

        $pieChartabo->getData()->setArrayToDataTable( $data);

        $pieChartabo->getOptions()->setTitle('nombre Abonnment selon categorie');
        $pieChartabo->getOptions()->setHeight(400);
        $pieChartabo->getOptions()->setWidth(400);
        $pieChartabo->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChartabo->getOptions()->getTitleTextStyle()->setFontSize(25);
        return $pieChartabo;
    }


    private function  statistique_Accessoire()
    {
        $pieChartaccessoire = new PieChart();
        $statistique=$this->getDoctrine()->getRepository(accessoire::class)->statistique_accessoire();

        $totalabo=0;
        for ($i=0 ;$i<count($statistique) ;$i++)
        {
            $totalabo=$totalabo+(int)$statistique[$i]['nombre'];
        }

        $data= array();
        $stat=['categorie', 'Nbr accessoire'];
        $nb=0;
        array_push($data,$stat);
        for ($i=0 ;$i<count($statistique) ;$i++)
        {
            $stat=array();
            array_push($stat,$statistique[$i]['Lib_C'],(((int)$statistique[$i]['nombre']) *100)/$totalabo);
            $nb=((int)$statistique[$i]['nombre'] *100)/$totalabo;
            $stat=[$statistique[$i]['Lib_C'],$nb];
            array_push($data,$stat);
        }

        $pieChartaccessoire->getData()->setArrayToDataTable( $data);

        $pieChartaccessoire->getOptions()->setTitle('nombre accessoire selon categorie');
        $pieChartaccessoire->getOptions()->setHeight(400);
        $pieChartaccessoire->getOptions()->setWidth(400);
        $pieChartaccessoire->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChartaccessoire->getOptions()->getTitleTextStyle()->setFontSize(25);
        return $pieChartaccessoire;
    }





    public function passer_premium()
    {
        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $nombrereservation=  $this->getDoctrine()->getRepository(Reservation::class)->nombre_reservation($user->getId());


        if((int)$nombrereservation['nb'] == 5)
        {
            $db = $this->getDoctrine()->getManager();
            $achat = $db->getRepository('AchatBundle:achat')->findBy(['idU' => $user->getId()]);
            $achat2 = new achat();
            $achat2 = $db->getRepository('AchatBundle:achat')->find($achat[0]->getId());
            $em=$this->getDoctrine()->getManager();
            $em->remove($achat2);

            $em->flush();
            $produit_abo=$this->getDoctrine()->getRepository(produit::class)->return_produit_categorie_premium();

            $date=new \DateTime();
            $datefin=date_add($date, date_interval_create_from_date_string('90 days'));
            $achat = new achat();
            $user=$this->container->get('security.token_storage')->getToken()->getUser();

            $prix=0;
            $achat->setPrix($prix);

            $achat->setIdA((int)$produit_abo['ID_A']);
            $achat->setDateD(new \DateTime());
            $achat->setDateF($datefin);
            $achat->setIdU($user->getId());
            $em = $this->getDoctrine()->getManager();
            $em->persist($achat);
            $em->flush();

        }
    }


    public function oublier_maitenance()
    {
        $user=$this->container->get('security.token_storage')->getToken()->getUser();
        $listmaintenance=$this->getDoctrine()->getRepository(maitenance::class)->oublier_maitenance();
        for ($i=0 ;$i<count($listmaintenance) ;$i++)
        {

            $message = \Swift_Message::newInstance()
                ->setSubject('rappel maintenance')
                ->setFrom('faydrahammami@gmail.com')
                ->setTo($user->getEmail())
                ->setBody(
                    $this->renderView(
                    // app/Resources/views/Emails/registration.html.twig
                        'Emails/mail_oublier_maintenance.html.twig',
                        array(

                            'nom' =>  $user->getLastName() . " ". $user->getFirstName(),
                            'txt' => 'l ' .$listmaintenance[$i]['type']." de problem : ".$listmaintenance[$i]['Problem'],


                        )


                    ),
                    'text/html'
                );
            $this->get('mailer')->send($message);

        }

    }
}
